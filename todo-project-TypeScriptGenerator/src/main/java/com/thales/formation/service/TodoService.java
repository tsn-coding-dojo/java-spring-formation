package com.thales.formation.service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thales.formation.dto.TodoDto;
import com.thales.formation.enums.TodoStatus;
import com.thales.formation.exception.AppNotFoundException;
import com.thales.formation.exception.AppPreconditionFailedException;
import com.thales.formation.mapper.TodoMapper;
import com.thales.formation.message.EmailMessage;
import com.thales.formation.model.Todo;
import com.thales.formation.repository.TodoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
@Service
public class TodoService {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private TodoMapper todoMapper;

	@Autowired
	private TodoRepository todoRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private SecurityService securityService;

	@Value("${app.email.supervisor}")
	private String emailSupervisor;

	public Iterable<Todo> findAllNotCompleted() {
		return todoRepository.findByStatus(TodoStatus.TODO);
	}

	public Todo findById(Long id) {
		Optional<Todo> optTodo = todoRepository.findById(id);
		if (!optTodo.isPresent()) {
			throw new AppNotFoundException("Todo with id '" + id + "' does not exist");
		}
		return optTodo.get();
	}

	public Todo create(TodoDto todoDto) {
		Todo todo = todoMapper.dtoToModel(todoDto);
		todo.setStatus(TodoStatus.TODO);
		todo.setUser(userService.getCurrentUser());
		return todoRepository.save(todo);
	}

	public void update(TodoDto todoDto) {
		Todo todo = this.findById(todoDto.getId());
		todo.setName(todoDto.getName());
		todoRepository.updateWithControl(todo, todoDto.getVersion());
	}

	public void complete(Long todoId, Long version) {
		Todo todo = this.findById(todoId);
		if (todo.getStatus() != TodoStatus.TODO) {
			throw new AppPreconditionFailedException("Todo with id '" + todoId + "' is already completed");
		}
		todo.setStatus(TodoStatus.COMPLETED);
		todoRepository.updateWithControl(todo, version);
	}

	public void delete(Long id, Long version) {
		Todo todo = this.findById(id);
		todoRepository.deleteWithControl(todo, version);

		// Sending a deletion email
		EmailMessage emailMessage = new EmailMessage(emailSupervisor, securityService.getAuthenticationUserLogin() + " deleted todo id=" + id);
		emailService.sendEmail(emailMessage);
	}

	public void deleteAll() {
		todoRepository.deleteAll();
	}

	public void exportTodos() {
		final AtomicInteger nbLines = new AtomicInteger();
		todoRepository.streamAllToExport().forEach(todo -> {
			log.info(todo.getName() + " - " + todo.getStatus());
			nbLines.incrementAndGet();
			entityManager.detach(todo);
		});
	}

}
