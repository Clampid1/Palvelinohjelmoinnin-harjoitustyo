package com.example.Harjoitustyo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Tilanne muuttujien tietokanta.
@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

    Status findByStatus(String status);
}
