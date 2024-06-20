package com.example.championat.repository;

import com.example.championat.model.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquipeRepository extends CrudRepository<Team, Long> {
    @Override
    List<Team> findAll();

    @Query("SELECT e FROM Team e JOIN e.championnats c WHERE c.id = :championnatId")
    List<Team> findByChampionnatId(@Param("championnatId") Long championnatId);

    Optional<Team> findByNom(String nom);
}