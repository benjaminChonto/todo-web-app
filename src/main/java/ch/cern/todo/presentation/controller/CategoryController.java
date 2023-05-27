package ch.cern.todo.presentation.controller;

import ch.cern.todo.presentation.dto.CategoryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/api/category")
public class CategoryController {

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAll() {
        return null;
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> create() {
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable Long id) {
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable Long id) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryDTO> delete(@PathVariable Long id) {
        return null;
    }

}
