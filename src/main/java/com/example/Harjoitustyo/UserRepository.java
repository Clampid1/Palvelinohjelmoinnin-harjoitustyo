package com.example.Harjoitustyo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Tasker, Long> {

    Tasker findByUsername(String username);
}
