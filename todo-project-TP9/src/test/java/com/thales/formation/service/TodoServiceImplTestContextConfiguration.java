package com.thales.formation.service;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.thales.formation.mapper.TodoMapper;
import com.thales.formation.repository.TodoRepository;

@TestConfiguration
@ComponentScan(basePackages = "com.thales.formation.mapper")
public class TodoServiceImplTestContextConfiguration {

  @Bean
  public TodoService todoService(TodoMapper todoMapper, TodoRepository todoRepository) {
    return new TodoService(todoMapper, todoRepository);
  }
}
