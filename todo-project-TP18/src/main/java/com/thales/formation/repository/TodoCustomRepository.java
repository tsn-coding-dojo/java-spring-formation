package com.thales.formation.repository;

import com.thales.formation.model.Todo;
import com.thales.formation.service.domain.AuthData;

public interface TodoCustomRepository {

	void updateWithControl(Todo todo, Long version, AuthData authData);
	
	void deleteWithControl(Todo todo, Long version, AuthData authData);

	void detach(Todo todo);
	
}
