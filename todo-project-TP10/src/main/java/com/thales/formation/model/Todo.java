package com.thales.formation.model;

import com.thales.formation.enums.TodoStatus;
import jakarta.persistence.*;

@Entity
public class Todo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Version
  @Column(nullable = false)
  private Long version;

  //  @NotNull
  @Column(nullable = false, length = 255)
  private String name;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private TodoStatus status = TodoStatus.TODO;

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

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

}
