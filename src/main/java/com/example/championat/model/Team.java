package com.example.championat.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Equipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le champ nom ne peut pas être null")
    @NotBlank(message = "Le champ nom ne peut pas être vide")
    private String nom;

    @ManyToMany(mappedBy = "equipes")
    private List<Championnat> championnats;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Championnat> getChampionnats() {
        return championnats;
    }

    public void setChampionnats(List<Championnat> championnats) {
        this.championnats = championnats;
    }

    @Override
    public String toString() {
        return "Equipe{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", championnats=" + championnats +
                '}';
    }
}