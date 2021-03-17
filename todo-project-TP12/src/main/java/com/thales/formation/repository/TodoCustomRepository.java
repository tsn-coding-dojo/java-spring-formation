package com.thales.formation.repository;

import com.thales.formation.model.Todo;

public interface TodoCustomRepository {

	void updateWithControl(Todo todo, Long version);
	
	void deleteWithControl(Todo todo, Long version);
	
}
