package com.example.championat.controllers;

import com.example.championat.model.Championnat;
import com.example.championat.model.Team;
import com.example.championat.repository.ChampionnatRepository;
import com.example.championat.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private ChampionnatRepository championnatRepository;

    @GetMapping("/")
    public List<Team> getAllTeams() {
        return (List<Team>) teamRepository.findAll();
    }

    @GetMapping("/championnat/{championnatId}")
    public List<Team> getTeamsByChampionnatId(@PathVariable int championnatId) {
        return teamRepository.findByChampionships_Id(championnatId);
    }

    @GetMapping("/{id}")
    public Team getTeamById(@PathVariable int id) {
        return teamRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found"));
    }

    @PostMapping("/")
    public ResponseEntity<Team> createTeam(@Valid @RequestBody Team team, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        }
        teamRepository.save(team);
        return new ResponseEntity<>(team, HttpStatus.CREATED);
    }

    @PostMapping("/{teamId}/championnat/{championnatId}")
    public ResponseEntity<Team> addTeamToChampionnat(@PathVariable int teamId, @PathVariable int championnatId) {
        Team team = teamRepository.findById(teamId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found"));
        Championnat championnat = championnatRepository.findById(championnatId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Championnat not found"));

        team.getChampionships().add(championnat);
        championnat.getTeams().add(team);

        teamRepository.save(team);
        championnatRepository.save(championnat);

        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable int id, @Valid @RequestBody Team teamUpdate, BindingResult bindingResult) {
        Team team = teamRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found"));
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        }
        teamUpdate.setId(team.getId());
        teamRepository.save(teamUpdate);
        return new ResponseEntity<>(teamUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable int id) {
        Team team = teamRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found"));
        teamRepository.delete(team);
        return ResponseEntity.ok("Team deleted successfully");
    }
}