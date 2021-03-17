package com.thales.formation.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import com.thales.formation.config.jpa.config.JpaEnumListConverter;
import com.thales.formation.enums.Role;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@Accessors(chain=true)
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Version
	@Column(nullable = false)
	private Long version;
	
	@Column(nullable = false)
	private String login;
	
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String salt;
	
	@Column
	@Convert(converter = JpaEnumListConverter.class)
	private List<Role> roles = new ArrayList<>();
	
	@OneToMany(orphanRemoval=true, mappedBy = "user")
	private Set<Todo> todos;
	
}
