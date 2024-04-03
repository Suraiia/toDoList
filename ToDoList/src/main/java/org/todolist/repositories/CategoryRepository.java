package org.todolist.repositories;

import org.springframework.data.repository.CrudRepository;
import org.todolist.entities.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
}
