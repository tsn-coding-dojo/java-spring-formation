package com.thales.formation.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.thales.formation.aspect.annotation.LogExecutionTime;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LogExecutionTimeAspect {
	
	@Around("@annotation(logExecutionTime)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint, LogExecutionTime logExecutionTime) throws Throwable {
		long start = System.currentTimeMillis();
	    Object proceed = joinPoint.proceed();
	    long executionTime = System.currentTimeMillis() - start;
	    log.info(joinPoint.getSignature() + " executed in " + executionTime + "ms");
	    return proceed;
	}

}
