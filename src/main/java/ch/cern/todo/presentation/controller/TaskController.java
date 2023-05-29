package ch.cern.todo.presentation.controller;

import ch.cern.todo.presentation.dto.TaskDTO;
import ch.cern.todo.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/task")
public class TaskController {

    private TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<TaskDTO> create(@RequestBody TaskDTO taskDTO) {
        try {
            return new ResponseEntity<>(service.create(taskDTO), HttpStatus.CREATED);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getTask(id));
        } catch(NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> update(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        try {
            return ResponseEntity.ok(service.update(id, taskDTO));
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskDTO> merge(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        try {
            return ResponseEntity.ok(service.merge(id, taskDTO));
        } catch(NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch(NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
