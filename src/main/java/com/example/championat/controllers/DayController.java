package com.example.championat.controllers;



import com.example.championat.model.Day;
import com.example.championat.repository.JourneeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/journees")
public class JourneeController {
    private final JourneeRepository journeeRepository;

    @Autowired
    public JourneeController(JourneeRepository journeeRepository) {
        this.journeeRepository = journeeRepository;
    }

    @GetMapping("/")
    public List<Day> all() {
        return journeeRepository.findAll();
    }

    @GetMapping("/championnat/{championnatId}")
    public List<Day> getJourneesByChampionnatId(@PathVariable Long championnatId) {
        return journeeRepository.findByChampionnatId(championnatId);
    }

    @GetMapping("/{id}")
    public Day getOne(@PathVariable Long id) {
        return journeeRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Journee introuvable"));
    }

    @PostMapping("/")
    public ResponseEntity<Day> saveJournee(@Valid @RequestBody Day day, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        } else {
            journeeRepository.save(day);
            return new ResponseEntity<>(day, HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Day> updateJournee(@PathVariable Long id, @Valid @RequestBody Day dayUpdate, BindingResult bindingResult) {
        Day day = journeeRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Journee introuvable"));
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        } else {
            dayUpdate.setId(day.getId());
            journeeRepository.save(dayUpdate);
            return new ResponseEntity<>(dayUpdate, HttpStatus.CREATED);
        }
    }

    public ResponseEntity<String> deleteOne(@PathVariable Long id) {
        Day day = journeeRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Journee introuvable"));

        journeeRepository.delete(day);
        return ResponseEntity.ok("La Journee a bien été supprimé");
    }
}