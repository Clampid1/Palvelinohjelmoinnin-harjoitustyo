package com.example.Harjoitustyo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Rest_Controller {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskerRepository taskerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Uuden käyttäjän lisääminen JSON-oliona.
    @PostMapping("/register/user")
    public Tasker createTasker(@RequestBody Tasker tasker) {
        tasker.setPassword(passwordEncoder.encode(tasker.getPassword()));
        return taskerRepository.save(tasker);
    }

    // Voidaan hakea Sprint boardin sisältö JSON-oliona.
    @GetMapping("/rest/data")
    public List<Task> showAll() {
        return this.taskRepository.findAll();
    }

    // Voidaan poistaa yksittäinen tehtävä.
    @DeleteMapping("/rest/data/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }

}
