package com.football.api.footballapi.feign;

import com.football.api.footballapi.model.CompetitionModel;
import com.football.api.footballapi.model.StandingsModel;
import feign.form.ContentType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Map;

@FeignClient(name = "football-api", url = "${football.service.api.url}")
public interface FootballApiFeignClient {

    @GetMapping
    List<StandingsModel> getStandings(@SpringQueryMap Map<String, String> customParams, @RequestHeader ContentType contentType);

    @GetMapping
    CompetitionModel[] getCompetitions(@SpringQueryMap Map<String, String> customParams, @RequestHeader ContentType contentType);
}
