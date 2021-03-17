package com.thales.formation.repository;

import java.util.stream.Stream;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.thales.formation.enums.TodoStatus;
import com.thales.formation.model.Todo;

public interface TodoRepository extends TodoCustomRepository, PagingAndSortingRepository<Todo , Long> {

	Iterable<Todo> findByStatus(TodoStatus todoStatus);
	
	@QueryHints(value = @QueryHint(name = org.hibernate.jpa.QueryHints.HINT_FETCH_SIZE, value = "1000"))
	@Query("SELECT t FROM Todo t")
	Stream<Todo> streamAllToExport();
	
}
