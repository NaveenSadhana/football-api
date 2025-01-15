package com.football.api.footballapi.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "country_name",
        "league_id",
        "league_name",
        "team_id",
        "team_name",
        "overall_promotion",
        "overall_league_position",
        "overall_league_payed",
        "overall_league_W",
        "overall_league_D",
        "overall_league_L",
        "overall_league_GF",
        "overall_league_GA",
        "overall_league_PTS",
        "home_league_position",
        "home_promotion",
        "home_league_payed",
        "home_league_W",
        "home_league_D",
        "home_league_L",
        "home_league_GF",
        "home_league_GA",
        "home_league_PTS",
        "away_league_position",
        "away_promotion",
        "away_league_payed",
        "away_league_W",
        "away_league_D",
        "away_league_L",
        "away_league_GF",
        "away_league_GA",
        "away_league_PTS",
        "league_round",
        "team_badge",
        "fk_stage_key",
        "stage_name"
})
@Data
public class StandingsModel {

    @JsonProperty("country_name")
    private String countryName;
    @JsonProperty("league_id")
    private String leagueId;
    @JsonProperty("league_name")
    private String leagueName;
    @JsonProperty("team_id")
    private String teamId;
    @JsonProperty("team_name")
    private String teamName;
    @JsonProperty("overall_promotion")
    private String overallPromotion;
    @JsonProperty("overall_league_position")
    private String overallLeaguePosition;
    @JsonProperty("overall_league_payed")
    private String overallLeaguePayed;
    @JsonProperty("overall_league_W")
    private String overallLeagueW;
    @JsonProperty("overall_league_D")
    private String overallLeagueD;
    @JsonProperty("overall_league_L")
    private String overallLeagueL;
    @JsonProperty("overall_league_GF")
    private String overallLeagueGF;
    @JsonProperty("overall_league_GA")
    private String overallLeagueGA;
    @JsonProperty("overall_league_PTS")
    private String overallLeaguePTS;
    @JsonProperty("home_league_position")
    private String homeLeaguePosition;
    @JsonProperty("home_promotion")
    private String homePromotion;
    @JsonProperty("home_league_payed")
    private String homeLeaguePayed;
    @JsonProperty("home_league_W")
    private String homeLeagueW;
    @JsonProperty("home_league_D")
    private String homeLeagueD;
    @JsonProperty("home_league_L")
    private String homeLeagueL;
    @JsonProperty("home_league_GF")
    private String homeLeagueGF;
    @JsonProperty("home_league_GA")
    private String homeLeagueGA;
    @JsonProperty("home_league_PTS")
    private String homeLeaguePTS;
    @JsonProperty("away_league_position")
    private String awayLeaguePosition;
    @JsonProperty("away_promotion")
    private String awayPromotion;
    @JsonProperty("away_league_payed")
    private String awayLeaguePayed;
    @JsonProperty("away_league_W")
    private String awayLeagueW;
    @JsonProperty("away_league_D")
    private String awayLeagueD;
    @JsonProperty("away_league_L")
    private String awayLeagueL;
    @JsonProperty("away_league_GF")
    private String awayLeagueGF;
    @JsonProperty("away_league_GA")
    private String awayLeagueGA;
    @JsonProperty("away_league_PTS")
    private String awayLeaguePTS;
    @JsonProperty("league_round")
    private String leagueRound;
    @JsonProperty("team_badge")
    private String teamBadge;
    @JsonProperty("fk_stage_key")
    private String fkStageKey;
    @JsonProperty("stage_name")
    private String stageName;
}