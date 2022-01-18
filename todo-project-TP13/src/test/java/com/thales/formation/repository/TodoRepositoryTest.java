package com.thales.formation.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.thales.formation.enums.Role;
import com.thales.formation.enums.TodoStatus;
import com.thales.formation.model.Todo;
import com.thales.formation.model.User;
import com.thales.formation.service.SecurityService;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class TodoRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private TodoRepository todoRepository;
	
	@MockBean
	private SecurityService securityService;

	@Test
    public void shouldFindByStatus() {
        // given
		
		User user = (new User()).setLogin("shouldFindByStatus").setPassword("password").setSalt("salt");
		user.getRoles().add(Role.USER);
		user = entityManager.persist(user);
		
        Todo todo = (new Todo()).setName("name").setStatus(TodoStatus.TODO).setUser(user);
        entityManager.persist(todo);
        
        todo = (new Todo()).setName("name2").setStatus(TodoStatus.COMPLETED).setUser(user);
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
