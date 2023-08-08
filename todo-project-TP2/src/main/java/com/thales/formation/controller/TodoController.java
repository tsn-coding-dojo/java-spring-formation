
package com.thales.formation.controller;

import com.thales.formation.dto.TodoDto;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

	private final Map<Long, String> todos = new HashMap<>();

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public List<TodoDto> findAll() {
		return convertToDto(this.todos);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/")
	public TodoDto create(@RequestBody TodoDto todo) throws Exception {
		if (todo.getName().isBlank()) {
			throw new Exception("input is incorrect");
		}
		if (this.todos.containsValue(todo.getName())) {
			throw new Exception("todo with same name already exists");
		}
		int nbExistingTodos = this.todos.size();
		this.todos.put((long) nbExistingTodos + 1, todo.getName());
		return new TodoDto((long) (nbExistingTodos + 1), todo.getName());
	}

	private List<TodoDto> convertToDto(Map<Long, String> todos) {
		return todos.entrySet().stream()
				.map(entry -> new TodoDto(entry.getKey(), entry.getValue()))
				.collect(Collectors.toList());
	}

	@PostConstruct
	public void onPostConstruct() {
		this.todos.put(1L, "Faire les courses");
		this.todos.put(2L, "Ranger la maison");
	}
}
