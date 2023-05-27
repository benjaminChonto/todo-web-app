package ch.cern.todo.mapper;

import ch.cern.todo.data.model.TaskCategory;
import ch.cern.todo.presentation.dto.TaskCategoryDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskCategoryMapperTest {

    @Autowired
    TaskCategoryMapper mapper;
    static TaskCategory taskCategory;
    static TaskCategoryDTO taskCategoryDTO;

    @BeforeAll
    static void create() {
        taskCategory = new TaskCategory(42L, "Test Category", "Test Category Description");
        taskCategoryDTO = new TaskCategoryDTO(42L, "Test Category", "Test Category Description");
    }

    @Test
    void dtoToEntity() {
        TaskCategory mapped = mapper.dtoToEntity(taskCategoryDTO);
        assertEquals(taskCategory, mapped);
    }

    @Test
    void entityToDto() {
        TaskCategoryDTO mapped = mapper.entityToDto(taskCategory);
        assertEquals(taskCategoryDTO, mapped);
    }

    @Test
    void dtoListToEntityList() {
        List<TaskCategory> mappedList = mapper.dtoListToEntityList(List.of(taskCategoryDTO));
        assertEquals(List.of(taskCategory), mappedList);
    }

    @Test
    void entityListToDtoList() {
        List<TaskCategoryDTO> mappedList = mapper.entityListToDtoList(List.of(taskCategory));
        assertEquals(List.of(taskCategoryDTO), mappedList);
    }

}