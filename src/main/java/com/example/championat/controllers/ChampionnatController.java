package com.example.championat.controllers;

import com.example.championat.model.Championnat;
import com.example.championat.repository.ChampionnatRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/championnats")
public class ChampionnatController {
    private final ChampionnatRepository championnatRepository;

    @Autowired
    public ChampionnatController(ChampionnatRepository championnatRepository) {
        this.championnatRepository = championnatRepository;
    }

    @GetMapping(value = "/")
    public List<Championnat> all() {
        return championnatRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Championnat getOne(@PathVariable Long id) {
        return championnatRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Championnat introuvable"));
    }

    @PostMapping(value = "/")
    public ResponseEntity<Championnat> saveChampionnat(@Valid @RequestBody Championnat championnat) {
        championnatRepository.save(championnat);
        return new ResponseEntity<>(championnat, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Championnat> updateChampionnat(@PathVariable Long id, @Valid @RequestBody Championnat championnatUpdate) {
        Championnat championnat = championnatRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Championnat introuvable"));

        championnatUpdate.setId(championnat.getId());
        championnatRepository.save(championnatUpdate);
        return new ResponseEntity<>(championnatUpdate, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteOne(@PathVariable Long id) {
        Championnat championnat = championnatRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Championnat introuvable"));

        championnatRepository.delete(championnat);
        return ResponseEntity.ok("Le championnat a bien été supprimé");
    }
}