package ch.cern.todo.presentation.controller;

import ch.cern.todo.presentation.dto.TaskDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/api/task")
public class TaskController {

    @GetMapping
    public ResponseEntity<TaskDTO> getAll() {
        return null;
    }

    @PostMapping
    public ResponseEntity<TaskDTO> create() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable Long id) {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> update(@PathVariable Long id) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskDTO> delete(@PathVariable Long id) {
        return null;
    }

}
