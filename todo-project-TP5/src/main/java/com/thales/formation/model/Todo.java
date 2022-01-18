package com.thales.formation.model;

import com.thales.formation.enums.TodoStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Todo {

  private Long id;

  private String name;

  private TodoStatus status;

}
