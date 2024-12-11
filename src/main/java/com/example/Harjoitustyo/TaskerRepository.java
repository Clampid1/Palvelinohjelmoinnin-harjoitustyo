package com.example.Harjoitustyo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Käyttäjien tietokanta
@Repository
public interface TaskerRepository extends JpaRepository<Tasker, Long> {

    Tasker findByUsername(String username);
}
