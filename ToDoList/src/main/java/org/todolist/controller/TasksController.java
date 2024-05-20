package org.todolist.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.todolist.entities.Category;
import org.todolist.entities.Status;
import org.todolist.entities.Task;
import org.todolist.repositories.CategoryRepository;
import org.todolist.repositories.TaskRepository;
import org.todolist.service.TaskService;
import org.todolist.validator.TaskValidator;

import java.util.Arrays;

@Controller
@RequestMapping(path = "/tasks")
public class TasksController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private final TaskValidator taskValidator;
    private final TaskService taskService;

    @Autowired
    public TasksController(TaskValidator taskValidator, TaskService taskService) {
        this.taskValidator = taskValidator;
        this.taskService = taskService;
    }

    @GetMapping
    public String index(Model model) {
        Iterable<Task> findAllTasks = taskRepository.findAll();
        model.addAttribute("tasks", findAllTasks);
        Iterable<Category> findAllCategories = categoryRepository.findAll();
        model.addAttribute("categories", findAllCategories);
        model.addAttribute("statuses", Arrays.asList(Status.values()));
        return "tasks/index";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        Task task = new Task();
        Iterable<Category> findAllCategories = categoryRepository.findAll();
        model.addAttribute("categories", findAllCategories);
        model.addAttribute("statuses", Arrays.asList(Status.values()));
        model.addAttribute("task", task);
        return "tasks/create";
    }

    @PostMapping("/create")
    public String createTask(@Valid Task task, BindingResult result, Model model) {
        taskValidator.validate(task, result);
        if (task.getPlannedOn() != null && !taskService.isPlannedDateValid(task.getPlannedOn())) {
            result.rejectValue("plannedOn", "error.task", "Planned date cannot be in the past");
        }
        Iterable<Category> findAllCategories = categoryRepository.findAll();
        model.addAttribute("categories", findAllCategories);
        model.addAttribute("statuses", Arrays.asList(Status.values()));
        if (result.hasErrors()) {
            return "tasks/create";
        }
        taskRepository.save(task);
        return "redirect:/tasks";
    }

    @GetMapping("/update/{id}")
    public String updateForm(Model model, @PathVariable("id") Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid task Id:" + id));
        Iterable<Category> findAllCategories = categoryRepository.findAll();
        model.addAttribute("categories", findAllCategories);
        model.addAttribute("statuses", Arrays.asList(Status.values()));
        model.addAttribute("task", task);
        return "tasks/update";
    }

    /*
    @PostMapping("/update")
    public String updateTask(@Valid Task task, BindingResult result, Model model) {
        taskValidator.validate(task, result);
        Iterable<Category> findAllCategories = categoryRepository.findAll();
        model.addAttribute("categories", findAllCategories);
        model.addAttribute("statuses", Arrays.asList(Status.values()));
        if (result.hasErrors()) {
            return "tasks/edit";
        }
        taskRepository.save(task);
        return "redirect:/tasks";
    }
    */
}
