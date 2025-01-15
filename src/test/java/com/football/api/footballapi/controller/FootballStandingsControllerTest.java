package com.football.api.footballapi.controller;

import com.football.api.footballapi.exception.NoResultFoundException;
import com.football.api.footballapi.model.StandingsModel;
import com.football.api.footballapi.service.StandingsService;
import com.football.api.footballapi.utils.TestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class FootballStandingsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private StandingsService standingsService;

    @InjectMocks
    private FootballStandingsController controller;

    @Test
    void getStandings() throws Exception {

        this.mockMvc.perform(get("http://localhost:8080/standings")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testGetStandings() throws Exception {
        StandingsModel[] models = TestUtil.getStandings();
        Mockito.when(standingsService.getStandings("England", null, null)).thenReturn(List.of(models));

        List<StandingsModel> result = controller.getStandings("England", null, null);
        assertNotNull(result);
        assertEquals(4,result.size());
    }

    @Test
    void testGetStandingsError() throws Exception {
        Mockito.when(standingsService.getStandings("England", null, null)).thenThrow(NoResultFoundException.class);
        assertThrows(NoResultFoundException.class,()-> controller.getStandings("England", null, null));
    }
}