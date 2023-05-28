package ch.cern.todo.service;

import ch.cern.todo.data.model.Category;
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
    private CategoryService categoryService;

    public TaskService(TaskMapper mapper, CategoryMapper categoryMapper, TaskRepository repository, CategoryService categoryService) {
        this.mapper = mapper;
        this.categoryMapper = categoryMapper;
        this.repository = repository;
        this.categoryService = categoryService;
    }

    public List<TaskDTO> getAll() {
        return mapper.entityListToDtoList(repository.findAll());
    }

    public TaskDTO create(TaskDTO taskDTO) {
        if(taskDTO.getCategory() == null)
            throw new RuntimeException("Task must have category assigned to it");
        Category category = getCategory(taskDTO.getCategory().getName());
        TaskTodo mapped = mapper.dtoToEntity(taskDTO);
        mapped.setCategory(category);
        TaskTodo created = repository.save(mapped);
        return mapper.entityToDto(created);
    }

    public Category getCategory(String name) {
        Category found = categoryService.getEntityByName(name);
        if(found == null)
            throw new NoSuchElementException("Category with name " + name + "does not exist");
        return found;
    }

    public TaskDTO getTask(Long id) {
        TaskTodo found = repository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Task with id(" + id + ") does not exist")
        );
        return mapper.entityToDto(found);
    }

    public TaskDTO update(Long id, TaskDTO taskUpdate) {
        if(taskUpdate.getCategory() == null)
            throw new RuntimeException("Task must have category assigned to it");
        return repository.findById(id)
                .map(task -> {
                    Category category = getCategory(taskUpdate.getCategory().getName());
                    task.setName(taskUpdate.getName());
                    task.setDescription(taskUpdate.getDescription());
                    task.setDeadline(taskUpdate.getDeadline());
                    task.setCategory(category);
                    return mapper.entityToDto(repository.save(task));
                })
                .orElseGet(() -> {
                    taskUpdate.setId(id);
                    TaskTodo created = repository.save(mapper.dtoToEntity(taskUpdate));
                    return mapper.entityToDto(created);
                });
    }

    public TaskDTO merge(Long id, TaskDTO taskDTO) {
        return repository.findById(id)
                .map( task -> {
                    Category category = taskDTO.getCategory() == null ? null : getCategory(taskDTO.getCategory().getName());
                    TaskTodo mapped = mapper.dtoToEntity(taskDTO);
                    mapped.setCategory(category);
                    task.mergeNotNull(mapper.dtoToEntity(taskDTO));
                    TaskTodo saved = repository.save(task);
                    return mapper.entityToDto(saved);
                })
                .orElseThrow(NoSuchElementException::new);
    }

    public void delete(Long id) {
        TaskTodo found = repository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Task with id(" + id + ") does not exist")
        );
        repository.delete(found);
    }

}
