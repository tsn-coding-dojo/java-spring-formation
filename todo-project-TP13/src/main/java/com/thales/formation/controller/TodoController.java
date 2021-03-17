
package com.thales.formation.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thales.formation.config.security.SecurityProperties;
import com.thales.formation.dto.TodoDto;
import com.thales.formation.mapper.TodoMapper;
import com.thales.formation.service.TodoService;
import com.thales.formation.validator.group.Update;

@Transactional
@RestController
@RequestMapping("/api/todos")
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	@Autowired
	private TodoMapper todoMapper;

	@PreAuthorize(SecurityProperties.PUBLIC)
	@RequestMapping(method = RequestMethod.GET, value = "/")
	public List<TodoDto> findAll() {
		return todoMapper.modelToDto(todoService.findAllNotCompleted());
	}
	
	@PreAuthorize(SecurityProperties.IS_AUTHENTICATED)
	@RequestMapping(method = RequestMethod.POST, value = "/")
	public TodoDto create(@RequestBody(required = true) @Valid TodoDto todoDto) {
		return todoMapper.modelToDto(todoService.create(todoDto));
	}
	
	@PreAuthorize(SecurityProperties.IS_AUTHENTICATED)
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public void update(@PathVariable Long id, @RequestBody(required = true) @Valid @Validated({ Update.class }) TodoDto todoDto) {
		todoDto.setId(id);
		todoService.update(todoDto);
	}
	
	@PreAuthorize(SecurityProperties.IS_AUTHENTICATED)
	@RequestMapping(method = RequestMethod.POST, value = "/{id}/complete")
	public void complete(@PathVariable Long id, @RequestParam Long version) {
		todoService.complete(id, version);
	}
	
	@PreAuthorize(SecurityProperties.IS_AUTHENTICATED)
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public void delete(@PathVariable Long id, @RequestParam Long version) {
		todoService.delete(id, version);
	}
	
	@PreAuthorize(SecurityProperties.IS_ADMIN)
	@RequestMapping(method = RequestMethod.DELETE, value = "/")
	public void deleteAll() {
		todoService.deleteAll();
	}
	
}
