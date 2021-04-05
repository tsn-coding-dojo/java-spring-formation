package com.thales.formation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thales.formation.dto.TodoDto;
import com.thales.formation.enums.TodoStatus;
import com.thales.formation.mapper.TodoMapper;
import com.thales.formation.model.Todo;
import com.thales.formation.repository.TodoRepository;

@Transactional
@Service
public class TodoService {

  private final TodoMapper todoMapper;

  private final TodoRepository todoRepository;

  public TodoService(TodoMapper todoMapper, TodoRepository todoRepository) {
    super();
    this.todoMapper = todoMapper;
    this.todoRepository = todoRepository;
  }

  public List<TodoDto> findAllNotCompleted() {
    return todoMapper.modelToDto(todoRepository.findByStatus(TodoStatus.TODO));
  }

  public Todo findById(Long id) {
    Optional<Todo> optTodo = todoRepository.findById(id);
    return optTodo.get();
  }

  public TodoDto create(TodoDto todoDto, String user) {
    Todo todo = todoMapper.dtoToModel(todoDto);
    todo.setStatus(TodoStatus.TODO);
    //        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    //        String user = authentication.getName();
    todo.setUser(user);
    return todoMapper.modelToDto(todoRepository.save(todo));
  }

  public void update(TodoDto todoDto) {
    Todo todo = this.findById(todoDto.getId());
    todo.setName(todoDto.getName());
    todoRepository.updateWithControl(todo, todoDto.getVersion());
  }

  public void complete(Long todoId, Long version) {
    Todo todo = this.findById(todoId);
    todo.setStatus(TodoStatus.COMPLETED);
    todoRepository.updateWithControl(todo, version);
  }

  public void delete(Long id, Long version) {
    Todo todo = this.findById(id);
    todoRepository.deleteWithControl(todo, version);
  }

  public void deleteAll() {
    todoRepository.deleteAll();
  }

}
