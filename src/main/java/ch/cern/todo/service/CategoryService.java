package ch.cern.todo.service;

import ch.cern.todo.data.model.Category;
import ch.cern.todo.data.repository.CategoryRepository;
import ch.cern.todo.mapper.CategoryMapper;
import ch.cern.todo.presentation.dto.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CategoryService {

    private CategoryMapper mapper;
    private CategoryRepository repository;

    public CategoryService(CategoryMapper mapper, CategoryRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public List<CategoryDTO> getAll() {
        return mapper.entityListToDtoList(repository.findAll());
    }

    public CategoryDTO create(CategoryDTO categoryDTO) {
        Category created = repository.save(mapper.dtoToEntity(categoryDTO));
        return mapper.entityToDto(created);
    }

    public CategoryDTO getCategory(Long id) {
        Category found = repository.findById(id).orElseThrow(
                ()-> new NoSuchElementException("Category with id(" + id + ") does not exist"));
        return mapper.entityToDto(found);
    }

    public CategoryDTO swap(Long id, CategoryDTO catUpdate) {
        return repository.findById(id)
                .map(cat -> {
                    cat.setId(catUpdate.getId());
                    cat.setName(catUpdate.getName());
                    cat.setDescription(catUpdate.getDescription());
                    return mapper.entityToDto(repository.save(cat));
                })
                .orElseGet(() -> {
                    catUpdate.setId(id);
                    Category created = repository.save(mapper.dtoToEntity(catUpdate));
                    return mapper.entityToDto(created);
                });
    }

    public void delete(Long id) {
        Category found = repository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Category with id(" + id + ") does not exist")
        );
        repository.delete(found);
    }

}
