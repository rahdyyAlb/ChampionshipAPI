package com.example.championat;

import com.example.championat.model.*;
import com.example.championat.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Configuration
public class LoadData {
    private final Logger log = LoggerFactory.getLogger(LoadData.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository,
                                   ChampionnatRepository championnatRepository,
                                   TeamRepository teamRepository,
                                   DayRepository dayRepository,
                                   GameRepository gameRepository) {
        return args -> {
            log.info("Chargement des données");

            // Vérifiez si les tables sont vides avant de charger les données
            if (userRepository.count() == 0) {
                User user1 = new User();
                user1.setNom("Dupont");
                user1.setEmail("dupont@example.com");
                user1.setPassword("password");
                userRepository.save(user1);

                User user2 = new User();
                user2.setNom("Durand");
                user2.setEmail("durand@example.com");
                user2.setPassword("password");
                userRepository.save(user2);

                log.info("Utilisateurs chargés: " + userRepository.count());
            } else {
                log.info("Utilisateurs déjà chargés");
            }

            if (championnatRepository.count() == 0) {
                Championnat championnat1 = new Championnat();
                championnat1.setName("Ligue 1");
                championnat1.setStartDate(Date.from(LocalDate.of(2023, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
                championnat1.setEndDate(Date.from(LocalDate.of(2023, 12, 31).atStartOfDay(ZoneId.systemDefault()).toInstant()));
                championnatRepository.save(championnat1);

                Championnat championnat2 = new Championnat();
                championnat2.setName("Ligue 2");
                championnat2.setStartDate(Date.from(LocalDate.of(2023, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
                championnat2.setEndDate(Date.from(LocalDate.of(2023, 12, 31).atStartOfDay(ZoneId.systemDefault()).toInstant()));
                championnatRepository.save(championnat2);

                log.info("Championnats chargés: " + championnatRepository.count());
            } else {
                log.info("Championnats déjà chargés");
            }

            if (teamRepository.count() == 0) {
                Championnat championnat1 = championnatRepository.findById(1).orElse(null);
                if (championnat1 != null) {
                    Team team1 = new Team();
                    team1.setName("PSG");
                    team1.setCreationDate(Date.from(LocalDate.of(2023, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    team1.setChampionships(Collections.singletonList(championnat1));
                    teamRepository.save(team1);

                    Team team2 = new Team();
                    team2.setName("OM");
                    team2.setCreationDate(Date.from(LocalDate.of(2023, 1, 1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    team2.setChampionships(Collections.singletonList(championnat1));
                    teamRepository.save(team2);

                    championnat1.setTeams(Arrays.asList(team1, team2));
                    championnatRepository.save(championnat1);

                    log.info("Equipes chargées: " + teamRepository.count());
                }
            } else {
                log.info("Equipes déjà chargées");
            }

            if (dayRepository.count() == 0) {
                Championnat championnat1 = championnatRepository.findById(1).orElse(null);
                if (championnat1 != null) {
                    Day day1 = new Day();
                    day1.setNumber("1");
                    day1.setChampionnat(championnat1);
                    dayRepository.save(day1);

                    Day day2 = new Day();
                    day2.setNumber("2");
                    day2.setChampionnat(championnat1);
                    dayRepository.save(day2);

                    log.info("Journées chargées: " + dayRepository.count());
                }
            } else {
                log.info("Journées déjà chargées");
            }

            if (gameRepository.count() == 0) {
                Championnat championnat1 = championnatRepository.findById(1).orElse(null);
                if (championnat1 != null) {
                    List<Day> days = dayRepository.findByChampionnat_Id(championnat1.getId());
                    if (!days.isEmpty()) {
                        Day day1 = days.get(0);
                        Team team1 = teamRepository.findById(1).orElse(null);
                        Team team2 = teamRepository.findById(2).orElse(null);

                        if (team1 != null && team2 != null) {
                            Game game1 = new Game();
                            game1.setDay(day1);
                            game1.setTeam1(team1);
                            game1.setTeam2(team2);
                            game1.setTeam1Point(2);
                            game1.setTeam2Point(1);
                            gameRepository.save(game1);

                            Game game2 = new Game();
                            game2.setDay(day1);
                            game2.setTeam1(team2);
                            game2.setTeam2(team1);
                            game2.setTeam1Point(3);
                            game2.setTeam2Point(3);
                            gameRepository.save(game2);

                            log.info("Jeux chargés: " + gameRepository.count());
                        }
                    }
                }
            } else {
                log.info("Jeux déjà chargés");
            }
        };
    }
}