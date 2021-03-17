package com.thales.formation.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.thales.formation.enums.TodoStatus;
import com.thales.formation.model.Todo;

public interface TodoRepository extends PagingAndSortingRepository<Todo , Long> {

	List<Todo> findByStatus(TodoStatus todoStatus);
	
}
