package org.todolist.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String name;

    @Column(length = 7)
    private String color;

    //TODO change to list of tasks
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

    public String getHexValue() {
        return color;
    }

    public void setHexValue(String hexValue) {
        this.color = hexValue;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
