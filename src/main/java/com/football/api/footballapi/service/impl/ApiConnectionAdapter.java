package com.football.api.footballapi.service.impl;

import com.football.api.footballapi.feign.FootballApiFeignClient;
import com.football.api.footballapi.model.CompetitionModel;
import com.football.api.footballapi.model.StandingsModel;
import com.football.api.footballapi.util.FootballApiAction;
import feign.form.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Component
@Slf4j
public class ApiConnectionAdapter {

    @Value("${football.service.api.key}")
    private String apiKey;
    @Autowired
    private FootballApiFeignClient footballApiFeignClient;

    public CompetitionModel[] getCompetitionsFromApi() {
        log.info("Calling Football getLeagues Api");
        Map<String, String> params = new HashMap<>();
        params.put("action", FootballApiAction.COMPETITIONS.getAction());
        params.put("APIkey", apiKey);
        return footballApiFeignClient.getCompetitions(params, ContentType.of(MediaType.APPLICATION_JSON_VALUE));
    }

    private List<Callable<List<StandingsModel>>> getCallables(List<CompetitionModel> competitions) {
        List<Callable<List<StandingsModel>>> tasks = new ArrayList<>();

        for (CompetitionModel competitionModel : competitions) {
            tasks.add(() -> getStandingsFromApi(competitionModel.getLeagueId()));
        }
        return tasks;
    }

    public List<StandingsModel> getStandingsModels(List<CompetitionModel> competitions) throws InterruptedException, ExecutionException {
        try (ExecutorService es = Executors.newFixedThreadPool(competitions.size())) {
            List<Callable<List<StandingsModel>>> tasks = getCallables(competitions);
            List<Future<List<StandingsModel>>> response = es.invokeAll(tasks);

            List<StandingsModel> standings = new ArrayList<>();
            for (Future<List<StandingsModel>> future : response) {
                standings.addAll(future.get());
            }
            return standings;
        }
    }


    public List<StandingsModel> getStandingsFromApi(String leagueId) {
        Map<String, String> params = new HashMap<>();
        params.put("APIkey", apiKey);
        params.put("action", FootballApiAction.STANDINGS.name());
        params.put("league_id", leagueId);
        return footballApiFeignClient.getStandings(params, ContentType.of(MediaType.APPLICATION_JSON_VALUE));
    }
}
