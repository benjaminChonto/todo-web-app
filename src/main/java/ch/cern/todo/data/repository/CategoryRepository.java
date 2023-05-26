package ch.cern.todo.data.repository;

import ch.cern.todo.data.model.TaskCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<TaskCategory,Long> {
}
