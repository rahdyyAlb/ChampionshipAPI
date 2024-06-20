package com.example.championnatapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
public class Championnat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    @OneToMany(mappedBy = "championnat")
    private List<Equipe> equipes;

    @OneToMany(mappedBy = "championnat")
    private List<Journee> journees;

    // Getters et setters
}