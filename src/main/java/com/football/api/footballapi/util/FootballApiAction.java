package com.football.api.footballapi.util;

import lombok.Getter;

@Getter
public enum FootballApiAction {
    STANDINGS("get_standings"),
    COMPETITIONS("get_leagues");
    private final String action;

    FootballApiAction(String action) {
        this.action = action;
    }

}