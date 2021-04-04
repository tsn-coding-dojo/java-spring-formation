
package com.thales.formation.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thales.formation.dto.TodoDto;
import com.thales.formation.service.TodoService;
import com.thales.formation.validator.group.TodoDtoValidationOnUpdate;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

  private final TodoService todoService;

  public TodoController(TodoService todoService) {
    super();
    this.todoService = todoService;
  }

  @PreAuthorize("permitAll")
  @RequestMapping(method = RequestMethod.GET, value = "/")
  public List<TodoDto> findAll() {
    return todoService.findAllNotCompleted();
  }

  @PreAuthorize("isAuthenticated()")
  @RequestMapping(method = RequestMethod.POST, value = "/")
  public TodoDto create(@RequestBody(required = true) @Valid TodoDto todoDto) {
    return todoService.create(todoDto);
  }

  @PreAuthorize("isAuthenticated()")
  @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
  public void update(@PathVariable Long id, @RequestBody(required = true) @Validated(TodoDtoValidationOnUpdate.class) TodoDto todoDto) {
    todoDto.setId(id);
    todoService.update(todoDto);
  }

  @PreAuthorize("isAuthenticated()")
  @RequestMapping(method = RequestMethod.POST, value = "/{id}/complete")
  public void complete(@PathVariable Long id, @RequestParam Long version) {
    todoService.complete(id, version);
  }

  @PreAuthorize("isAuthenticated()")
  @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
  public void delete(@PathVariable Long id, @RequestParam Long version) {
    todoService.delete(id, version);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @RequestMapping(method = RequestMethod.DELETE, value = "/")
  public void deleteAll() {
    todoService.deleteAll();
  }

}
