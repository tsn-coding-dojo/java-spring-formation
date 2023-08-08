package com.thales.formation.repository;

import com.thales.formation.model.Todo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

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
      throw new RuntimeException("Todo has already been updated");
    }

  }

  @Override
  public void detachTodo(Todo todo) {
    em.detach(todo);
  }

}
