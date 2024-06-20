package com.example.championat.controllers;

import com.example.championat.model.Team;
import com.example.championat.repository.EquipeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/equipes")
public class EquipeController {
    private final EquipeRepository equipeRepository;

    @Autowired
    public EquipeController(EquipeRepository equipeRepository) {
        this.equipeRepository = equipeRepository;
    }

    @GetMapping("/")
    public List<Team> all() {
        return equipeRepository.findAll();
    }

    @GetMapping("/championnat/{championnatId}")
    public List<Team> getEquipesByChampionnatId(@PathVariable Long championnatId) {
        return equipeRepository.findByChampionnatId(championnatId);
    }

    @GetMapping("/{id}")
    public Team getOne(@PathVariable Long id) {
        return equipeRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Team introuvable"));
    }

    @PostMapping("/")
    public ResponseEntity<Team> saveEquipe(@Valid @RequestBody Team team, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        } else {
            equipeRepository.save(team);
            return new ResponseEntity<>(team, HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> updateEquipe(@PathVariable Long id, @Valid @RequestBody Team teamUpdate, BindingResult bindingResult) {
        Team team = equipeRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Team introuvable"));

        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        } else {
            teamUpdate.setId(team.getId());
            equipeRepository.save(teamUpdate);
            return new ResponseEntity<>(teamUpdate, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOne(@PathVariable Long id) {
        Team team = equipeRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Team introuvable"));

        equipeRepository.delete(team);
        return ResponseEntity.ok("L'Team a bien été supprimée");
    }
}