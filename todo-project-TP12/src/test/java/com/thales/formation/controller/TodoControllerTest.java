package com.thales.formation.controller;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thales.formation.dto.TodoDto;
import com.thales.formation.mapper.TodoMapper;
import com.thales.formation.service.TodoService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = TodoController.class)
class TodoControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private TodoService todoService;

  @MockBean
  private TodoMapper todoMapper;

  @Test
  void testFindAll() throws Exception {

    TodoDto todoDto = new TodoDto();
    todoDto.setId(1L);
    todoDto.setName("nope");

    List<TodoDto> mockedList = new ArrayList<>();
    mockedList.add(todoDto);

    when(todoService.findAllNotCompleted()).thenReturn(mockedList);

    this.mvc.perform(
        get("/api/todos/"))
        .andExpect(status().is(200))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].name").value("nope"))
        .andExpect(jsonPath("$[0].id").isNumber())
        .andExpect(jsonPath("$[0].id").value(1L));
  }

  @Test
  @WithMockUser(roles = { "ADMIN" })
  void test_postWithRole() throws Exception {

    TodoDto todoDto = new TodoDto();
    todoDto.setId(1L);
    todoDto.setName("THISISATEST");

    List<TodoDto> mockedList = new ArrayList<>();
    mockedList.add(todoDto);

    when(todoService.findAllNotCompleted()).thenReturn(mockedList);

    this.mvc.perform(
        post("/api/todos/")
            // Very important if CSRF is enabled :)
            .with(csrf())
            .contentType(MediaType.APPLICATION_JSON).content(asJsonString(todoDto)))
        .andExpect(status().is(200));
  }

  @Test
  @WithMockUser(authorities = { "add" })
  void test_postWithAuth() throws Exception {

    TodoDto todoDto = new TodoDto();
    todoDto.setId(1L);
    todoDto.setName("THISISATEST");

    List<TodoDto> mockedList = new ArrayList<>();
    mockedList.add(todoDto);

    when(todoService.findAllNotCompleted()).thenReturn(mockedList);

    this.mvc.perform(
        post("/api/todos/")
            // Very important if CSRF is enabled :)
            .with(csrf())
            .contentType(MediaType.APPLICATION_JSON).content(asJsonString(todoDto)))
        .andExpect(status().is(200));
  }

  @Test
  void test_shoulFailedWith403() throws Exception {

    TodoDto todoDto = new TodoDto();
    todoDto.setId(1L);
    todoDto.setName("THISISATEST");

    List<TodoDto> mockedList = new ArrayList<>();
    mockedList.add(todoDto);

    when(todoService.findAllNotCompleted()).thenReturn(mockedList);

    this.mvc.perform(
        post("/api/todos/")
            // Very important if CSRF is enabled :)
            .with(csrf())
            .contentType(MediaType.APPLICATION_JSON).content(asJsonString(todoDto)))
        .andExpect(status().is(403));
  }

  static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
