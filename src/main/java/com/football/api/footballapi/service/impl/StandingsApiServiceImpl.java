package com.football.api.footballapi.service.impl;

import com.football.api.footballapi.exception.NoResultFoundException;
import com.football.api.footballapi.model.CompetitionModel;
import com.football.api.footballapi.model.StandingsModel;
import com.football.api.footballapi.service.StandingsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Slf4j
@Component("StandingsApiServiceImpl")
@ConditionalOnProperty(name = "enable.football.api.call", havingValue = "true", matchIfMissing = true)
public class StandingsApiServiceImpl implements StandingsService {

    @Autowired
    private ApiConnectionAdapter apiConnectionAdapter;

    @Override
    public List<StandingsModel> getStandings(String countryName, String leagueName, String teamName) throws Exception {

        List<CompetitionModel> competitions = filterLeaguesByLeagueNameAndCountryName(countryName, leagueName);
        if (competitions.isEmpty()) {
            throw new NoResultFoundException("No data found for the criteria");
        }

        List<StandingsModel> standings = getStandingsModelsFromAPi(competitions);

        if (standings.isEmpty()) {
            throw new NoResultFoundException("No data found for the criteria");
        }
        return standings.stream().filter(standingsModel -> (teamName == null || teamName.equals(standingsModel.getTeamName()))).collect(Collectors.toList());
    }

    private List<StandingsModel> getStandingsModelsFromAPi(List<CompetitionModel> competitions) throws Exception {
        return getStandingsForAllLeagues(competitions);
    }

    @Override
    public List<StandingsModel> getStandingsForAllLeagues(List<CompetitionModel> competitions) throws InterruptedException, ExecutionException {
        return apiConnectionAdapter.getStandingsModels(competitions);
    }




    private List<CompetitionModel> filterLeaguesByLeagueNameAndCountryName(String countryName, String leagueName) {

        List<CompetitionModel> competitionModels = getCompetitions();

        return competitionModels == null || competitionModels.isEmpty() ? Collections.emptyList() : getCompetitions().stream().filter(competitionModel ->
                (countryName == null || countryName.equals(competitionModel.getCountryName()))
                        && (leagueName == null || leagueName.equals(competitionModel.getLeagueName()))).toList();
    }

    @Override
    public List<CompetitionModel> getCompetitions() {

        //get all competitions
        CompetitionModel[] apiRes = apiConnectionAdapter.getCompetitionsFromApi();
        return apiRes == null ? Collections.emptyList() : List.of(apiRes);
    }
}
