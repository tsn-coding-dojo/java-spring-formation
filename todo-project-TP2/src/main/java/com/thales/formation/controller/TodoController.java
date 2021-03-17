
package com.thales.formation.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thales.formation.dto.TodoDto;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public List<TodoDto> findAll() {
		List<TodoDto> todos = new ArrayList<>();
		
		TodoDto todoDto1 = new TodoDto();
		todoDto1.setId(1l);
		todoDto1.setName("Faire les courses");
		todos.add(todoDto1);
		
		TodoDto todoDto2 = new TodoDto();
		todoDto2.setId(2l);
		todoDto2.setName("Ranger la maison");
		todos.add(todoDto2);
		
		return todos;
	}

}
