package com.football.api.footballapi.service.impl;

import com.football.api.footballapi.exception.NoResultFoundException;
import com.football.api.footballapi.model.CompetitionModel;
import com.football.api.footballapi.model.StandingsModel;
import com.football.api.footballapi.utils.TestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class StandingsApiServiceImplTest {

    @InjectMocks
    private StandingsApiServiceImpl standingsApiService;

    @Mock
    private ApiConnectionAdapter apiConnectionAdapter;


    @Test
    void getStandings() throws Exception {
        String countryName = "England";
        CompetitionModel[] models = TestUtil.getCompetitions();
        List<StandingsModel> standings = Arrays.stream(TestUtil.getStandings()).filter(item->item.getCountryName().equals(countryName)).collect(Collectors.toList());
        Mockito.when(apiConnectionAdapter.getCompetitionsFromApi()).thenReturn(models);
        Mockito.when( apiConnectionAdapter.getStandingsModels(any())).thenReturn(standings);
        List<StandingsModel> result = standingsApiService.getStandings(countryName, null, null);
        assertTrue(result.stream().allMatch(item->item.getCountryName().equals(countryName)));

    }

    @Test
    void getStandingsNoCompetitions() throws Exception {
        String countryName = "England";
        CompetitionModel[] models = {};
        List<StandingsModel> standings = Arrays.stream(TestUtil.getStandings()).filter(item -> item.getCountryName().equals(countryName)).collect(Collectors.toList());
        Mockito.when(apiConnectionAdapter.getCompetitionsFromApi()).thenReturn(models);
        assertThrows(NoResultFoundException.class,
                () -> standingsApiService.getStandings(countryName, null, null));
    }

    @Test
    void getStandingsNoStandings() throws Exception {
        CompetitionModel[] models = TestUtil.getCompetitions();
        Mockito.when(apiConnectionAdapter.getCompetitionsFromApi()).thenReturn(models);
        Mockito.when(apiConnectionAdapter.getStandingsModels(any())).thenReturn(Collections.emptyList());
        assertThrows(NoResultFoundException.class,
                () -> standingsApiService.getStandings("England", null, null));
    }

    @Test
    void getStandingsForAllLeagues() throws Exception {
        CompetitionModel[] models = TestUtil.getCompetitions();
        StandingsModel[] standings = TestUtil.getStandings();
        Mockito.when( apiConnectionAdapter.getStandingsModels(any())).thenReturn(List.of(standings));
        List<StandingsModel> result = standingsApiService.getStandingsForAllLeagues(List.of(models));
        assertNotNull(result);
        assertEquals(4, result.size());
    }

    @Test
    void getCompetitions() throws IOException {
        CompetitionModel[] models = TestUtil.getCompetitions();
        Mockito.when( apiConnectionAdapter.getCompetitionsFromApi()).thenReturn(models);
        List<CompetitionModel> result = standingsApiService.getCompetitions();
        assertNotNull(result);
        assertEquals(4, result.size());
    }
}