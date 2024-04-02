package org.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.todolist.entities.Category;
import org.todolist.entities.Status;
import org.todolist.entities.Task;
import org.todolist.repositories.CategoriesRepository;
import org.todolist.repositories.TasksRepository;
import org.todolist.validator.TaskValidator;

import java.util.Arrays;
import java.util.List;

@Controller
public class TasksController {

    @Autowired
    private TasksRepository tasksRepository;
    private CategoriesRepository categoriesRepository;

    private final TaskValidator taskValidator;

    @Autowired
    public TasksController(TaskValidator taskValidator) {
        this.taskValidator = taskValidator;
    }

    @GetMapping("/allTasks")
    public String showAllTasks(Model model) {
        Iterable<Task> findAllTasks = tasksRepository.findAll();
        model.addAttribute("tasks", findAllTasks);
        return "allTasks";
    }

    @GetMapping("/createTask")
    public String showCreateTaskForm(Model model) {
        Task task = new Task();
        model.addAttribute("categories", List.of("Work", "Personal", "Shopping"));
        model.addAttribute("statuses", Arrays.asList(Status.values()));
        model.addAttribute("task", task);
        return "createTask";
    }

    @PostMapping("/createTask")
    public String saveTask(@Validated Task task, BindingResult result, Model model) {
        taskValidator.validate(task, result);
        Iterable<Category> categories = categoriesRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("priorities", Status.values());
        if (result.hasErrors()) {
            return "createTask";
        }
         tasksRepository.save(task);
        return "redirect:/allTasks";
    }
}
