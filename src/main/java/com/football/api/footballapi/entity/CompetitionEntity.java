package com.football.api.footballapi.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "COMPETITION")
@Data
@IdClass(CompetitionId.class)
public class CompetitionEntity {

    @Id
    @Column(name = "league_id")
    public String leagueId;
    @Column(name = "country_id")
    public String countryId;
    @Column(name = "country_name")
    public String countryName;
    @Column(name = "league_name")
    public String leagueName;
    @Column(name = "league_season")
    public String leagueSeason;
    @Column(name = "league_logo")
    public String leagueLogo;
    @Column(name = "country_logo")
    public String countryLogo;
}
