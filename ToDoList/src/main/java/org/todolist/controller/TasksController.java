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
import org.todolist.validator.TaskValidator;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Controller
@RequestMapping(path = "/tasks")
public class TasksController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private final TaskValidator taskValidator;


    @Autowired
    public TasksController(TaskValidator taskValidator) {
        this.taskValidator = taskValidator;
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

        Task task = taskRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("Invalid task Id:" + id));

       /* DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (task.getPlannedOn() != null) {
            String formattedDate = task.getPlannedOn().format(formatter);
            model.addAttribute("formattedPlannedOn", formattedDate);
        }*/

        Iterable<Category> findAllCategories = categoryRepository.findAll();
        model.addAttribute("categories", findAllCategories);
        model.addAttribute("statuses", Arrays.asList(Status.values()));
        model.addAttribute("task", task);
        return "tasks/update";
    }

/*    @PostMapping("/update")
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
    }*/
}
