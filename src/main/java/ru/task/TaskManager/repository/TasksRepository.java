package ru.task.TaskManager.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.task.TaskManager.models.Task;

@Repository
public interface TasksRepository extends CrudRepository<Task, Integer> {
}
