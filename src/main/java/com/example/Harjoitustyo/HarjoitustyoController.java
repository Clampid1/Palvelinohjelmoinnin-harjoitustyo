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
    private TaskRepository taskRepository;

    //Näyttää data-ikkunan
    @GetMapping("/data")
    public String showAll(Model model) {
        // Haetaan tilanne muuttujat valmiiksi
        List<Status> statusList = this.statusRepository.findAll();
        // Haetaan tehtävät valmiiksi
        List<Task> taskList = this.taskRepository.findAll();
        // Jaotellaan tehtävälista sen mukaan, mikä on tehtävän status
        List<Task> waitingList = taskList.stream()
                .filter(todo -> todo.getStatus() == statusList.get(0))
                .toList();
        List<Task> doingList = taskList.stream()
                .filter(todo -> todo.getStatus() == statusList.get(1))
                .toList();
        List<Task> reviewList = taskList.stream()
                .filter(todo -> todo.getStatus() == statusList.get(2))
                .toList();
        List<Task> completedList = taskList.stream()
                .filter(todo -> todo.getStatus() == statusList.get(3))
                .toList();
        // Lisätään listat näkymään
        model.addAttribute("todoList", taskList);
        model.addAttribute("waitingList", waitingList);
        model.addAttribute("doingList", doingList);
        model.addAttribute("reviewList", reviewList);
        model.addAttribute("completeList", completedList);
        return "data";
    }

    // Näyttää tehtävän lisäys sivuston
    @GetMapping("/new")
    public String showAddPage() {
        return "/new";
    }

    // Uuden tehtävän lisäys metodi
    @PostMapping("/new")
    public String save(@RequestParam String name, @RequestParam int duration, @RequestParam String description,
    @RequestParam(defaultValue = "1") Long id) {
        // Tarkastetaan onko status id NULL
        Optional<Status> status1 = this.statusRepository.findById(id);
        if (status1.isPresent()) {
            // Koska ei ollut NULL, voidaan asettaa arvot ja tallentaa
            Status status = status1.get();
            Task note = new Task();
            note.setName(name);
            note.setDuration(duration);
            note.setDescription(description);
            note.setStatus(status);
            this.taskRepository.save(note);
            return "redirect:/data";
        }
        return "redirect:/data";
    }

    // Siirtää tehtävää Sprint boardilla yhden pykälän vasempaan
    @PostMapping("/move_left")
    public String move_left(@RequestParam Long left) {
        // Kerätään tilanne muuttujat valmiiksi
        List<Status> statusList = this.statusRepository.findAll();
        // Tarkastetaan onko valittu task NULL
        Optional<Task> task_left = this.taskRepository.findById(left);
        if (task_left.isPresent()) {
            // Koska ei ollut NULL, voidaan tarkastaa, mikä status viesti vastaa otsikkoa
            // ja siirtää task yhden otsikon vasempaan get(statusMsg -1)
            Task todo = task_left.get();
            Status statusMsg = todo.getStatus();
            // Jotta pysytään rajoissa, ei voida mennä 0 pienemmäksi.
            if (statusMsg == statusList.get(0)) {
                return "redirect:/data";
            }
            if (statusMsg == statusList.get(1)) {
                Status newStatus = statusList.get(0);
                todo.setStatus(newStatus);
                this.taskRepository.save(todo);
            }
            if (statusMsg == statusList.get(2)) {
                Status newStatus = statusList.get(1);
                todo.setStatus(newStatus);
                this.taskRepository.save(todo);
            }
            if (statusMsg == statusList.get(3)) {
                Status newStatus = statusList.get(2);
                todo.setStatus(newStatus);
                this.taskRepository.save(todo);
            }
        }
        return "redirect:/data";
    }

        // Muuten sama kuin edellinen, mutta liikutaan oikealle ja 4 on suurin.
        @PostMapping("/move_right")
        public String move_right(@RequestParam Long right) {
        List<Status> statusList = this.statusRepository.findAll();
        Optional<Task> task_right = this.taskRepository.findById(right);
        if (task_right.isPresent()) {
            Task todo = task_right.get();
            Status statusMsg = todo.getStatus();
            if (statusMsg == statusList.get(0)) {
                Status newStatus = statusList.get(1);
                todo.setStatus(newStatus);
                this.taskRepository.save(todo);
            }
            if (statusMsg == statusList.get(1)) {
                Status newStatus = statusList.get(2);
                todo.setStatus(newStatus);
                this.taskRepository.save(todo);
            }
            if (statusMsg == statusList.get(2)) {
                Status newStatus = statusList.get(3);
                todo.setStatus(newStatus);
                this.taskRepository.save(todo);
            }
            if (statusMsg == statusList.get(3)) {
                return "redirect:/data";
            }
        }
        return "redirect:/data";
    }

    // Voidaan tulostaa valittu task data sivun pohjalla.
    @GetMapping("/data/{id}")
    public String showOne(@PathVariable Long id, Model model) {
        Task task = this.taskRepository.getReferenceById(id);
        String statusMsg = task.getStatus().toString();
        Status status = this.statusRepository.findByStatus(statusMsg);
        Long statusId = status.getId();
        if (statusId == 1) {
            List<Task> waitingList = new ArrayList<>();
            model.addAttribute("waitingList", waitingList);
            return "/data";
        }
        if (statusId == 2) {
            List<Task> doingList = new ArrayList<>();
            model.addAttribute("doingList", doingList);
            return "/data";
        }
        if (statusId == 3) {
            List<Task> reviewList = new ArrayList<>();
            model.addAttribute("reviewList", reviewList);
            return "/data";
        }
        if (statusId == 4) {
            List<Task> completedList = new ArrayList<>();
            model.addAttribute("completeList", completedList);
            return "/data";
        }
        return "/data";
    }
}
