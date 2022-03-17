package com.ssafy.ws.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;



@Component
@Aspect
public class LoggingAspect {
		
	@Pointcut("excution(* com.ssafy.ws.model..*.*(..))")
	public void profileTarget() {}
	
	@Around("profileTarget()")
	public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("hi");
		String signature = joinPoint.getSignature().toShortString();
		System.out.println("Log : " + signature);
		Object result = joinPoint.proceed();
		return result;
	}
	
}
