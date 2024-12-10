package com.example.Harjoitustyo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskerRepository extends JpaRepository<Tasker, Long> {

    Tasker findByUsername(String username);
}
