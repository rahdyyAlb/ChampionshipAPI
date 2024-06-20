package com.example.championat.repository;

import com.example.championat.model.Championnat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChampionnatRepository extends CrudRepository<Championnat, Integer> {
    List<Championnat> findAll();

    Optional<Championnat> findById(Long id);
}