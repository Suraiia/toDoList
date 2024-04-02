package org.todolist.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.todolist.entities.Task;

@Component
public class TaskValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Task.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "title", "title.empty", "The title must not be empty.");
        //ValidationUtils.rejectIfEmpty(errors, "plannedOn", "plannedOn.empty", "The date must not be empty.");
        //ValidationUtils.rejectIfEmpty(errors, "status", "status.empty", "The status must not be empty.");
    }
}