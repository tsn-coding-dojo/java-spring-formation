package com.thales.formation.service;

import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import com.thales.formation.dto.TodoDto;
import com.thales.formation.enums.TodoStatus;
import com.thales.formation.mapper.TodoMapper;
import com.thales.formation.model.Todo;
import com.thales.formation.repository.TodoRepository;

@Service
public class TodoService {

  private final TodoMapper todoMapper;

  private final TodoRepository todoRepository;

  public TodoService(TodoMapper todoMapper, TodoRepository todoRepository) {
    super();
    this.todoMapper = todoMapper;
    this.todoRepository = todoRepository;
  }

  public Iterable<Todo> findAllNotCompleted() {
    return todoRepository.findByStatus(TodoStatus.TODO);
  }

  public Todo findById(Long id) {
    Optional<Todo> optTodo = todoRepository.findById(id);
    return optTodo.orElseThrow(EntityNotFoundException::new);
  }

  public Todo create(TodoDto todoDto) {
    Todo todo = todoMapper.dtoToModel(todoDto);
    todo.setStatus(TodoStatus.TODO);
    return todoRepository.save(todo);
  }

  public void update(TodoDto todoDto) {
    Todo todo = this.findById(todoDto.getId());
    todo.setName(todoDto.getName());
  }

  public void complete(Long todoId) {
    Todo todo = this.findById(todoId);
    todo.setStatus(TodoStatus.COMPLETED);
  }

  public void delete(Long id) {
    todoRepository.deleteById(id);
  }

  public void deleteAll() {
    todoRepository.deleteAll();
  }

}
