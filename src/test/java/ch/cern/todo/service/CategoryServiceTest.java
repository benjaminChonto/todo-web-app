package ch.cern.todo.service;

import ch.cern.todo.data.model.Category;
import ch.cern.todo.data.repository.CategoryRepository;
import ch.cern.todo.mapper.CategoryMapper;
import ch.cern.todo.presentation.dto.CategoryDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class CategoryServiceTest {

    @Autowired
    CategoryMapper mapper;
    CategoryRepository repository = mock(CategoryRepository.class);
    CategoryService service;
    static Category category;
    static CategoryDTO categoryDTO;

    @BeforeEach
    void create() {
        service = new CategoryService(mapper, repository);
        category = new Category(44L,"Test Cat", "Test Descr");
        categoryDTO = new CategoryDTO(44L,"Test Cat", "Test Descr");
    }

    @AfterEach
    void breakDown() {
        reset(repository);
    }

    void mockForSuccess() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(category));
    }

    void mockForException() {
        when(repository.findById(anyLong())).thenReturn(Optional.empty());
    }

    @Test
    void getCategory_success() {
        mockForSuccess();
        CategoryDTO result = service.getCategory(44L);
        verify(repository).findById(44L);
        assertEquals(categoryDTO, result);
    }

    @Test
    void getCategory_throw() {
        mockForException();
        assertThrows(NoSuchElementException.class, () -> service.getCategory(11L));
    }

    @Test
    void update_found() {
        mockForSuccess();
        when(repository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        CategoryDTO updateDTO = new CategoryDTO(null, "Override", "Override Description");
        CategoryDTO result = service.update(44L, updateDTO);
        updateDTO.setId(44L);
        verify(repository).findById(44L);
        verify(repository).save(any());
        assertEquals(updateDTO, result);
    }

    @Test
    void update_missing() {
        mockForException();
        when(repository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        CategoryDTO result = service.update(10L, categoryDTO);
        CategoryDTO expected = new CategoryDTO(10L, category.getName(), category.getDescription());
        verify(repository).findById(10L);
        verify(repository).save(any());
        assertEquals(expected, result);
    }

    @Test
    void delete_success() {
        mockForSuccess();
        service.delete(44L);
        verify(repository).findById(44L);
    }

    @Test
    void delete_throw() {
        mockForException();
        assertThrows(NoSuchElementException.class, () -> service.delete(44L));
    }

}