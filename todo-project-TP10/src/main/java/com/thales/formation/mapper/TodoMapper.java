package com.thales.formation.mapper;

import com.thales.formation.dto.TodoDto;
import com.thales.formation.model.Todo;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
@DecoratedWith(TodoMapperDecorator.class)
public interface TodoMapper {

  // CREATE
  @Mapping(target = "status", ignore = true)
  Todo dtoToModel(TodoDto todoDto);

  // UPDATE
  @Mapping(target = "status", ignore = true)
  void dtoToModel(TodoDto todoDto, @MappingTarget Todo todo);

  TodoDto modelToDto(Todo todo);

  List<TodoDto> modelToDto(Iterable<Todo> todo);
}
