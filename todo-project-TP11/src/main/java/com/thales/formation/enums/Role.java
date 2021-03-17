package com.thales.formation.enums;

import com.thales.formation.config.security.SecurityProperties;

public enum Role {

	USER (SecurityProperties.ROLE_USER, SecurityProperties.AUTH_CRUD_TODO ),
	ADMIN (SecurityProperties.ROLE_ADMIN, SecurityProperties.AUTH_CRUD_TODO );
	
	private final String name;
	private final String[] authorities;
	
	private Role(String name, String... authorities) {
		this.name = name;
		this.authorities = authorities;
	}
	
	public String getName() {
		return name;
	}
	
	public String[] getAuthorities() {
		return authorities;
	}
	
}
