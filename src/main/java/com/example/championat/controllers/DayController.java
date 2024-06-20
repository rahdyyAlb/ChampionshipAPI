package com.example.championat.controllers;

import com.example.championat.model.Day;
import com.example.championat.repository.DayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/days")
public class DayController {
    @Autowired
    private DayRepository dayRepository;

    @GetMapping("/")
    public List<Day> getAllDays() {
        return (List<Day>) dayRepository.findAll();
    }

    @GetMapping("/championnat/{championnatId}")
    public List<Day> getDaysByChampionnatId(@PathVariable int championnatId) {
        return dayRepository.findByChampionnat_Id(championnatId);
    }

    @GetMapping("/{id}")
    public Day getDayById(@PathVariable int id) {
        return dayRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Day not found"));
    }

    @PostMapping("/")
    public ResponseEntity<Day> createDay(@Valid @RequestBody Day day, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        }
        dayRepository.save(day);
        return new ResponseEntity<>(day, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Day> updateDay(@PathVariable int id, @Valid @RequestBody Day dayUpdate, BindingResult bindingResult) {
        Day day = dayRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Day not found"));
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        }
        dayUpdate.setId(day.getId());
        dayRepository.save(dayUpdate);
        return new ResponseEntity<>(dayUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDay(@PathVariable int id) {
        Day day = dayRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Day not found"));
        dayRepository.delete(day);
        return ResponseEntity.ok("Day deleted successfully");
    }
}