package com.thales.formation.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.thales.formation.exception.AppConflictException;
import com.thales.formation.exception.AppForbiddenException;
import com.thales.formation.model.Todo;

public class TodoCustomRepositoryImpl implements TodoCustomRepository {

  @PersistenceContext
  private EntityManager em;

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
      throw new AppConflictException("Todo has already been updated");
    }

    // CHECK SECURITY

    // ADMIN can delete/update any Todo
    // But a standard User can only delete/udpate it's own
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentPrincipalName = authentication.getName();

    boolean isNotAdmin = authentication.getAuthorities().stream().noneMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
    boolean isNotTheSameCreationUser = !todo.getUser().equals(currentPrincipalName);

    if (isNotAdmin && isNotTheSameCreationUser) {
      throw new AppForbiddenException("You are not allowed to update / delete this todo");
    }

  }

}
