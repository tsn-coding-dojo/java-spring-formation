package com.thales.formation.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.thales.formation.validator.NoSpecialCharacters;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain=true)
public class TodoDto {
	
	private Long id;
	
	@NotBlank
	@Size(min=5, max=255)
	@NoSpecialCharacters
	private String name;

}
