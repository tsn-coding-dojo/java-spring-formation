package com.thales.formation.service;

import com.thales.formation.dto.TodoDto;
import com.thales.formation.enums.TodoStatus;
import com.thales.formation.exception.AppNotFoundException;
import com.thales.formation.exception.AppPreconditionFailedException;
import com.thales.formation.mapper.TodoMapper;
import com.thales.formation.model.Todo;
import com.thales.formation.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Transactional
@Service
public class TodoService {

  private final UserService userService;

  private final TodoMapper todoMapper;

  private final TodoRepository todoRepository;

  public TodoService(UserService userService, TodoMapper todoMapper, TodoRepository todoRepository) {
    super();
    this.userService = userService;
    this.todoMapper = todoMapper;
    this.todoRepository = todoRepository;
  }

  public List<TodoDto> findAllNotCompleted() {
    return todoMapper.modelToDto(todoRepository.findByStatus(TodoStatus.TODO));
  }

  public Todo findById(Long id) {
    Optional<Todo> optTodo = todoRepository.findById(id);
    return optTodo
            .orElseThrow(() -> new AppNotFoundException("Todo with id '" + id + "' does not exist"));
  }

  public TodoDto create(TodoDto todoDto, String user) {
    Todo todo = todoMapper.dtoToModel(todoDto);
    todo.setStatus(TodoStatus.TODO);
    todo.setUser(user);
    return todoMapper.modelToDto(todoRepository.save(todo));
  }

  public void update(TodoDto todoDto) {
    Todo todo = this.findById(todoDto.getId());
    todo.setName(todoDto.getName());
    todoRepository.updateWithControl(todo, todoDto.getVersion(), userService.getCurrentAuth());
  }

  public void complete(Long todoId, Long version) {
    Todo todo = this.findById(todoId);
    if (todo.getStatus() != TodoStatus.TODO) {
      throw new AppPreconditionFailedException("Todo with id '" + todoId + "' is already completed");
    }
    todo.setStatus(TodoStatus.COMPLETED);
    todoRepository.updateWithControl(todo, version, userService.getCurrentAuth());
  }

  public void delete(Long id, Long version) {
    Todo todo = this.findById(id);
    todoRepository.deleteWithControl(todo, version, userService.getCurrentAuth());
  }

  public void deleteAll() {
    todoRepository.deleteAll();
  }

  public void exportTodos() {
    final AtomicInteger nbLines = new AtomicInteger();
    todoRepository.streamAllToExport().forEach(todo -> {
      log.info(todo.getName() + " - " +todo.getStatus());
      nbLines.incrementAndGet();
      todoRepository.detach(todo);
    });
  }

}
