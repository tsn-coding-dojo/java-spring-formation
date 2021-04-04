package com.thales.formation.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.thales.formation.enums.TodoStatus;
import com.thales.formation.validator.NoSpecialCharacters;
import com.thales.formation.validator.group.TodoDtoValidationOnUpdate;

public class TodoDto {

  private Long id;

  @NotNull(groups = { TodoDtoValidationOnUpdate.class })
  private Long version;

  @NotBlank
  @Size(min = 5, max = 255)
  @NoSpecialCharacters(groups = { TodoDtoValidationOnUpdate.class })
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

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  public TodoStatus getStatus() {
    return status;
  }

  public void setStatus(TodoStatus status) {
    this.status = status;
  }

}
