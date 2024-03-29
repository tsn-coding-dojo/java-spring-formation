package com.thales.formation.mapper;

import java.util.List;

import org.mapstruct.DecoratedWith;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.thales.formation.dto.TodoDto;
import com.thales.formation.model.Todo;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
@DecoratedWith(TodoMapperDecorator.class)
public interface TodoMapper {

  // CREATE
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "status", ignore = true)
  Todo dtoToModel(TodoDto todoDto);

  // UPDATE
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "status", ignore = true)
  void dtoToModel(TodoDto todoDto, @MappingTarget Todo todo);

  TodoDto modelToDto(Todo todo);

  List<TodoDto> modelToDto(Iterable<Todo> todo);

}
