
package com.thales.formation.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thales.formation.dto.TodoDto;
import com.thales.formation.mapper.TodoMapper;
import com.thales.formation.service.TodoService;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

  private final TodoService todoService;

  private final TodoMapper todoMapper;

  public TodoController(TodoService todoService, TodoMapper todoMapper) {
    super();
    this.todoService = todoService;
    this.todoMapper = todoMapper;
  }

  @RequestMapping(method = RequestMethod.GET, value = "/")
  public List<TodoDto> findAll() {
    return todoMapper.modelToDto(todoService.findAllNotCompleted());
  }

  @RequestMapping(method = RequestMethod.POST, value = "/")
  public TodoDto create(@RequestBody(required = true) TodoDto todoDto) {
    return todoMapper.modelToDto(todoService.create(todoDto));
  }

  @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
  public void update(@PathVariable Long id, @RequestBody(required = true) TodoDto todoDto) {
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

  public TodoService getTodoService() {
    return todoService;
  }

  public TodoMapper getTodoMapper() {
    return todoMapper;
  }

}
