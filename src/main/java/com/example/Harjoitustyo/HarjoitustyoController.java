package com.example.Harjoitustyo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HarjoitustyoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/data")
    public String showAll(Model model) {
        model.addAttribute("notes", this.todoRepository.findAll());
        return "data";
    }
    @GetMapping("/new")
    public String showNewPage() {
        return "/new";
    }
    @PostMapping("/new")
    public String save(@RequestParam String name, @RequestParam int duration, @RequestParam String description) {
        Todo note = new Todo(name, duration, description);
        todoRepository.save(note);
        return "/data";
    }
}
