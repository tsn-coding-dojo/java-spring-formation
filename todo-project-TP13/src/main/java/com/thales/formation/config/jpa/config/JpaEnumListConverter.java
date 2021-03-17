package com.thales.formation.config.jpa.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.thales.formation.enums.Role;

@Converter
public class JpaEnumListConverter implements AttributeConverter<List<Enum<?>>, String> {

  @Override
  public String convertToDatabaseColumn(List<Enum<?>> list) {
	  if (list == null || list.isEmpty()) {
		  return null;
	  }
	  return list.stream().map(enumValue -> enumValue.name()).collect(Collectors.joining(","));
  }

  @Override
  public List<Enum<?>> convertToEntityAttribute(String joined) {
	  if (joined == null || joined.isEmpty()) {
		  return new ArrayList<>();
	  }
	  return Arrays.asList(joined.split(","))
			  .stream().map(enumName -> Role.valueOf(enumName))
			  .collect(Collectors.toList());
  }

}