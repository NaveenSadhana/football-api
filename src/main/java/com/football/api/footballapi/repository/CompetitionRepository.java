package com.football.api.footballapi.repository;

import com.football.api.footballapi.entity.CompetitionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompetitionRepository extends JpaRepository<CompetitionEntity, String> {

}

