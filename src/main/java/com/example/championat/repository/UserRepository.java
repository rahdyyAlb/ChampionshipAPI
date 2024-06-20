package com.example.championat.repository;


import com.example.championat.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends CrudRepository<User, Long> {
    @Override
    List<User> findAll();
    Optional<User> findByEmailAndMotDePasse(String email, String motDePasse);
}