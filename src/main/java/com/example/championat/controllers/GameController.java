package com.example.championat.controllers;


import com.example.championat.model.Resultat;
import com.example.championat.repository.ResultatRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/resultats")
public class ResultatController {
    private final ResultatRepository resultatRepository;

    @Autowired
    public ResultatController(ResultatRepository resultatRepository) {
        this.resultatRepository = resultatRepository;
    }

    @GetMapping("/")
    public List<Resultat> all() {
        return resultatRepository.findAll();
    }

    @GetMapping("/championnat/{championnatId}")
    public List<Resultat> getResultatsByChampionnatId(@PathVariable Long championnatId) {
        return resultatRepository.findByJourneeId(championnatId);
    }

    @GetMapping("/journee/{journeeId}")
    public List<Resultat> getResultatsByJourneeId(@PathVariable Long journeeId) {
        return resultatRepository.findByJourneeId(journeeId);
    }

    @GetMapping("/{id}")
    public Resultat getOne(@PathVariable Long id) {
        return resultatRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Resultat introuvable"));
    }

    @PostMapping("/")
    public ResponseEntity<Resultat> saveResultat(@Valid @RequestBody Resultat resultat, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        } else {
            resultatRepository.save(resultat);
            return new ResponseEntity<>(resultat, HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resultat> updateResultat(@PathVariable Long id, @Valid @RequestBody Resultat resultatUpdate, BindingResult bindingResult) {
        Resultat resultat = resultatRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Resultat introuvable"));

        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        } else {
            resultatUpdate.setId(resultat.getId());
            resultatRepository.save(resultatUpdate);
            return new ResponseEntity<>(resultatUpdate, HttpStatus.CREATED);
        }
    }


    public ResponseEntity<String> deleteOne(@PathVariable Long id) {
        Resultat resultat = resultatRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Resultat introuvable"));

        resultatRepository.delete(resultat);
        return ResponseEntity.ok("Le resultat a bien été supprimé");
    }
}