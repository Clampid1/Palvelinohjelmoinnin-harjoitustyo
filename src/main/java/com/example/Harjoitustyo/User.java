package com.example.Harjoitustyo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.stereotype.Component;

import jakarta.persistence.OneToMany;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractPersistable<Long> {
    private String username;
    private String password;
    @OneToMany(mappedBy = "_user")
    private List<Todo> todoList = new ArrayList<>();
}
