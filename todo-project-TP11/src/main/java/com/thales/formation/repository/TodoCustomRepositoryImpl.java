package com.thales.formation.repository;

import com.thales.formation.model.Todo;
import com.thales.formation.service.domain.AuthData;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.security.authentication.InsufficientAuthenticationException;

public class TodoCustomRepositoryImpl implements TodoCustomRepository {

  @PersistenceContext
  private EntityManager em;

  @Override
  public void updateWithControl(Todo todo, Long version, AuthData authData) {
    this.canUpdateOrDelete(todo, version, authData);
    // Nothing to do, it will be handled by the transaction
  }

  @Override
  public void deleteWithControl(Todo todo, Long version, AuthData authData) {
    this.canUpdateOrDelete(todo, version, authData);
    em.remove(todo);
  }

  private void canUpdateOrDelete(Todo todo, Long version, AuthData authData) {
    // CHECK VERSION

    if (!todo.getVersion().equals(version)) {
      throw new RuntimeException("Todo has already been updated");
    }

    // CHECK SECURITY

    // ADMIN can delete/update any Todo
    // But a standard User can only delete/udpate it's own
    String currentUserName = authData.name();

    boolean isNotAdmin = authData.authorities().stream().noneMatch(auth -> auth.equals("admin"));
    boolean isNotTheSameCreationUser = !todo.getUser().equals(currentUserName);

    if (isNotAdmin && isNotTheSameCreationUser) {
      throw new InsufficientAuthenticationException("You are not allowed to update / delete this todo");
    }

  }

}
