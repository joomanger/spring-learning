package com.isd.pointcut;

import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

public class SimpleDynamicPointcut extends DynamicMethodMatcherPointcut {
	/*
	 * Рекомендуется применять следующий подход: выполнять проверку класса в
	 * методе getClassFilter (), проверку метода — в методе matches (Method,
	 * Class<?>) и проверку аргумента — в методе matches (Method, Class<?>,
	 * Object []). Это сделает срез намного проще для по- нимания и
	 * сопровождения, а также улучшит показатели производительности.
	 */

	@Override
	public boolean matches(Method method, Class<?> targetClass) {
		// Выполнить статическую проверку.
		System.out.println("Static check for " + method.getName());
		return ("foo".equals(method.getName()));
	}

	@Override
	public boolean matches(Method arg0, Class<?> arg1, Object[] arg2) {
		// Выполнить динамическую проверку.
		System.out.println("Dynamic check for " + arg0.getName());
		int x = ((Integer) arg2[0]).intValue();
		return (x == 100);
	}

	@Override
	public ClassFilter getClassFilter() {
		// TODO Auto-generated method stub
		return new ClassFilter() {

			@Override
			public boolean matches(Class<?> arg0) {
				// TODO Auto-generated method stub
				return (arg0 == DynamBean.class);
			}
		};
	}

}
