package ch.cern.todo.mapper;

import ch.cern.todo.data.model.Category;
import ch.cern.todo.data.model.TaskTodo;
import ch.cern.todo.presentation.dto.CategoryDTO;
import ch.cern.todo.presentation.dto.TaskDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskMapperTest {

    @Autowired
    TaskMapper mapper;
    static TaskTodo task;
    static TaskDTO taskDTO;

    @BeforeAll
    static void create() {
        Category category = new Category(42L, "Test Category", "Descr for Test Category");
        CategoryDTO categoryDTO = new CategoryDTO(42L, "Test Category", "Descr for Test Category");
        task = new TaskTodo(12L, "Test Task", "Descr for Test Task", LocalDateTime.of(2023,6,30,12,30), category);
        taskDTO = new TaskDTO(12L, "Test Task", "Descr for Test Task", LocalDateTime.of(2023,6,30,12,30), categoryDTO);
    }

    @Test
    void dtoToEntity() {
        TaskTodo mapped = mapper.dtoToEntity(taskDTO);
        assertEquals(task, mapped);
    }

    @Test
    void entityToDto() {
        TaskDTO mapped = mapper.entityToDto(task);
        assertEquals(taskDTO, mapped);
    }

    @Test
    void dtoListToEntityList() {
        List<TaskTodo> mappedList = mapper.dtoListToEntityList(List.of(taskDTO));
        assertEquals(List.of(task), mappedList);
    }

    @Test
    void entityListToDtoList() {
        List<TaskDTO> mappedList = mapper.entityListToDtoList(List.of(task));
        assertEquals(List.of(taskDTO), mappedList);
    }
}