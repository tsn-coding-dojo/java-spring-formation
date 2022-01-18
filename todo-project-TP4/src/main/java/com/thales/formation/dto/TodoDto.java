package com.thales.formation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain=true)
@Builder
public class TodoDto {
	
	private Long id;
	private String name;

}
