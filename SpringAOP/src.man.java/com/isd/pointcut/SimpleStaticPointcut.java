package com.isd.pointcut;

import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

public class SimpleStaticPointcut extends StaticMethodMatcherPointcut {

	@Override
	public boolean matches(Method arg0, Class<?> arg1) {
		// TODO Auto-generated method stub
		return ("foo".equals(arg0.getName()));
	}

	public ClassFilter getClassFilter() {
		return new ClassFilter() {
			public boolean matches(Class<?> cls) {
				return (cls == BeanOne.class);
			}
		};
	}

}
