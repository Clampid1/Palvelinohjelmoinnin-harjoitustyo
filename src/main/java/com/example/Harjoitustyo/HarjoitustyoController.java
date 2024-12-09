package com.example.Harjoitustyo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HarjoitustyoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/data")
    public String showAll(Model model) {
        model.addAttribute("notes", this.todoRepository.findAll());
        return "data";
    }
    @PostMapping("/new")
    public String save(String name, int duration, String description, User _user) {
        Todo note = new Todo(name, duration, description, _user);
        todoRepository.save(note);

        return "redirect:/data";
    }
}
