package com.thales.formation.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Rules definition for protected controllers.
 */
@Configuration
@EnableWebMvc
public class StaticResourceConfiguration implements WebMvcConfigurer{
	
@Override
public void addResourceHandlers(final ResourceHandlerRegistry registry)
{
	registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
	registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	registry.addResourceHandler("/todos-v1.json").addResourceLocations("classpath:/openapi/todos-v1.json");
	registry.addResourceHandler("/**").addResourceLocations("classpath:/public/");
}
	
}
