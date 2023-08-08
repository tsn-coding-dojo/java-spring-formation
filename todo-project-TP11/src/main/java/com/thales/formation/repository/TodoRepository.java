package com.thales.formation.repository;

import com.thales.formation.enums.TodoStatus;
import com.thales.formation.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends TodoCustomRepository, JpaRepository<Todo , Long> {

	Iterable<Todo> findByStatus(TodoStatus todoStatus);
	
}
