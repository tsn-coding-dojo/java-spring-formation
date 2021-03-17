
package com.thales.formation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thales.formation.dto.TodoDto;
import com.thales.formation.model.Todo;
import com.thales.formation.service.TodoService;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
	
	@Autowired
	private TodoService todoService;

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public List<TodoDto> findAll() {
		return todoToDto(todoService.findAllNotCompleted());
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/")
	public TodoDto create(@RequestBody(required = true) TodoDto todoDto) {
		return todoToDto(todoService.create(todoDto));
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public void update(@PathVariable Long id, @RequestBody(required = true) TodoDto todoDto) {
		todoDto.setId(id);
		todoService.update(todoDto);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/{id}/complete")
	public void complete(@PathVariable Long id) {
		todoService.complete(id);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public void delete(@PathVariable Long id) {
		todoService.delete(id);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/")
	public void deleteAll() {
		todoService.deleteAll();
	}
	
	private List<TodoDto> todoToDto(Iterable<Todo> todos) {
		List<TodoDto> todoDtos = new ArrayList<>();
		todos.forEach(todo -> todoDtos.add(todoToDto(todo)));
		return todoDtos;
	}
	
	private TodoDto todoToDto(Todo todo) {
		TodoDto todoDto = new TodoDto();
		todoDto.setId(todo.getId());
		todoDto.setName(todo.getName());
		return todoDto;
	}	

}
