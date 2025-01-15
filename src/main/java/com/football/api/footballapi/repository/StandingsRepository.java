package com.football.api.footballapi.repository;

import com.football.api.footballapi.entity.StandingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StandingsRepository extends JpaRepository<StandingsEntity, String> {
}
