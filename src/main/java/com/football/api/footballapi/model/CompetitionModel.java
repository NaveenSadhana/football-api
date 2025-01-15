package com.football.api.footballapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "country_id",
        "country_name",
        "league_id",
        "league_name",
        "league_season",
        "league_logo",
        "country_logo"
})
@JsonSerialize
@JsonDeserialize
@Data
@ToString

public class CompetitionModel {

    @JsonProperty("country_id")
    String countryId;

    @JsonProperty("country_name")
    String countryName;

    @JsonProperty("league_id")
    String leagueId;

    @JsonProperty("league_name")
    String leagueName;

    @JsonProperty("league_season")
    String leagueSeason;

    @JsonProperty("league_logo")
    String leagueLogo;

    @JsonProperty("country_logo")
    String countryLogo;
}
