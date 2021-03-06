package com.isd;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class SecurityAdvice implements MethodBeforeAdvice {
	private SecurityManager securityManager;

	public SecurityAdvice() {
		// System.out.println("advice");
		this.securityManager = new SecurityManager();
	}

	@Override
	public void before(Method method, Object[] arg1, Object arg2) throws Throwable {
		// TODO Auto-generated method stub
		UserInfo user = securityManager.getLoggedOnUser();

		if (user == null) {
			System.out.println("No user authenticated");
			throw new SecurityException("You must login before attempting to invoke the method: " + method.getName());
		} else if ("clarence".equals(user.getUserName())) {
			System.out.println("Logged in user is clarence - OKAY!");
		} else {
			System.out.println("Logged in user is " + user.getUserName() + " NOT GOOD : (");
			throw new SecurityException(
					"User " + user.getUserName() + " is not allowed access to method " + method.getName());
		}
	}

}
