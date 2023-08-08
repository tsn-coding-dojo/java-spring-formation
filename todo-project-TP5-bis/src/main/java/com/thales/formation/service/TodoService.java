package com.thales.formation.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.thales.formation.dto.TodoDto;
import com.thales.formation.enums.TodoStatus;
import com.thales.formation.model.Todo;

@Service
public class TodoService {

	private Map<Long, Todo> todos = new HashMap<>();

	private AtomicLong atomicLong = new AtomicLong();

	public Iterable<Todo> findAllNotCompleted() {
		return todos.values().stream().filter(todos -> todos.status() == TodoStatus.TODO)
				.collect(Collectors.toList());
	}

	public Todo findById(Long id) {
		return todos.get(id);
	}

	public Todo create(TodoDto todoDto) {
		Todo todo = new Todo(atomicLong.getAndIncrement(), todoDto.name(), TodoStatus.TODO);
		todos.put(todo.id(), todo);
		return todo;
	}

	public void update(TodoDto todoDto) {
		Todo todo = this.findById(todoDto.id());
		Todo updatedTodo = new Todo(todo.id(), todoDto.name(), todo.status());
		this.todos.put(todo.id(), updatedTodo);
	}

	public void complete(Long todoId) {
		Todo todo = this.findById(todoId);
		Todo completedTodo = new Todo(todo.id(), todo.name(), TodoStatus.COMPLETED);
		this.todos.put(todo.id(), completedTodo);
	}

	public void delete(Long id) {
		todos.remove(id);
	}

	public void deleteAll() {
		todos.clear();
	}

}
