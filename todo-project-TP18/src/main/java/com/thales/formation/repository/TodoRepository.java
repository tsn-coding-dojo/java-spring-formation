package com.thales.formation.repository;

import com.thales.formation.enums.TodoStatus;
import com.thales.formation.model.Todo;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.stream.Stream;

public interface TodoRepository extends TodoCustomRepository, JpaRepository<Todo , Long> {

	Iterable<Todo> findByStatus(TodoStatus todoStatus);

	@QueryHints(value = @QueryHint(name = org.hibernate.jpa.QueryHints.HINT_FETCH_SIZE, value = "1000"))
	@Query("SELECT t FROM Todo t")
	Stream<Todo> streamAllToExport();
}
