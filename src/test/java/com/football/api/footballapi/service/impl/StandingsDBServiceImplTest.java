package com.football.api.footballapi.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.football.api.footballapi.entity.StandingsEntity;
import com.football.api.footballapi.model.CompetitionModel;
import com.football.api.footballapi.model.StandingsModel;
import com.football.api.footballapi.scheduler.ApiDataToDatabaseLoader;
import com.football.api.footballapi.utils.TestUtil;
import jakarta.persistence.AssociationOverride;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class StandingsDBServiceImplTest {

    @Autowired
    private StandingsDBServiceImpl service;



    @Test
    void getStandings() throws Exception {
        String countryName = "England";

        Thread.sleep(7000);
        List<StandingsModel> result = service.getStandings(countryName, null,null);

        assertEquals(88, result.size());
        assertTrue(result.stream().allMatch(item->item.getCountryName().equals(countryName)));

    }

    @Test
    void getStandingsLeagueNameFilter() throws Exception {
        String leagueName = "Non League Premier";
        Thread.sleep(7000);

        List<StandingsModel> result = service.getStandings(null, leagueName,null);

        assertEquals(88, result.size());
        assertTrue(result.stream().allMatch(item->item.getLeagueName().equals(leagueName)));

    }

    @Test
    void getStandingsTeamNameFilter() throws Exception {
        String teamName = "Marine";
        Thread.sleep(7000);

        List<StandingsModel> result = service.getStandings(null, null,teamName);

        assertEquals(1, result.size());
        assertTrue(result.stream().allMatch(item->item.getTeamName().equals(teamName)));

    }

    @Test
    void getStandingsForAllLeagues() throws IOException, ExecutionException, InterruptedException {
        CompetitionModel[] models = TestUtil.getCompetitions();
        List<StandingsModel> standings = service.getStandingsForAllLeagues(List.of(models));
        assertNull(standings);

    }

    @Test
    void getCompetitions() {
        List<CompetitionModel> competitionModelList = service.getCompetitions();
        assertNull(competitionModelList);
    }
}