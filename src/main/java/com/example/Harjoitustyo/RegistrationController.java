package com.example.Harjoitustyo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    private TaskerRepository taskerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register/user")
    public Tasker createTasker(@RequestBody Tasker tasker) {
        tasker.setPassword(passwordEncoder.encode(tasker.getPassword()));
        return taskerRepository.save(tasker);
    }

}
