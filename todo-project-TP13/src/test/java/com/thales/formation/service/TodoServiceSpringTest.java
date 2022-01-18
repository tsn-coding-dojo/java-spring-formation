package com.thales.formation.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.thales.formation.enums.TodoStatus;
import com.thales.formation.mapper.TodoMapper;
import com.thales.formation.model.Todo;
import com.thales.formation.repository.TodoRepository;

@ExtendWith(SpringExtension.class)
public class TodoServiceSpringTest {
 
    @TestConfiguration
    static class TodoServiceImplTestContextConfiguration {
        @Bean
        public TodoService todoService() {
            return new TodoService();
        }
    }
	
	@MockBean
	private TodoRepository todoRepositoryMock;
	@MockBean
	private EntityManager entityManagerMock;
	@MockBean
	private EntityManagerFactory entityManagerFactoryMock;
	@MockBean
	private TodoMapper todoMapperMock;
	@MockBean
	private UserService userService;
	
	@Autowired
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
