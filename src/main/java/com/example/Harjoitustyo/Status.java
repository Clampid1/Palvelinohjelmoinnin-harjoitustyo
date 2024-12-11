package com.example.Harjoitustyo;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
// Luokka, joka säilyttää eri tilanne muuttujia.
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Current status must not be blank")
    private String status;
    @OneToMany (mappedBy = "status")
    List<Task> currentStatus = new ArrayList<>();

    public List<Task> getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(List<Task> currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

