package com.example.championat.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDate;
import java.util.List;
@Entity
public class Journee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le champ date ne peut pas être null")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "championnat_id")
    @JsonBackReference
    private Championnat championnat;

    @OneToMany(mappedBy = "journee")
    @JsonBackReference
    private List<Resultat> resultats;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Championnat getChampionnat() {
        return championnat;
    }

    public void setChampionnat(Championnat championnat) {
        this.championnat = championnat;
    }

    public List<Resultat> getResultats() {
        return resultats;
    }

    public void setResultats(List<Resultat> resultats) {
        this.resultats = resultats;
    }

    @Override
    public String toString() {
        return "Journee{" +
                "id=" + id +
                ", date=" + date +
                ", championnat=" + championnat +
                ", resultats=" + resultats +
                '}';
    }
}