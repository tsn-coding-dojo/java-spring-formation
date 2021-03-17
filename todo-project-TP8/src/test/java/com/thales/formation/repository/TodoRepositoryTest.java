package com.thales.formation.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.thales.formation.enums.TodoStatus;
import com.thales.formation.model.Todo;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TodoRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private TodoRepository todoRepository;

	@Test
    public void shouldFindByStatus() {
        // given
		
        Todo todo = (new Todo()).setName("name").setStatus(TodoStatus.TODO);
        entityManager.persist(todo);
        
        todo = (new Todo()).setName("name2").setStatus(TodoStatus.COMPLETED);
        entityManager.persist(todo);
        
        entityManager.flush();
     
        // when
        Iterable<Todo> todosIter = todoRepository.findByStatus(TodoStatus.TODO);
        List<Todo> todos = new ArrayList<>();
        todosIter.forEach(todos::add);
     
        // then
        assertThat(todos).size().isEqualTo(1);
    }

}
