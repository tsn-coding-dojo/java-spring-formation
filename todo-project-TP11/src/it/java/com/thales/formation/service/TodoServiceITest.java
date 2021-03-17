package com.thales.formation.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import com.thales.formation.Application;
import com.thales.formation.dto.TodoDto;
import com.thales.formation.enums.Role;
import com.thales.formation.enums.TodoStatus;
import com.thales.formation.mapper.TodoMapper;
import com.thales.formation.model.Todo;
import com.thales.formation.model.User;
import com.thales.formation.repository.TodoRepository;
import com.thales.formation.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TodoServiceITest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TodoRepository todoRepository;
	
	@Autowired
	private TodoService todoService;
	
	@Autowired
	private TodoMapper todoMapper;
	
	@Autowired
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	private SecurityService securityService;
	
	@Test
	public void shouldFindAllNotCompleted() {
		
		User user = new User();
		user.setLogin("shouldFindAllNotCompleted");
		securityService.setPassword(user, "password");
		user.getRoles().add(Role.USER);
		user = userRepository.save(user);
		
		todoRepository.saveAll(Arrays.asList(
				(new Todo()).setName("toto").setStatus(TodoStatus.TODO).setUser(user),
				(new Todo()).setName("tata").setStatus(TodoStatus.TODO).setUser(user)
				));
		assertThat(todoService.findAllNotCompleted()).hasSize(2);
	}
	
	@Test
	public void shouldBeInConflict() {
		
		User user = new User();
		user.setLogin("shouldBeInConflict");
		securityService.setPassword(user, "password");
		user.getRoles().add(Role.USER);
		user = userRepository.save(user);
		
		// Authenticate the user
		Authentication authRequest = new UsernamePasswordAuthenticationToken("shouldBeInConflict", "password");
		Authentication authentication = authenticationProvider.authenticate(authRequest);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		Todo todo = todoRepository.save((new Todo()).setName("name").setStatus(TodoStatus.TODO).setUser(user));
		
		TodoDto todoDto = todoMapper.modelToDto(todo);
		todoDto.setName("name2");
		todoService.update(todoDto);
		assertThat(todoService.findById(todo.getId()).getName()).isEqualTo("name2");
		
		todoDto.setName("name3");		
		Throwable thrown = catchThrowable(() -> todoService.update(todoDto));
		assertThat(thrown).isInstanceOf(RuntimeException.class);
	}

}
