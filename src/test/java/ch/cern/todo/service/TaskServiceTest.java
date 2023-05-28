package ch.cern.todo.service;

import ch.cern.todo.data.model.Category;
import ch.cern.todo.data.model.TaskTodo;
import ch.cern.todo.data.repository.TaskRepository;
import ch.cern.todo.mapper.CategoryMapper;
import ch.cern.todo.mapper.TaskMapper;
import ch.cern.todo.presentation.dto.CategoryDTO;
import ch.cern.todo.presentation.dto.TaskDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class TaskServiceTest {

    @Autowired
    TaskMapper mapper;
    @Autowired
    CategoryMapper categoryMapper;
    TaskRepository repository = mock(TaskRepository.class);
    TaskService service;
    static TaskTodo task;
    static TaskDTO taskDTO;

    @BeforeEach
    void create() {
        service = new TaskService(mapper, categoryMapper, repository);
        Category cat = new Category(44L, "Test Cat", "Test Cat Descr");
        CategoryDTO catDTO = new CategoryDTO(44L, "Test Cat", "Test Cat Descr");
        task = new TaskTodo(24L, "Test Task", "Test Task Descr", LocalDateTime.of(2023,6,30,12,0), cat);
        taskDTO = new TaskDTO(24L, "Test Task", "Test Task Descr", LocalDateTime.of(2023,6,30,12,0), catDTO);
    }

    @AfterEach
    void breakDown() {
        reset(repository);
    }

    void mockForSuccess() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(task));
    }

    void mockForException() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
    }

    @Test
    void getTask_success() {
        mockForSuccess();
        TaskDTO result = service.getTask(24L);
        verify(repository).findById(24L);
        assertEquals(taskDTO, result);
    }

    @Test
    void getTask_throw() {
        mockForException();
        assertThrows(NoSuchElementException.class, () -> service.getTask(24L));
    }

    @Test
    void update_found() {
        mockForSuccess();
        when(repository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        TaskDTO updateDTO = new TaskDTO(null, "Override", "Override Descr", LocalDateTime.now(), null);
        TaskDTO result = service.update(24L, updateDTO);
        updateDTO.setId(24L);
        verify(repository).findById(24L);
        verify(repository).save(any());
        assertEquals(updateDTO, result);
    }

    @Test
    void update_missing() {
        mockForException();
        when(repository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        TaskDTO result = service.update(10L, taskDTO);
        TaskDTO expected = new TaskDTO(10L, taskDTO.getName(), taskDTO.getDescription(), taskDTO.getDeadline(), taskDTO.getCategory());
        verify(repository).findById(10L);
        verify(repository).save(any());
        assertEquals(expected, result);
    }

    @Test
    void delete_success() {
        mockForSuccess();
        service.delete(10L);
        verify(repository).findById(10L);
        verify(repository).delete(any());
    }

    @Test
    void delete_throw() {
        mockForException();
        assertThrows(NoSuchElementException.class, () -> service.delete(10L));
    }
}