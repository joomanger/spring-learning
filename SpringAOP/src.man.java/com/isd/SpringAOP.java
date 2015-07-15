package com.isd;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.ProxyFactory;

public class SpringAOP implements MethodBeforeAdvice, AfterReturningAdvice, ThrowsAdvice {

	public void afterThrowing(Exception ex) {
		System.out.println(ex.getMessage() + " " + ex.getClass().getName());
	}

	public void afterThrowing(Method method, Object[] args, Object target, IllegalArgumentException ex) {
		System.out.println(
				ex.getMessage() + "; className=" + ex.getClass().getName() + "; methodName=" + method.getName());
	}

	public static SecureBean getSecureBean() {

		SecureBean bean = new SecureBean();

		ProxyFactory pf = new ProxyFactory();
		pf.addAdvice(new SecurityAdvice());
		pf.setTarget(bean);

		return (SecureBean) pf.getProxy();

	}

	public void myException() throws Exception {
		throw new Exception("Пиздец! Исключение!!!");
	}

	public void myException2() throws IllegalArgumentException {
		throw new IllegalArgumentException("Пиздец2! Исключение2!!!");
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		MessageWriter target = new MessageWriter();
		ProxyFactory pf = new ProxyFactory();
		pf.addAdvice(new SpringAOP());
		pf.setTarget(target);

		MessageWriter mwp = (MessageWriter) pf.getProxy();
		mwp.writeMessage();

		// пример безопасного доступа к методу
		SecurityManager sm = new SecurityManager();
		sm.login("clarence", "asd");

		SecureBean sb = getSecureBean();

		try {
			sb.writeMessage();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// пример methodInterceptor (вместо)
		System.out.println("пример methodInterceptor (вместо)");
		WorkerBean wb = new WorkerBean();
		ProxyFactory pf2 = new ProxyFactory();
		pf2.addAdvice(new WorkerAdvice());
		pf2.setTarget(wb);

		WorkerBean wb2 = (WorkerBean) pf2.getProxy();
		wb2.doSomeWork(10000000);

		// Пример ThrowAdvice. перехватчик исключений
		SpringAOP sa = new SpringAOP();
		ProxyFactory pf3 = new ProxyFactory();
		pf3.setTarget(sa);
		pf3.addAdvice(sa);

		SpringAOP sa2 = (SpringAOP) pf3.getProxy();
		try {
			sa2.myException();

		} catch (Exception e) {
			// TODO: handle exception
			sa2.myException2();
		}
		/*
		 * С помощью срезов можно конфигурировать перечень методов, к которым
		 * будет применяться совет
		 */
		

	}

	@Override
	public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("before method=" + arg0.getName() + " insert this message");
	}

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("after method=" + method.getName() + " insert this message. result=" + returnValue
				+ " target=" + target);
	}

}
