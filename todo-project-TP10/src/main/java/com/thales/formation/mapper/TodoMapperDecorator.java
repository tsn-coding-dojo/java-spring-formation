package com.thales.formation.mapper;

import com.thales.formation.dto.TodoDto;
import com.thales.formation.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class TodoMapperDecorator implements TodoMapper {

  @Autowired
  @Qualifier("delegate")
  private TodoMapper delegate;

  @Override
  public Todo dtoToModel(TodoDto todoDto) {
    Todo todo = delegate.dtoToModel(todoDto);
    if (todo.getName() != null) {
      todo.setName(todo.getName().toUpperCase());
    }
    return todo;
  }

}
