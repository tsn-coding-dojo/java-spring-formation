package com.thales.formation.service.domain;

import java.util.List;

public record AuthData(String name, List<String> authorities) { }
