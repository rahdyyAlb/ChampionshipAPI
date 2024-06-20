package com.example.championat.repository;

import com.example.championat.model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends CrudRepository<Team, Integer> {
    List<Team> findByChampionships_Id(int championnatId);
}