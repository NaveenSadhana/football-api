package com.football.api.footballapi.service;

import com.football.api.footballapi.model.CompetitionModel;
import com.football.api.footballapi.model.StandingsModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public interface StandingsService {

    List<StandingsModel> getStandings(String countryName, String leagueName, String teamName) throws Exception;

    List<StandingsModel> getStandingsForAllLeagues(List<CompetitionModel> competitions) throws InterruptedException, ExecutionException;

    List<CompetitionModel> getCompetitions();
}
