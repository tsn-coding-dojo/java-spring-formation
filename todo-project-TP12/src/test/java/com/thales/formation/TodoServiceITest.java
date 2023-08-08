package com.thales.formation;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.DeleteAll;
import com.ninja_squad.dbsetup.operation.Operation;
import com.thales.formation.config.security.SecurityConfiguration;
import com.thales.formation.dto.TodoDto;
import com.thales.formation.enums.TodoStatus;
import com.thales.formation.model.Todo;
import com.thales.formation.repository.TodoRepository;
import com.thales.formation.service.TodoService;
import com.thales.formation.service.UserService;
import com.thales.formation.service.domain.AuthData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TodoProjectApplication.class)
@Import(SecurityConfiguration.class)
class TodoServiceITest {

  @Autowired
  private TodoRepository todoRepository;

  @Autowired
  private TodoService todoService;

  @MockBean
  private UserService userService;

  @Autowired
  private DataSource dataSource;

  private final List<String> nbName = Arrays.asList("Test1", "test2");

  @BeforeEach
  void cleanUp() {
    DeleteAll deleteAllFrom = deleteAllFrom("todo");

    Operation operation = sequenceOf(
        deleteAllFrom);

    // same DbSetup definition as above
    DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
    dbSetup.launch();
  }

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

  @Test
  void save_withOptimisticLockingHandling() throws InterruptedException {
    TodoDto todoDto = new TodoDto();
    todoDto.setName("test");
    // given
    final TodoDto srTodoDto = todoService.create(todoDto, "user");
    assertEquals(0, srTodoDto.getVersion());


    AuthData mockedAuthData = new AuthData("user", List.of("add"));
    Mockito.when(userService.getCurrentAuth()).thenReturn(mockedAuthData);

    // when
    ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
    threadPoolTaskExecutor.setThreadNamePrefix("OptLock");
    final ExecutorService executor = Executors.newFixedThreadPool(nbName.size(), threadPoolTaskExecutor);

    for (final String name : nbName) {
      srTodoDto.setName(name);
      executor.execute(() -> todoService.update(srTodoDto));
    }

    executor.shutdown();
    boolean awaitTermination = executor.awaitTermination(1, TimeUnit.MINUTES);
    assertTrue(awaitTermination);
  }
}
