package com.thales.formation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thales.formation.enums.TodoStatus;
import com.thales.formation.model.Todo;

public interface TodoRepository extends JpaRepository<Todo , Long> {

	Iterable<Todo> findByStatus(TodoStatus todoStatus);
	
}
