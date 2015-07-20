package com.isd;

import java.lang.reflect.Method;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.aop.support.NameMatchMethodPointcut;

import com.isd.pointcut.BeanOne;
import com.isd.pointcut.BeanTwo;
import com.isd.pointcut.DynamBean;
import com.isd.pointcut.NameBean;
import com.isd.pointcut.SimpleAdvice;
import com.isd.pointcut.SimpleDynamicPointcut;
import com.isd.pointcut.SimpleStaticPointcut;

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
		pf = new ProxyFactory();
		pf.addAdvice(new WorkerAdvice());
		pf.setTarget(wb);

		WorkerBean wb2 = (WorkerBean) pf.getProxy();
		wb2.doSomeWork(10000000);

		// Пример ThrowAdvice. перехватчик исключений
		SpringAOP sa = new SpringAOP();
		pf = new ProxyFactory();
		pf.setTarget(sa);
		pf.addAdvice(sa);

		SpringAOP sa2 = (SpringAOP) pf.getProxy();
		try {
			sa2.myException();

		} catch (Exception e) {
			// TODO: handle exception
		}

		try {
			sa2.myException2();
		} catch (Exception e) {
			// TODO: handle exception
		}

		/*
		 * С помощью срезов можно конфигурировать перечень методов, к которым
		 * будет применяться совет. Статический срез:
		 */

		BeanOne beanOne = new BeanOne();
		BeanTwo beanTwo = new BeanTwo();

		BeanOne oneProxy;
		BeanTwo twoProxy;

		Pointcut pc = new SimpleStaticPointcut();
		Advice advice = new SimpleAdvice();
		Advisor advisor = new DefaultPointcutAdvisor(pc, advice);

		// Создаем прокси BeanOne
		pf = new ProxyFactory();
		pf.addAdvisors(advisor);
		pf.setTarget(beanOne);

		oneProxy = (BeanOne) pf.getProxy();
		// Создаем прокси BeanTwo
		pf = new ProxyFactory();
		pf.addAdvisors(advisor);
		pf.setTarget(beanTwo);

		twoProxy = (BeanTwo) pf.getProxy();

		oneProxy.foo();
		twoProxy.foo();
		oneProxy.bar();
		twoProxy.bar();

		// Динамический срез
		/*
		 * динамические проверки обеспечивают более высокую гибкость, чем
		 * статические проверки, но из-за дополнительных накладных расходов во
		 * время вы- полнения они должны использоваться только в случае
		 * абсолютной необходимости.
		 */
		DynamBean db = new DynamBean();
		Advisor advisor2 = new DefaultPointcutAdvisor(new SimpleDynamicPointcut(), new SimpleAdvice());
		ProxyFactory pf2 = new ProxyFactory();
		pf2.addAdvisor(advisor2);
		pf2.setTarget(db);

		DynamBean proxy = (DynamBean) pf2.getProxy();

		proxy.foo(1);
		proxy.foo(10);
		proxy.foo(100); // проверка аргумента вернет true
		proxy.bar();
		proxy.bar();
		proxy.bar();

		// Использование простого сопоставления имен NameMatchMethodPointcut
		System.out.println("Использование простого сопоставления имен NameMatchMethodPointcut");
		NameBean nb = new NameBean();

		NameMatchMethodPointcut mp = new NameMatchMethodPointcut();

		mp.addMethodName("foo");
		mp.addMethodName("bar");

		pf = new ProxyFactory();
		pf.addAdvisor(new DefaultPointcutAdvisor(mp, new SimpleAdvice()));
		pf.setTarget(nb);

		NameBean proxik = (NameBean) pf.getProxy();

		proxik.foo();
		proxik.foo(100);
		proxik.bar();
		// yup игнорируется
		proxik.yup();

		// Создание срезов с помощью регулярного выражения
		System.out.println("Создание срезов с помощью регулярного выражения:");
		JdkRegexpMethodPointcut jr = new JdkRegexpMethodPointcut();
		jr.setPattern(".*bar.*");
		Advisor advisor1 = new DefaultPointcutAdvisor(jr, new SimpleAdvice());
		pf = new ProxyFactory();

		pf.addAdvisor(advisor1);
		pf.setTarget(nb);

		proxik = (NameBean) pf.getProxy();
		proxik.foo();
		proxik.foo(100);
		proxik.bar();
		proxik.yup();

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
