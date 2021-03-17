package com.thales.formation.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.thales.formation.enums.TodoStatus;
import com.thales.formation.model.Todo;

public interface TodoRepository extends TodoCustomRepository, PagingAndSortingRepository<Todo , Long> {

	Iterable<Todo> findByStatus(TodoStatus todoStatus);
	
}
