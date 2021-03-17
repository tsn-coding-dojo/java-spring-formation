package com.thales.formation.config.scheduling;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableAsync
@Configuration
public class SchedulingConfiguration {
	
	@Bean
	public Executor taskExecutor() {
		return Executors.newScheduledThreadPool(10);
	}

}
