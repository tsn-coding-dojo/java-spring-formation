package com.thales.formation.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.thales.formation.dto.TodoDto;
import com.thales.formation.enums.TodoStatus;
import com.thales.formation.mapper.TodoMapper;
import com.thales.formation.model.Todo;
import com.thales.formation.repository.TodoRepository;

@Service
@Transactional
public class TodoService {

  private final TodoMapper todoMapper;

  private final TodoRepository todoRepository;

  //  @Autowired
  //  private Validator validator;

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

  public TodoDto create(TodoDto todoDto) {
    Todo todo = todoMapper.dtoToModel(todoDto);
    todo.setStatus(TodoStatus.TODO);
    return todoMapper.modelToDto(todoRepository.save(todo));
  }

  //  public TodoDto create(TodoDto todoDto) {
  //    Set<ConstraintViolation<TodoDto>> constraintViolations = validator.validate(todoDto);
  //    if (!constraintViolations.isEmpty()) {
  //      throw new RuntimeException("Ouch! ");
  //    }
  //    Todo todo = todoMapper.dtoToModel(todoDto);
  //    todo.setStatus(TodoStatus.TODO);
  //    return todoMapper.modelToDto(todoRepository.save(todo));
  //  }

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
