package com.example.championat.controllers;



import com.example.championat.model.User;
import com.example.championat.repository.UtilisateurRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {
    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurController(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @GetMapping("/")
    public List<User> all() {
        return utilisateurRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getOne(@PathVariable Long id) {
        return utilisateurRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User introuvable"));
    }

    @GetMapping("/login")
    public User getByEmailAndMotDePasse(@RequestParam String email, @RequestParam String motDePasse) {
        return utilisateurRepository.findByEmailAndMotDePasse(email, motDePasse).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User introuvable"));
    }

    @PostMapping("/")
    public ResponseEntity<User> saveUtilisateur(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        } else {
            utilisateurRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUtilisateur(@PathVariable Long id, @Valid @RequestBody User userUpdate, BindingResult bindingResult) {
        User user = utilisateurRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User introuvable"));

        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.toString());
        } else {
            userUpdate.setId(user.getId());
            utilisateurRepository.save(userUpdate);
            return new ResponseEntity<>(userUpdate, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOne(@PathVariable Long id) {
        User user = utilisateurRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User introuvable"));

        utilisateurRepository.delete(user);
        return ResponseEntity.ok("L'user a bien été supprimé");
    }
}