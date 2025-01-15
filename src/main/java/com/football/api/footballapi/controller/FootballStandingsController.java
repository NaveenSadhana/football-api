package com.football.api.footballapi.controller;

import com.football.api.footballapi.model.StandingsModel;
import com.football.api.footballapi.service.StandingsService;
import com.football.api.footballapi.validation.InputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FootballStandingsController {

    @Autowired
    private StandingsService standingsService;

    @GetMapping("/standings")
    public List<StandingsModel> getStandings(
            @RequestParam(value = "country_name", required = false) String countryName,
            @RequestParam(value = "league_name", required = false) String leagueName,
            @RequestParam(value = "team_name", required = false) String teamName) throws Exception {
        InputValidator.validateInputParams(countryName);
        InputValidator.validateInputParams(leagueName);
        InputValidator.validateInputParams(teamName);
        return standingsService.getStandings(countryName, leagueName, teamName);
    }
}
