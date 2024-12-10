package com.example.Harjoitustyo;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.stereotype.Component;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@EqualsAndHashCode(callSuper = true)
public class Tasker extends AbstractPersistable<Long> {
    private String username;
    private String password;
}
