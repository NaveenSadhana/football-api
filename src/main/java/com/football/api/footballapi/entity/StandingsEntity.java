package com.football.api.footballapi.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "STANDINGS")
@Data
@IdClass(StandingsId.class)
public class StandingsEntity {
    @Id
    @Column(name = "team_id")
    public String teamId;
    @Id
    @Column(name = "league_id")
    public String leagueId;
    @Column(name = "country_name")
    public String countryName;
    @Column(name = "league_name")
    public String leagueName;
    @Column(name = "team_name")
    public String teamName;
    @Column(name = "overall_promotion")
    public String overallPromotion;
    @Column(name = "overall_league_position")
    public String overallLeaguePosition;
    @Column(name = "overall_league_payed")
    public String overallLeaguePayed;
    @Column(name = "overall_league_W")
    public String overallLeagueW;
    @Column(name = "overall_league_D")
    public String overallLeagueD;
    @Column(name = "overall_league_L")
    public String overallLeagueL;
    @Column(name = "overall_league_GF")
    public String overallLeagueGF;
    @Column(name = "overall_league_GA")
    public String overallLeagueGA;
    @Column(name = "overall_league_PTS")
    public String overallLeaguePTS;
    @Column(name = "home_league_position")
    public String homeLeaguePosition;
    @Column(name = "home_promotion")
    public String homePromotion;
    @Column(name = "home_league_payed")
    public String homeLeaguePayed;
    @Column(name = "home_league_W")
    public String homeLeagueW;
    @Column(name = "home_league_D")
    public String homeLeagueD;
    @Column(name = "home_league_L")
    public String homeLeagueL;
    @Column(name = "home_league_GF")
    public String homeLeagueGF;
    @Column(name = "home_league_GA")
    public String homeLeagueGA;
    @Column(name = "home_league_PTS")
    public String homeLeaguePTS;
    @Column(name = "away_league_position")
    public String awayLeaguePosition;
    @Column(name = "away_promotion")
    public String awayPromotion;
    @Column(name = "away_league_payed")
    public String awayLeaguePayed;
    @Column(name = "away_league_W")
    public String awayLeagueW;
    @Column(name = "away_league_D")
    public String awayLeagueD;
    @Column(name = "away_league_L")
    public String awayLeagueL;
    @Column(name = "away_league_GF")
    public String awayLeagueGF;
    @Column(name = "away_league_GA")
    public String awayLeagueGA;
    @Column(name = "away_league_PTS")
    public String awayLeaguePTS;
    @Column(name = "league_round")
    public String leagueRound;
    @Column(name = "team_badge")
    public String teamBadge;
    @Column(name = "fk_stage_key")
    public String fkStageKey;
    @Column(name = "stage_name")
    public String stageName;
}
