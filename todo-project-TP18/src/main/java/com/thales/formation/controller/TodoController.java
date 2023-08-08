
package com.thales.formation.controller;

import com.thales.formation.aspect.annotation.LogExecutionTime;
import com.thales.formation.dto.TodoDto;
import com.thales.formation.exception.AppCustomException;
import com.thales.formation.service.TodoService;
import com.thales.formation.validator.group.TodoDtoValidationOnUpdate;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

  private final TodoService todoService;

  public TodoController(TodoService todoService) {
    super();
    this.todoService = todoService;
  }

  @LogExecutionTime
  @PreAuthorize("permitAll")
  @GetMapping(value = "/")
  public List<TodoDto> findAll() {
    return todoService.findAllNotCompleted();
  }

  @PreAuthorize("hasAuthority('add')")
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

  @PreAuthorize("hasAuthority('deleteAll')")
  @DeleteMapping(value = "/")
  public void deleteAll() {
    todoService.deleteAll();
  }

//  @ExceptionHandler(NoSuchElementFoundException.class)
//  @ResponseStatus(HttpStatus.NOT_FOUND)
//  public ResponseEntity<String> handleNoSuchElementFoundException(AppCustomException exception ) {
//    return ResponseEntity.status(HttpStatus.NOT_FOUND) body(exception.getMessage());
//  }

  @GetMapping(value = "/exception")
  public void testExceptionWithPostman() {
    throw new AppCustomException("oups");
  }

}
