package ch.cern.todo.service;

import ch.cern.todo.data.model.TaskTodo;
import ch.cern.todo.data.repository.TaskRepository;
import ch.cern.todo.mapper.CategoryMapper;
import ch.cern.todo.mapper.TaskMapper;
import ch.cern.todo.presentation.dto.TaskDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskService {

    private TaskMapper mapper;
    private CategoryMapper categoryMapper;
    private TaskRepository repository;

    public TaskService(TaskMapper mapper, CategoryMapper categoryMapper, TaskRepository repository) {
        this.mapper = mapper;
        this.categoryMapper = categoryMapper;
        this.repository = repository;
    }

    public List<TaskDTO> getAll() {
        return mapper.entityListToDtoList(repository.findAll());
    }

    public TaskDTO create(TaskDTO taskDTO) {
        TaskTodo created = repository.save(mapper.dtoToEntity(taskDTO));
        return mapper.entityToDto(created);
    }

    public TaskDTO getTask(Long id) {
        TaskTodo found = repository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Task with id(" + id + ") does not exist")
        );
        return mapper.entityToDto(found);
    }

    public TaskDTO update(Long id, TaskDTO taskUpdate) {
        return repository.findById(id)
                .map(task -> {
                    task.setName(taskUpdate.getName());
                    task.setDescription(taskUpdate.getDescription());
                    task.setDeadline(taskUpdate.getDeadline());
                    task.setCategory(categoryMapper.dtoToEntity(taskUpdate.getCategory()));
                    return mapper.entityToDto(repository.save(task));
                })
                .orElseGet(() -> {
                    taskUpdate.setId(id);
                    TaskTodo created = repository.save(mapper.dtoToEntity(taskUpdate));
                    return mapper.entityToDto(created);
                });
    }

    public void delete(Long id) {
        TaskTodo found = repository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Task with id(" + id + ") does not exist")
        );
        repository.delete(found);
    }

}
