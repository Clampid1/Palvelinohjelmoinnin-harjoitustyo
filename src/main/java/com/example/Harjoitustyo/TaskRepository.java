package com.example.Harjoitustyo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// Tehtävien tietokanta.
public interface TaskRepository extends JpaRepository<Task, Long> {
}
