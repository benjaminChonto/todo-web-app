package ch.cern.todo.mapper;

import ch.cern.todo.data.model.TaskCategory;
import ch.cern.todo.presentation.dto.TaskCategoryDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskCategoryMapper {
    TaskCategory dtoToEntity(TaskCategoryDTO dto);
    TaskCategoryDTO entityToDto(TaskCategory entity);
    List<TaskCategory> dtoListToEntityList(List<TaskCategoryDTO> dtoList);
    List<TaskCategoryDTO> entityListToDtoList(List<TaskCategory> entityList);
}
