package org.todolist.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.todolist.entities.Task;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

    @Query("SELECT t FROM Task t WHERE t.plannedOn = :specificDate")
    List<Task> findTasksBySpecificDate(@Param("specificDate") LocalDate specificDate);

    Optional<Task> findById(Long taskId);


}




