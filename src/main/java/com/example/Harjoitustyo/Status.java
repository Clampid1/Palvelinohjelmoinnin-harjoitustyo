package com.example.Harjoitustyo;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Status {

    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "Current status must not be blank")
    private String status;
    @OneToMany (mappedBy = "status")
    List<Todo> currentStatus = new ArrayList<>();

    public List<Todo> getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(List<Todo> currentStatus) {
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

