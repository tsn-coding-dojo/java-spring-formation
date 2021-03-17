package com.thales.formation.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private TodoMapper todoMapper;
	
	@Autowired
	private TodoRepository todoRepository;
	@Autowired
	private UserService userService;
	
	public Iterable<Todo> findAllNotCompleted() {
		return todoRepository.findByStatus(TodoStatus.TODO);
	}
	
	public Todo findById(Long id) {
		Optional<Todo> optTodo = todoRepository.findById(id);
		return optTodo.get();
	}

	public Todo create(TodoDto todoDto) {
		Todo todo = todoMapper.dtoToModel(todoDto);
		todo.setStatus(TodoStatus.TODO);
		todo.setUser(userService.getCurrentUser());
		return todoRepository.save(todo);
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
