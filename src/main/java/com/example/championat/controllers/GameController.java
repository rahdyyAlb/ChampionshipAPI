package com.example.championat.controllers;

import com.example.championat.model.Game;
import com.example.championat.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {
    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/")
    public List<Game> getAllGames() {
        return (List<Game>) gameRepository.findAll();
    }

    @GetMapping("/championnat/{championnatId}")
    public List<Game> getGamesByChampionnatId(@PathVariable int championnatId) {
        return gameRepository.findByDay_Championnat_Id(championnatId);
    }

    @GetMapping("/day/{dayId}")
    public List<Game> getGamesByDayId(@PathVariable int dayId) {
        return gameRepository.findByDay_Id(dayId);
    }

    @GetMapping("/{id}")
    public Game getGameById(@PathVariable int id) {
        return gameRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found"));
    }

    @PostMapping("/")
    public ResponseEntity<Game> createGame(@Valid @RequestBody Game game, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        }
        gameRepository.save(game);
        return new ResponseEntity<>(game, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable int id, @Valid @RequestBody Game gameUpdate, BindingResult bindingResult) {
        Game game = gameRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found"));
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        }
        gameUpdate.setId(game.getId());
        gameRepository.save(gameUpdate);
        return new ResponseEntity<>(gameUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGame(@PathVariable int id) {
        Game game = gameRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found"));
        gameRepository.delete(game);
        return ResponseEntity.ok("Game deleted successfully");
    }
}