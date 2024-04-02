package org.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.todolist.entities.Task;
import org.todolist.repositories.TasksRepository;

import java.time.LocalDate;
import java.util.List;


@Controller
public class ToDoListController {
    @Autowired
    private TasksRepository tasksRepository;
    @GetMapping("/mainPage")
    public String getTasks(Model model){

        LocalDate today = LocalDate.now();
        List<Task> tasksForToday = tasksRepository.findTasksBySpecificDate(today);
        model.addAttribute("tasks", tasksForToday);

        return "mainPage";
    }


}
