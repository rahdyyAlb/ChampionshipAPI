package com.example.championat.repository;

import com.example.championat.model.Day;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface JourneeRepository extends CrudRepository<Day, Long> {
    @Override
    List<Day> findAll();
    List<Day> findByChampionnatId(Long championnatId);

    @Transactional
    void deleteByChampionnatId(Long championnatId);
}