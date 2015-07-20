package com.isd.pointcut;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class SimpleAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation arg0) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("-------------- Invoking " + arg0.getMethod().getName()+"---------------");
		Object retVal = arg0.proceed();
		System.out.println("-------------- Done --------------");
		return retVal;
	}

}
