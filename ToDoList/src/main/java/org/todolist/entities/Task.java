package org.todolist.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Name may not be empty")
    private String title;

    private LocalDate createdAt = LocalDate.now();

    @NotNull(message = "Planned date may not be empty")
    private LocalDate plannedOn;

    @NotNull(message = "Status may not be empty")
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull(message = "Category may not be empty")
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getPlannedOn() {
        return plannedOn;
    }

    public void setPlannedOn(LocalDate plannedOn) {
        this.plannedOn = plannedOn;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    /*@PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDate.now();
        }
    }*/


}
