package com.football.api.footballapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class StandingsId {
    @Id
    @Column(name = "team_id")
    public String teamId;
    @Id
    @Column(name = "league_id")
    public String leagueId;
}
