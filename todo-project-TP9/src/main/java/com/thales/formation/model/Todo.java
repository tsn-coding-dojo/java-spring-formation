package com.thales.formation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.thales.formation.enums.TodoStatus;

@Entity
public class Todo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  //  @NotNull
  @Column(nullable = false, length = 255)
  private String name;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
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
