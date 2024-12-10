package com.example.Harjoitustyo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class HarjoitustyoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/data")
    public String showAll(Model model) {
        List<Todo> todoList = this.todoRepository.findAll();
        List<Todo> incompleteList = todoList.stream()
                .filter(todo -> !todo.isCompleted())
                .toList();

        List<Todo> completedList = todoList.stream()
                .filter(Todo::isCompleted)
                .toList();
        model.addAttribute("todoList", incompleteList);
        model.addAttribute("completedList", completedList);
        return "data";
    }
    @GetMapping("/new")
    public String showNewPage() {
        return "/new";
    }
    @PostMapping("/new")
    public String save(@RequestParam String name, @RequestParam int duration, @RequestParam String description) {
        Todo note = new Todo();
        note.setName(name);
        note.setDuration(duration);
        note.setDescription(description);
        note.setCompleted(false);
        this.todoRepository.save(note);
        return "redirect:/data";
    }

    @PostMapping("/data")
    public String update(@RequestParam Long value) {
        Optional<Todo> task = this.todoRepository.findById(value);
        if (task.isPresent()) {
            Todo todo = task.get();
            todo.setCompleted(true);
            this.todoRepository.save(todo);
        }
        return "redirect:/data";
    }
}
