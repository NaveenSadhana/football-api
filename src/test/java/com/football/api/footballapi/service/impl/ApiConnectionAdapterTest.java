package com.football.api.footballapi.service.impl;

import com.football.api.footballapi.feign.FootballApiFeignClient;
import com.football.api.footballapi.model.CompetitionModel;
import com.football.api.footballapi.model.StandingsModel;
import com.football.api.footballapi.utils.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
@ExtendWith(MockitoExtension.class)
class ApiConnectionAdapterTest {

    @InjectMocks
    private ApiConnectionAdapter apiConnectionAdapter;

    @Mock
    private FootballApiFeignClient apiFeignClient;

    @Test
    void getCompetitionsFromApi() throws IOException {
        CompetitionModel[] models = TestUtil.getCompetitions();

        Mockito.when(apiFeignClient.getCompetitions(any(), any())).thenReturn(models);
        CompetitionModel[] result = apiConnectionAdapter.getCompetitionsFromApi();
        Assertions.assertEquals(result.length, 4);

    }

    @Test
    void getStandingsModels() throws IOException, ExecutionException, InterruptedException {
        CompetitionModel[] competitions = TestUtil.getCompetitions();
        StandingsModel[] standings = TestUtil.getStandings();
        Mockito.when(apiFeignClient.getStandings(any(), any())).thenReturn(List.of(standings));
        List<StandingsModel>  result = apiConnectionAdapter.getStandingsModels(List.of(competitions));
        assertNotNull(result);
        assertEquals(16, result.size());

    }

    @Test
    void getStandingsFromApi() throws IOException {

        StandingsModel[] standings = TestUtil.getStandings();
        Mockito.when(apiFeignClient.getStandings(any(), any())).thenReturn(List.of(standings));
        List<StandingsModel> result = apiConnectionAdapter.getStandingsFromApi("300");
        assertNotNull(result);
        assertEquals(4, result.size());
    }
}