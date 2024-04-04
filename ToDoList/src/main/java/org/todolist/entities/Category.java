package org.todolist.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @Column(length = 7)
    private String color;

    @OneToMany(mappedBy = "category")
    private List<Task> tasks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }


    /*output of the category in sout in the TaskController:  @GetMapping("/createTask")
     for debugging purposes: org.todolist.entities.Category@21a3dda9
    ->Java is showing the default toString() representation of the Category objects,
    which is essentially the class name followed by the @ symbol
    and the hashcode of the object.This is normal behavior when the toString() method
    isn't overridden in the Category class like so:
     */
    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

}