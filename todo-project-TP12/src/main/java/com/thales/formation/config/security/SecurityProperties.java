package com.thales.formation.config.security;

/**
 * Rules definition for protected controllers.
 */
public class SecurityProperties {
	
	// ROLES
	
	public static final String ROLE_USER = "ROLE_USER";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	
	// AUTHORITIES
	
	public static final String AUTH_CRUD_TODO = "CRUD_TODO";
	
	// RULES

	public static final String PUBLIC = "permitAll";
	
	public static final String IS_AUTHENTICATED = "isAuthenticated()";
	public static final String IS_USER = "hasRole('" + ROLE_USER +"')";
	public static final String IS_ADMIN = "hasRole('" + ROLE_ADMIN + "')";
	
	public static final String CAN_CRUD_TODO = "hasAnyAuthority('" + AUTH_CRUD_TODO +"')";
	
}
