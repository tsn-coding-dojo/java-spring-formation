
package com.thales.formation.controller;

import com.thales.formation.dto.TodoDto;
import com.thales.formation.service.TodoService;
import com.thales.formation.validator.group.TodoDtoValidationOnUpdate;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

  private final TodoService todoService;

  public TodoController(TodoService todoService) {
    super();
    this.todoService = todoService;
  }

  @RequestMapping(method = RequestMethod.GET, value = "/")
  public List<TodoDto> findAll() {
    return todoService.findAllNotCompleted();
  }

  @RequestMapping(method = RequestMethod.POST, value = "/")
  public TodoDto create(@RequestBody(required = true) @Valid TodoDto todoDto) {
    return todoService.create(todoDto);
  }

  @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
  public void update(@PathVariable Long id, @RequestBody(required = true) @Validated(TodoDtoValidationOnUpdate.class) TodoDto todoDto) {
    todoDto.setId(id);
    todoService.update(todoDto);
  }

  @RequestMapping(method = RequestMethod.POST, value = "/{id}/complete")
  public void complete(@PathVariable Long id) {
    todoService.complete(id);
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
  public void delete(@PathVariable Long id) {
    todoService.delete(id);
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "/")
  public void deleteAll() {
    todoService.deleteAll();
  }

}
