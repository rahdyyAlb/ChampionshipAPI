package com.example.championat.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Le champ nom ne peut pas être null")
    @NotBlank(message = "Le champ nom ne peut pas être vide")
    private String nom;
    @NotNull(message = "Le champ email ne peut pas être null")
    @NotBlank(message = "Le champ email ne peut pas être vide")
    private String email;
    @NotNull(message = "Le champ motDePasse ne peut pas être null")
    @NotBlank(message = "Le champ motDePasse ne peut pas être vide")
    private String motDePasse;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }


    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
