package com.thales.formation.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.thales.formation.enums.TodoStatus;
import com.thales.formation.model.Todo;
import com.thales.formation.repository.TodoRepository;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {
	
	@Mock
	private TodoRepository todoRepositoryMock;
	
	@InjectMocks
	private TodoService todoService;
	
	@Test
	public void shouldFindAllNotCompleted() {
		
		when(todoRepositoryMock.findByStatus(TodoStatus.TODO))
		.thenReturn(
				Arrays.asList(
						(new Todo()).setName("toto").setStatus(TodoStatus.TODO),
						(new Todo()).setName("tata").setStatus(TodoStatus.TODO)));
		assertThat(todoService.findAllNotCompleted()).hasSize(2);
	}

}
