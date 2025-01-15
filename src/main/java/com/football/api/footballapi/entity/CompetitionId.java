package com.football.api.footballapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class CompetitionId {
    @Id
    @Column(name = "league_id")
    public String leagueId;
}
