package com.thales.formation.model;

import com.thales.formation.enums.TodoStatus;

public class Todo {

  private Long id;

  private String name;

  private TodoStatus status;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public TodoStatus getStatus() {
    return status;
  }

  public void setStatus(TodoStatus status) {
    this.status = status;
  }

}
