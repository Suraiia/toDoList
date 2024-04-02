package org.todolist.repositories;

import org.springframework.data.repository.CrudRepository;
import org.todolist.entities.Category;

public interface CategoriesRepository extends CrudRepository<Category, Integer> {
}
