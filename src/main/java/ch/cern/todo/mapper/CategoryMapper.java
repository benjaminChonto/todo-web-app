package ch.cern.todo.mapper;

import ch.cern.todo.data.model.Category;
import ch.cern.todo.presentation.dto.CategoryDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category dtoToEntity(CategoryDTO dto);
    CategoryDTO entityToDto(Category entity);
    List<Category> dtoListToEntityList(List<CategoryDTO> dtoList);
    List<CategoryDTO> entityListToDtoList(List<Category> entityList);
}
