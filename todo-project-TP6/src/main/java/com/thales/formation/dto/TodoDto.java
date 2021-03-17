package com.thales.formation.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain=true)
public class TodoDto {
	
	private Long id;
	private String name;

}
