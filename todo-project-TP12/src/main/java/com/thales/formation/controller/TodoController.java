
package com.thales.formation.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thales.formation.dto.TodoDto;
import com.thales.formation.exception.AppCustomException;
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
  @GetMapping(value = "/")
  public List<TodoDto> findAll() {
    return todoService.findAllNotCompleted();
  }

  @PreAuthorize("hasAuthority('add') || hasRole('ROLE_ADMIN')")
  //  @PostAuthorize("returnObject.name == 'POSTAUTH'")
  @PostMapping(value = "/")
  public TodoDto create(@RequestBody(required = true) @Valid TodoDto todoDto, Principal principal) {
    return todoService.create(todoDto, principal.getName());
  }

  @PreAuthorize("isAuthenticated()")
  @PutMapping(value = "/{id}")
  public void update(@PathVariable Long id, @RequestBody(required = true) @Validated(TodoDtoValidationOnUpdate.class) TodoDto todoDto) {
    todoDto.setId(id);
    todoService.update(todoDto);
  }

  @PreAuthorize("isAuthenticated()")
  @PostMapping(value = "/{id}/complete")
  public void complete(@PathVariable Long id, @RequestParam Long version) {
    todoService.complete(id, version);
  }

  @PreAuthorize("isAuthenticated()")
  @DeleteMapping(value = "/{id}")
  public void delete(@PathVariable Long id, @RequestParam Long version) {
    todoService.delete(id, version);
  }

  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @DeleteMapping(value = "/")
  public void deleteAll() {
    todoService.deleteAll();
  }

  //  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  //  @ExceptionHandler(AppCustomException.class)
  //  public Exception handleAppCustomException(Exception ex) {
  //    return ex;
  //  }

  @GetMapping(value = "/exception")
  public void testExceptionWithPostman() {
    throw new AppCustomException("oups");
  }

}
