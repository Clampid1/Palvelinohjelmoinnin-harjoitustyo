package com.example.Harjoitustyo;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.stereotype.Component;

@Entity

@Data
@Component
@EqualsAndHashCode(callSuper = true)
public class Todo extends AbstractPersistable<Long> {

    private String name;
    private int duration;
    private String description;
    private boolean completed;

    public Todo() {

    }

    public Todo(String name, int duration, String description) {
        this.name = name;
        this.duration = duration;
        this.description = description;
        this.completed = false;
    }
}
