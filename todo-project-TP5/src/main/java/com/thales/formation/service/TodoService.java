package com.thales.formation.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.thales.formation.dto.TodoDto;
import com.thales.formation.enums.TodoStatus;
import com.thales.formation.mapper.TodoMapper;
import com.thales.formation.model.Todo;

@Service
public class TodoService {

  private final TodoMapper todoMapper;

  public TodoService(TodoMapper todoMapper) {
    super();
    this.todoMapper = todoMapper;
  }

  private Map<Long, Todo> todos = new HashMap<>();

  private AtomicLong atomicLong = new AtomicLong();

  public Iterable<Todo> findAllNotCompleted() {
    return todos.values().stream()
        .filter(todos -> todos.getStatus() == TodoStatus.TODO)
        .collect(Collectors.toList());
  }

  public Todo findById(Long id) {
    return todos.get(id);
  }

  public Todo create(TodoDto todoDto) {
    Todo todo = todoMapper.dtoToModel(todoDto);
    todo.setId(atomicLong.getAndIncrement());
    todo.setStatus(TodoStatus.TODO);

    todos.put(todo.getId(), todo);

    return todo;
  }

  public void update(TodoDto todoDto) {
    Todo todo = this.findById(todoDto.getId());
    todoMapper.dtoToModel(todoDto, todo);
  }

  public void complete(Long todoId) {
    Todo todo = this.findById(todoId);
    todo.setStatus(TodoStatus.COMPLETED);
  }

  public void delete(Long id) {
    todos.remove(id);
  }

  public void deleteAll() {
    todos.clear();
  }

}
