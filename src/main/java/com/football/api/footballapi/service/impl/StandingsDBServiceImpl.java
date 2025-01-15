package com.football.api.footballapi.service.impl;

import com.football.api.footballapi.entity.StandingsEntity;
import com.football.api.footballapi.model.CompetitionModel;
import com.football.api.footballapi.model.StandingsModel;
import com.football.api.footballapi.repository.CompetitionRepository;
import com.football.api.footballapi.repository.StandingsRepository;
import com.football.api.footballapi.service.StandingsService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Slf4j
@Component
@ConditionalOnProperty(name = "enable.football.api.call", havingValue = "false", matchIfMissing = true)
public class StandingsDBServiceImpl implements StandingsService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EntityManager em;


    @Override
    public List<StandingsModel> getStandings(String countryName, String leagueName, String teamName) throws Exception {

        Map<String, String> params = new HashMap<>();
        if (countryName != null && !countryName.isBlank()) {
            params.put("countryName", countryName);
        }
        if (leagueName != null && !leagueName.isBlank()) {
            params.put("leagueName", leagueName);
        }
        if (teamName != null && !teamName.isBlank()) {
            params.put("teamName", teamName);
        }
        List<StandingsModel> standings;
        List<StandingsEntity> entities;
        try {
            entities = getDataFromDatabase(params);

        } catch (Exception ex) {
            log.error("Exception occurred while getting response from DB ", ex.fillInStackTrace());
            throw new Exception();
        }
        if(entities==null || entities.isEmpty()){
            return Collections.emptyList();
        }
        standings = modelMapper.map(entities, new TypeToken<List<StandingsModel>>() {
        }.getType());
        return standings;
    }

    /**
     * Not implementing because our requirement is to get the Standings based on league_name, country_name and team_name.
     * All parameters are available in the Standings Table
     * @param competitions
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Override
    public List<StandingsModel> getStandingsForAllLeagues(List<CompetitionModel> competitions) throws InterruptedException, ExecutionException {
        return null;
    }

    /**
     * Not implementing because our requirement is to get the Standings based on league_name, country_name and team_name.
     * All parameters are available in the Standings Table
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Override
    public List<CompetitionModel> getCompetitions() {
        return null;
    }

    private List<StandingsEntity> getDataFromDatabase(Map<String, String> params) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<StandingsEntity> cq = cb.createQuery(StandingsEntity.class);

        Root<StandingsEntity> standingsEntity = cq.from(StandingsEntity.class);
        List<Predicate> predicates = new ArrayList<>();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            predicates.add(cb.equal(standingsEntity.get(entry.getKey()), entry.getValue()));
        }
        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList();
    }
}
