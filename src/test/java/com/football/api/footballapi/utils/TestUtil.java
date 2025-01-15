package com.football.api.footballapi.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.football.api.footballapi.entity.StandingsEntity;
import com.football.api.footballapi.model.CompetitionModel;
import com.football.api.footballapi.model.StandingsModel;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TestUtil {

    private TestUtil(){}

    private final static ObjectMapper objectMapper = new ObjectMapper();

    public static CompetitionModel[] getCompetitions() throws IOException {
        return objectMapper.readValue(new File("src/test/resources/files/json/competitions.json"), CompetitionModel[].class);
    }

    public static StandingsModel[] getStandings() throws IOException {
        return objectMapper.readValue(new File("src/test/resources/files/json/standings.json"), StandingsModel[].class);
    }
}
