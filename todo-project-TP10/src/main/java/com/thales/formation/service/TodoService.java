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

  public void update(TodoDto todoDto) {
    // If object not in session, kaput !
    Todo todo = todoMapper.dtoToModel(todoDto);
    todoRepository.save(todo);

    //If object in session, hibernate does not care ! (as versioning is done with the version in session -  but we can still do it manually)
    //    Todo todo = this.findById(todoDto.getId());
    //    todo.setName(todoDto.getName());
    //    todo.setVersion(todoDto.getVersion()); // does not work if the entity is not detached
    //    todoRepository.updateWithControl(todo, todoDto.getVersion());

    //If object in session, hibernate does not care ! (as versioning is done with the version in session -  but we can still do it manually)
    //    Todo todo = this.findById(todoDto.getId());
    //    todo.setName(todoDto.getName());
    //    todo.setVersion(todoDto.getVersion()); // does not work if the entity is not detached
    //    todoRepository.detachTodo(todo);
    //    todoRepository.save(todo);
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
