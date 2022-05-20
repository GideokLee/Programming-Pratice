package com.ssafy.ws.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;



@Component
@Aspect
public class LoggingAspect {
	@Pointcut("execution( * com.ssafy.ws.model..*.*(..))")
	public void pointCut(){}
	
	@Before("pointCut()")
	public void before(JoinPoint joinPoint) {
		String signature = joinPoint.getSignature().toShortString();
		Object args[] = joinPoint.getArgs();

		System.out.println("Log : " + signature);
		System.out.println(args[0].toString());
		
		
	}
}
