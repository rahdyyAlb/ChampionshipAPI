package com.example.championat.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

@Entity
public class Championnat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Le nom du championnat ne peut pas être vide")
    private String name;

    @NotNull(message = "La date de début ne peut pas être null")
    private Date startDate;

    @NotNull(message = "La date de fin ne peut pas être null")
    private Date endDate;

    private int wonPoint;
    private int lostPoint;
    private int drawPoint;

    @OneToMany(mappedBy = "championnat")
    @JsonIgnore
    private List<Day> days;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "TeamChampionShip",
            joinColumns = @JoinColumn(name = "idChampionship"),
            inverseJoinColumns = @JoinColumn(name = "idTeam")
    )
    private List<Team> teams;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getWonPoint() {
        return wonPoint;
    }

    public void setWonPoint(int wonPoint) {
        this.wonPoint = wonPoint;
    }

    public int getLostPoint() {
        return lostPoint;
    }

    public void setLostPoint(int lostPoint) {
        this.lostPoint = lostPoint;
    }

    public int getDrawPoint() {
        return drawPoint;
    }

    public void setDrawPoint(int drawPoint) {
        this.drawPoint = drawPoint;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    @Override
    public String toString() {
        return "Championnat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", wonPoint=" + wonPoint +
                ", lostPoint=" + lostPoint +
                ", drawPoint=" + drawPoint +
                ", days=" + days +
                ", teams=" + teams +
                '}';
    }
}