package com.football.api.footballapi.scheduler;

import com.football.api.footballapi.entity.CompetitionEntity;
import com.football.api.footballapi.entity.StandingsEntity;
import com.football.api.footballapi.model.CompetitionModel;
import com.football.api.footballapi.model.StandingsModel;
import com.football.api.footballapi.repository.CompetitionRepository;
import com.football.api.footballapi.repository.StandingsRepository;
import com.football.api.footballapi.service.impl.ApiConnectionAdapter;
import com.football.api.footballapi.utils.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ApiDataToDatabaseLoaderTest {



    @Mock
    private ApiConnectionAdapter apiConnectionAdapter;

    @Mock
    private CompetitionRepository competitionRepository;

    @Mock
    private StandingsRepository standingsRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ApiDataToDatabaseLoader apiDataToDatabaseLoader;

    @Test
    void loadCompetitionsAndStandingsData() throws IOException, ExecutionException, InterruptedException {

        CompetitionModel[] competitions = TestUtil.getCompetitions();
        StandingsModel[] standings = TestUtil.getStandings();

        Mockito.when(apiConnectionAdapter.getCompetitionsFromApi()).thenReturn(competitions);
        Mockito.when(apiConnectionAdapter.getStandingsModels(List.of(competitions))).thenReturn(List.of(standings));
        assertDoesNotThrow(() -> apiDataToDatabaseLoader.loadCompetitionsAndStandingsData());

    }

    @Test
    void loadCompetitionsAndStandingsDataNoData() throws IOException, ExecutionException, InterruptedException {

        CompetitionModel[] competitions = {};
        Mockito.when(apiConnectionAdapter.getCompetitionsFromApi()).thenReturn(competitions);
        assertDoesNotThrow(() -> apiDataToDatabaseLoader.loadCompetitionsAndStandingsData());

    }
}