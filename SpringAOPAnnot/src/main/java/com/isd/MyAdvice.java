package com.isd;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAdvice {

	@Pointcut("execution(* foo(int)) && args(intValue)")
	public void fooExecution(int intValue) {

	}

	@Pointcut("bean(myDependency*)")
	public void inMyDependency() {

	}

	@Before("fooExecution(intValue) && inMyDependency()")
	public void simpleBeforeAdvice(JoinPoint jp, int intValue) {
		if (intValue != 100) {
			System.out.println("before executing: " + jp.getSignature().getDeclaringTypeName() + " " + jp.getSignature()
					+ " intValue=" + intValue);
		}
	}

	@Around("fooExecution(intValue) && inMyDependency()")
	public Object simpleAroundAdvice(ProceedingJoinPoint jp, int intValue) throws Throwable {
		System.out.println("around:before executing: " + jp.getSignature().getDeclaringTypeName() + " "
				+ jp.getSignature() + " intValue=" + intValue);

		Object retVal = jp.proceed();

		System.out.println("around:after executing: " + jp.getSignature().getDeclaringTypeName() + " "
				+ jp.getSignature() + " intValue=" + intValue);

		return retVal;
	}

}
