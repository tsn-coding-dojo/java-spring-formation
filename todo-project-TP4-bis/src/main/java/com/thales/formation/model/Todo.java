package com.thales.formation.model;

import com.thales.formation.enums.TodoStatus;

public record Todo (Long id, String name, TodoStatus status) {}



