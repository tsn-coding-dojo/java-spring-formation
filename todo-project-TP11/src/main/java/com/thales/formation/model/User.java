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

@Entity
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

  @OneToMany(orphanRemoval = true, mappedBy = "user")
  private Set<Todo> todos;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  public Set<Todo> getTodos() {
    return todos;
  }

  public void setTodos(Set<Todo> todos) {
    this.todos = todos;
  }

}
