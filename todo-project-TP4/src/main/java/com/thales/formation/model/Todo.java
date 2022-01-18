package com.thales.formation.model;

import com.thales.formation.enums.TodoStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain=true)
@Builder
public class Todo {
	
	private Long id;
	private String name;
	private TodoStatus status;	

}
