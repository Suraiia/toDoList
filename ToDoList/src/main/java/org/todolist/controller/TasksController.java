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
import org.todolist.repositories.CategoryRepository;
import org.todolist.repositories.TaskRepository;
import org.todolist.validator.TaskValidator;

import java.util.Arrays;

@Controller
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

    @GetMapping("/allTasks")
    public String showAllTasks(Model model) {
        Iterable<Task> findAllTasks = taskRepository.findAll();
        model.addAttribute("tasks", findAllTasks);

        Iterable<Category> findAllCategories = categoryRepository.findAll();
        model.addAttribute("categories", findAllCategories);
        model.addAttribute("statuses", Arrays.asList(Status.values()));
        return "allTasks";
    }

    @GetMapping("/createTask")
    public String showCreateTaskForm(Model model) {
        Category category = new Category();
        Iterable<Category> findAllCategories = categoryRepository.findAll();
        model.addAttribute("categories", findAllCategories);
        Task task = new Task();
        model.addAttribute("statuses", Arrays.asList(Status.values()));
        model.addAttribute("task", task);
        return "createTask";
    }

    @PostMapping("/createTask")
    public String saveTask(@Validated Task task, BindingResult result, Model model) {
        taskValidator.validate(task, result);
        Iterable<Category> findAllCategories = categoryRepository.findAll();
        model.addAttribute("categories", findAllCategories);
        model.addAttribute("statuses", Arrays.asList(Status.values()));
        if (result.hasErrors()) {
            return "createTask";
        }
         taskRepository.save(task);
        return "redirect:/allTasks";
    }
}
