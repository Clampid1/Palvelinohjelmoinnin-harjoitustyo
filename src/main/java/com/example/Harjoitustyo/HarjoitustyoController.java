package com.example.Harjoitustyo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class HarjoitustyoController {

    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/data")
    public String showAll(Model model) {
        List<Status> statusList = this.statusRepository.findAll();

        List<Todo> todoList = this.todoRepository.findAll();
        List<Todo> waitingList = todoList.stream()
                .filter(todo -> todo.getStatus() == statusList.get(0))
                .toList();
        List<Todo> doingList = todoList.stream()
                .filter(todo -> todo.getStatus() == statusList.get(1))
                .toList();
        List<Todo> reviewList = todoList.stream()
                .filter(todo -> todo.getStatus() == statusList.get(2))
                .toList();
        List<Todo> completedList = todoList.stream()
                .filter(todo -> todo.getStatus() == statusList.get(3))
                .toList();

        model.addAttribute("todoList", todoList);
        model.addAttribute("waitingList", waitingList);
        model.addAttribute("doingList", doingList);
        model.addAttribute("reviewList", reviewList);
        model.addAttribute("completeList", completedList);
        return "data";
    }
    @GetMapping("/new")
    public String showAddPage(Model model) {
        List<Status> statusList = this.statusRepository.findAll();
        model.addAttribute("statusList", statusList);
        return "/new";
    }
    @PostMapping("/new")
    public String save(@RequestParam String name, @RequestParam int duration, @RequestParam String description,
    @RequestParam(defaultValue = "1") Long id) {
        Optional<Status> status1 = this.statusRepository.findById(id);
        if (status1.isPresent()) {
            Status status = status1.get();
            Todo note = new Todo();
            note.setName(name);
            note.setDuration(duration);
            note.setDescription(description);
            note.setStatus(status);
            this.todoRepository.save(note);
            return "redirect:/data";
        }
        return "redirect:/data";
    }

    @PostMapping("/move_left")
    public String move_left(@RequestParam Long left) {
        List<Status> statusList = this.statusRepository.findAll();
        Optional<Todo> task_left = this.todoRepository.findById(left);
        if (task_left.isPresent()) {
            Todo todo = task_left.get();
            Status statusMsg = todo.getStatus();
            if (statusMsg == statusList.get(0)) {
                return "redirect:/data";
            }
            if (statusMsg == statusList.get(1)) {
                Status newStatus = statusList.get(0);
                todo.setStatus(newStatus);
                this.todoRepository.save(todo);
            }
            if (statusMsg == statusList.get(2)) {
                Status newStatus = statusList.get(1);
                todo.setStatus(newStatus);
                this.todoRepository.save(todo);
            }
            if (statusMsg == statusList.get(3)) {
                Status newStatus = statusList.get(2);
                todo.setStatus(newStatus);
                this.todoRepository.save(todo);
            }
        }
        return "redirect:/data";
    }
        @PostMapping("/move_right")
        public String move_right(@RequestParam Long right) {
        List<Status> statusList = this.statusRepository.findAll();
        Optional<Todo> task_right = this.todoRepository.findById(right);
        if (task_right.isPresent()) {
            Todo todo = task_right.get();
            Status statusMsg = todo.getStatus();
            if (statusMsg == statusList.get(0)) {
                Status newStatus = statusList.get(1);
                todo.setStatus(newStatus);
                this.todoRepository.save(todo);
            }
            if (statusMsg == statusList.get(1)) {
                Status newStatus = statusList.get(2);
                todo.setStatus(newStatus);
                this.todoRepository.save(todo);
            }
            if (statusMsg == statusList.get(2)) {
                Status newStatus = statusList.get(3);
                todo.setStatus(newStatus);
                this.todoRepository.save(todo);
            }
            if (statusMsg == statusList.get(3)) {
                return "redirect:/data";
            }
        }
        return "redirect:/data";
    }
    @GetMapping("/data/{id}")
    public String showOne(@PathVariable Long id, Model model) {
        Todo task = this.todoRepository.getReferenceById(id);
        String statusMsg = task.getStatus().toString();
        Status status = this.statusRepository.findByStatus(statusMsg);
        Long statusId = status.getId();
        if (statusId == 1) {
            List<Todo> waitingList = new ArrayList<>();
            model.addAttribute("waitingList", waitingList);
            return "/data";
        }
        if (statusId == 2) {
            List<Todo> doingList = new ArrayList<>();
            model.addAttribute("doingList", doingList);
            return "/data";
        }
        if (statusId == 3) {
            List<Todo> reviewList = new ArrayList<>();
            model.addAttribute("reviewList", reviewList);
            return "/data";
        }
        if (statusId == 4) {
            List<Todo> completedList = new ArrayList<>();
            model.addAttribute("completeList", completedList);
            return "/data";
        }
        return "/data";
    }
}
