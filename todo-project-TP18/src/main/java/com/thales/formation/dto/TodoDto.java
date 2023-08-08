package com.thales.formation.dto;

import com.thales.formation.enums.TodoStatus;
import com.thales.formation.validator.NoSpecialCharacters;
import com.thales.formation.validator.group.TodoDtoValidationOnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TodoDto {

  private Long id;

  @NotNull(groups = { TodoDtoValidationOnUpdate.class })
  private Long version;

  @NotBlank
  @Size(min = 5, max = 255)
  @NoSpecialCharacters
  private String name;

  private String user;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

}
