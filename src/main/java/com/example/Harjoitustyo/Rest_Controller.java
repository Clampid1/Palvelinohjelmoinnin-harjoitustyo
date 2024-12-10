package com.example.Harjoitustyo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Rest_Controller {

    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private TaskerRepository taskerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register/user")
    public Tasker createTasker(@RequestBody Tasker tasker) {
        tasker.setPassword(passwordEncoder.encode(tasker.getPassword()));
        return taskerRepository.save(tasker);
    }
    @GetMapping("/rest/data")
    public List<Todo> showAll() {
        return this.todoRepository.findAll();
    }
    @DeleteMapping("/rest/data/{id}")
    public void deleteTask(@PathVariable Long id) {
        todoRepository.deleteById(id);
    }

}
