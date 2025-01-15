package com.football.api.footballapi.scheduler;

import com.football.api.footballapi.entity.CompetitionEntity;
import com.football.api.footballapi.entity.StandingsEntity;
import com.football.api.footballapi.model.CompetitionModel;
import com.football.api.footballapi.model.StandingsModel;
import com.football.api.footballapi.repository.CompetitionRepository;
import com.football.api.footballapi.repository.StandingsRepository;
import com.football.api.footballapi.service.impl.ApiConnectionAdapter;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class ApiDataToDatabaseLoader {

    private final ApiConnectionAdapter apiConnectionAdapter;

    private final CompetitionRepository competitionRepository;

    private final StandingsRepository standingsRepository;

    private final ModelMapper modelMapper;

    public ApiDataToDatabaseLoader(@Autowired ApiConnectionAdapter apiConnectionAdapter,
                                   @Autowired CompetitionRepository competitionRepository,
                                   @Autowired StandingsRepository standingsRepository,
                                   @Autowired ModelMapper modelMapper) {
        this.apiConnectionAdapter = apiConnectionAdapter;
        this.competitionRepository = competitionRepository;
        this.standingsRepository = standingsRepository;
        this.modelMapper = modelMapper;
    }

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.HOURS)
    public void loadCompetitionsAndStandingsData() throws ExecutionException, InterruptedException {

        List<CompetitionModel> competitionModelList = getCompetitionModels();
        if (competitionModelList == null) return;

        loadStandingsToDatabase(competitionModelList);
    }

    private void loadStandingsToDatabase(List<CompetitionModel> competitionModelList) throws InterruptedException, ExecutionException {
        List<StandingsModel> standingsModels = apiConnectionAdapter.getStandingsModels(competitionModelList);

        List<StandingsEntity> standingsEntities = modelMapper.map(standingsModels, new TypeToken<List<StandingsEntity>>() {
        }.getType());

        List<StandingsEntity> savedStandings = standingsRepository.saveAll(standingsEntities);

        log.info("Loaded {} Standings into the database", savedStandings.size());
    }

    private List<CompetitionModel> getCompetitionModels() {
        List<CompetitionModel> competitionModelList = List.of(apiConnectionAdapter.getCompetitionsFromApi());

        if (competitionModelList.isEmpty()) {
            return Collections.emptyList();
        }
        List<CompetitionEntity> competitionEntities = modelMapper.map(competitionModelList, new TypeToken<List<CompetitionEntity>>() {
        }.getType());

        List<CompetitionEntity> savedCompetitions = competitionRepository.saveAll(competitionEntities);

        log.info("Loaded {} competitions into the database", savedCompetitions.size());
        return competitionModelList;
    }
}
