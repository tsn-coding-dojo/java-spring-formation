package com.thales.formation.service;

import com.thales.formation.enums.TodoStatus;
import com.thales.formation.model.Todo;
import com.thales.formation.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TodoServiceITest {
	
	@Autowired
	private TodoRepository todoRepository;
	
	@Autowired
	private TodoService todoService;
	
	@Test
	public void shouldFindAllNotCompleted() {
		todoRepository.saveAll(Arrays.asList(
				createTodo("toto", TodoStatus.TODO),
				createTodo("tata", TodoStatus.TODO)
				));
		assertThat(todoService.findAllNotCompleted()).hasSize(2);
	}

	private Todo createTodo(String name, TodoStatus status) {
		Todo todo = new Todo();
		todo.setName(name);
		todo.setStatus(status);
		return todo;
	}

}
