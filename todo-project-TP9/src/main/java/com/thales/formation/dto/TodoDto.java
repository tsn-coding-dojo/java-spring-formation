package com.thales.formation.dto;

import com.thales.formation.validator.NoSpecialCharacters;
import com.thales.formation.validator.group.TodoDtoValidationOnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TodoDto {

  private Long id;

  @NotBlank
  @Size(min = 5, max = 255)
  @NoSpecialCharacters(groups = { TodoDtoValidationOnUpdate.class })
  private String name;

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

}
