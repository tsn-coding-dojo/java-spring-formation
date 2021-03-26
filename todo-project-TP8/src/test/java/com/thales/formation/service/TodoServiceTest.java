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
class TodoServiceTest {

  @Mock
  private TodoRepository todoRepositoryMock;

  @InjectMocks
  private TodoService todoService;

  @Test
  void shouldFindAllNotCompleted() {

    when(todoRepositoryMock.findByStatus(TodoStatus.TODO))
        .thenReturn(
            Arrays.asList(mockTodo("toto", TodoStatus.TODO),
                mockTodo("toto", TodoStatus.TODO)));
    assertThat(todoService.findAllNotCompleted()).hasSize(2);
  }

  private Todo mockTodo(String name, TodoStatus status) {
    Todo todo = new Todo();
    todo.setName(name);
    todo.setStatus(status);
    return todo;
  }
}
