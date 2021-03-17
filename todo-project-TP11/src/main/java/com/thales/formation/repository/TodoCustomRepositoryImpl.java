package com.thales.formation.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.thales.formation.config.security.SecurityProperties;
import com.thales.formation.model.Todo;
import com.thales.formation.service.SecurityService;

public class TodoCustomRepositoryImpl implements TodoCustomRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private SecurityService securityService;

	@Override
	public void updateWithControl(Todo todo, Long version) {
		this.canUpdateOrDelete(todo, version);
		// Nothing to do, it will be handled by the transaction
	}

	@Override
	public void deleteWithControl(Todo todo, Long version) {
		this.canUpdateOrDelete(todo, version);
		em.remove(todo);
	}

	private void canUpdateOrDelete(Todo todo, Long version) {
		// CHECK VERSION

		if (!todo.getVersion().equals(version)) {
			throw new RuntimeException("Todo has already been updated");
		}

		// CHECK SECURITY

		// ADMIN can delete/update any Todo
		// But a standard User can only delete/udpate it's own
		if (!securityService.hasRole(SecurityProperties.ROLE_ADMIN)) {
			// Not admin : check owner
			if (!todo.getUser().getLogin().equals(securityService.getAuthenticationUserLogin())) {
				throw new RuntimeException("You are not allowed to update / delete this todo");
			}
		}

	}

}
