package com.example.Harjoitustyo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
@Component
@NoArgsConstructor
@AllArgsConstructor
// Tehtävä luokka
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Name must not be empty")
    private String name;
    @NotBlank(message = "Time in minutes, must not be empty")
    private int duration;
    @NotBlank(message = "Has to have at least 1 whitespace character")
    @Size(min = 1, max = 500, message = "description must be at most 500 characters, and has at least one character")
    private String description;
    @ManyToOne
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
