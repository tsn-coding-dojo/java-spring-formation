package com.thales.formation.repository;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;
import static org.assertj.core.api.Assertions.assertThat;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.DeleteAll;
import com.ninja_squad.dbsetup.operation.Operation;
import com.thales.formation.enums.TodoStatus;
import com.thales.formation.model.Todo;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class TodoRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private TodoRepository todoRepository;

  @Autowired
  private DataSource dataSource;

  @Test
  void shouldFindByStatus() {
    // given

    Todo todo = new Todo();
    todo.setName("name");
    todo.setStatus(TodoStatus.TODO);
    entityManager.persist(todo);

    todo = new Todo();
    todo.setName("name2");
    todo.setStatus(TodoStatus.COMPLETED);
    entityManager.persist(todo);

    entityManager.flush();

    // when
    Iterable<Todo> todosIter = todoRepository.findByStatus(TodoStatus.TODO);

    // then
    assertThat(todosIter).size().isEqualTo(1);
  }

  @Test
  @Sql("insertTest.sql")
  void findWithDbSetupSQL() {
    /*
     * Pure Java
     * - "type safe" 
     * - reusable
     * - Close to the test
     */

    DeleteAll deleteAllFrom = deleteAllFrom("todo");

    Operation operation = sequenceOf(
        deleteAllFrom,

        insertInto("todo")
            .columns("id", "name", "status")
            .build());

    // same DbSetup definition as above
    DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
    dbSetup.launch();
    // when
    Iterable<Todo> todosIter = todoRepository.findByStatus(TodoStatus.TODO);

    // then
    assertThat(todosIter).size().isEqualTo(1);
  }

}
