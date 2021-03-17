package com.thales.formation.service;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.thales.formation.Application;
import com.thales.formation.enums.TodoStatus;
import com.thales.formation.model.Todo;
import com.thales.formation.repository.TodoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TodoServiceITest {
	
	@Autowired
	private TodoRepository todoRepository;
	
	@Autowired
	private TodoService todoService;
	
	@Test
	public void shouldFindAllNotCompleted() {
		
		todoRepository.saveAll(Arrays.asList(
				(new Todo()).setName("toto").setStatus(TodoStatus.TODO),
				(new Todo()).setName("tata").setStatus(TodoStatus.TODO)
				));
		assertThat(todoService.findAllNotCompleted()).hasSize(2);
	}

}
