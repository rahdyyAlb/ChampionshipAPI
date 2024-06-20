package com.example.championat.repository;

import com.example.championat.model.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<Game, Integer> {
    List<Game> findByDay_Championnat_Id(int championnatId);

    List<Game> findByDay_Id(int dayId);
}