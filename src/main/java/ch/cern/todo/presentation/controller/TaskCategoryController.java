package ch.cern.todo.presentation.controller;

import ch.cern.todo.presentation.dto.TaskCategoryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/category")
public class TaskCategoryController {

    @GetMapping
    public ResponseEntity<List<TaskCategoryDTO>> getAll() {
        return null;
    }

    @PostMapping
    public ResponseEntity<TaskCategoryDTO> create() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskCategoryDTO> getCategory(@PathVariable Long id) {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskCategoryDTO> update(@PathVariable Long id) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskCategoryDTO> delete(@PathVariable Long id) {
        return null;
    }

}
