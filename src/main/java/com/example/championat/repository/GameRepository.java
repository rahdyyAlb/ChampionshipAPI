package com.example.championat.repository;

import com.example.championat.model.Resultat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultatRepository extends CrudRepository<Resultat, Long> {
    @Override
    List<Resultat> findAll();
    List<Resultat> findByJourneeId(Long journeeId);
}