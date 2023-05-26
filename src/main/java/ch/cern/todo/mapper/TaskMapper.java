package ch.cern.todo.mapper;


import ch.cern.todo.data.model.TaskTodo;
import ch.cern.todo.presentation.dto.TaskDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = TaskCategoryMapper.class)
public interface TaskMapper {
    TaskTodo dtoToEntity(TaskDTO dto);
    TaskDTO entityToDto(TaskTodo entity);
    List<TaskTodo> dtoListToEntityList(List<TaskDTO> dtoList);
    List<TaskDTO> entityListToDtoList(List<TaskTodo> entityList);
}
