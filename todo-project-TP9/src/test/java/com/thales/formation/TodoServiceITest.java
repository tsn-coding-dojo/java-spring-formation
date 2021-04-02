package com.thales.formation;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.thales.formation.enums.TodoStatus;
import com.thales.formation.model.Todo;
import com.thales.formation.repository.TodoRepository;
import com.thales.formation.service.TodoService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
class TodoServiceITest {

  @Autowired
  private TodoRepository todoRepository;

  @Autowired
  private TodoService todoService;

  @Test
  void shouldFindAllNotCompleted() {

    Todo todo1 = new Todo();
    todo1.setName("toto");
    todo1.setStatus(TodoStatus.TODO);

    Todo todo2 = new Todo();
    todo2.setName("tata");
    todo2.setStatus(TodoStatus.TODO);

    todoRepository.saveAll(Arrays.asList(todo1, todo2));
    assertThat(todoService.findAllNotCompleted()).hasSize(2);
  }

}
