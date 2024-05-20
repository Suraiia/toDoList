package org.todolist.service;

import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class TaskService {
    public boolean isPlannedDateValid(LocalDate plannedOn) {
        return !plannedOn.isBefore(LocalDate.now());
    }

    }


