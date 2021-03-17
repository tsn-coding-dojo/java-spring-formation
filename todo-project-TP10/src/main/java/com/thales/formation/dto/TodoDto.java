package com.thales.formation.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.thales.formation.validator.NoSpecialCharacters;
import com.thales.formation.validator.group.Update;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain=true)
public class TodoDto {
	
	private Long id;
	
	@NotNull(groups = { Update.class })
	private Long version;
	
	@NotBlank
	@Size(min=5, max=255)
	@NoSpecialCharacters
	private String name;

}
