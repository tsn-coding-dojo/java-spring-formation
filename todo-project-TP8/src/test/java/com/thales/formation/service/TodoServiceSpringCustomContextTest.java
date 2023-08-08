package com.thales.formation.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.thales.formation.enums.TodoStatus;
import com.thales.formation.mapper.TodoMapper;
import com.thales.formation.model.Todo;
import com.thales.formation.repository.TodoRepository;

@ExtendWith(SpringExtension.class)
class TodoServiceSpringCustomContextTest {

  @TestConfiguration
  @ComponentScan(basePackages = "com.thales.formation.mapper")
  static class TodoServiceImplTestContextConfiguration {

    @Bean
    public TodoService todoService(TodoMapper todoMapper, TodoRepository todoRepository) {
      return new TodoService(todoMapper, todoRepository);
    }
  }

  @MockBean
  private TodoRepository todoRepositoryMock;

  @MockBean
  private EntityManager entityManagerMock;

  @MockBean
  private EntityManagerFactory entityManagerFactoryMock;

  @Autowired
  private TodoService todoService;

  @Test
  void shouldFindAllNotCompleted() {

    when(todoRepositoryMock.findByStatus(any()))
        .thenReturn(
            Arrays.asList(mockTodo("toto", TodoStatus.TODO),
                mockTodo("toto", TodoStatus.TODO)));

    assertThat(todoService.findAllNotCompleted()).hasSize(2);

    verify(todoRepositoryMock).findByStatus(TodoStatus.TODO);
  }

  private Todo mockTodo(String name, TodoStatus status) {
    Todo todo = new Todo();
    todo.setName(name);
    todo.setStatus(status);
    return todo;
  }
}
