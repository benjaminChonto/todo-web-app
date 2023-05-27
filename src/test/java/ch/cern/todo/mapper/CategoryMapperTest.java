package ch.cern.todo.mapper;

import ch.cern.todo.data.model.Category;
import ch.cern.todo.presentation.dto.CategoryDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryMapperTest {

    @Autowired
    CategoryMapper mapper;
    static Category category;
    static CategoryDTO categoryDTO;

    @BeforeAll
    static void create() {
        category = new Category(42L, "Test Category", "Test Category Description");
        categoryDTO = new CategoryDTO(42L, "Test Category", "Test Category Description");
    }

    @Test
    void dtoToEntity() {
        Category mapped = mapper.dtoToEntity(categoryDTO);
        assertEquals(category, mapped);
    }

    @Test
    void entityToDto() {
        CategoryDTO mapped = mapper.entityToDto(category);
        assertEquals(categoryDTO, mapped);
    }

    @Test
    void dtoListToEntityList() {
        List<Category> mappedList = mapper.dtoListToEntityList(List.of(categoryDTO));
        assertEquals(List.of(category), mappedList);
    }

    @Test
    void entityListToDtoList() {
        List<CategoryDTO> mappedList = mapper.entityListToDtoList(List.of(category));
        assertEquals(List.of(categoryDTO), mappedList);
    }

}