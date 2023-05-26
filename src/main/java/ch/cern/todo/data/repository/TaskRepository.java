package ch.cern.todo.data.repository;

import ch.cern.todo.data.model.TaskTodo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskTodo, Long> {
}
