package com.isd;

import java.lang.reflect.Method;

import org.springframework.aop.Advisor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcher;

public class SpringAOP2 {
	public static void main(String[] args) {
		/*
		 * применение среза потока управления связано с существенным снижением
		 * (в 5 раз) производительности по сравнению с другими видами срезов.
		 */

		SpringAOP2 aop = new SpringAOP2();

		System.out.println("Использование срезов потока управления");
		aop.runControlFlow();
		System.out.println("Использование компонуемых срезов");
		aop.runComposableFlow();
	}

	// Использование срезов потока управления
	public void runControlFlow() {

		TestBean target = new TestBean();

		// "Включить в срез все методы, вызванные из метода
		// ControlFlowExample.test()".
		Pointcut pc = new ControlFlowPointcut(SpringAOP2.class, "test");

		Advisor advisor = new DefaultPointcutAdvisor(pc, new SimpleBeforeAdvice());

		ProxyFactory pf = new ProxyFactory();
		pf.addAdvisor(advisor);
		pf.setTarget(target);

		TestBean proxy = (TestBean) pf.getProxy();
		// совета не будет
		proxy.foo();
		// так как вызывается из метода test, то совет сработает
		test(proxy);

	}

	public void test(TestBean tb) {
		tb.foo();
	}

	// Использование компонуемых срезов
	public void runComposableFlow() {
		SampleBean target = new SampleBean();

		ComposablePointcut pc = new ComposablePointcut(ClassFilter.TRUE, new GetterMethodMatcher());

		System.out.println("Test 1"); // тест 1
		SampleBean proxy = getProxy(pc, target);
		testInvoke(proxy);

		System.out.println("Test 2"); // тест 2
		/*
		 * В этой точке мы имеем объединение двух MethodMatcher: один со-
		 * ответствует всем методам, начинающимся на get. а другой — всем
		 * методам, начинаю- щимся на set. Здесь мы ожидаем, что будут снабжены
		 * советом все вызовы методов.
		 */
		pc.union(new SetterMethodMatcher());
		proxy = getProxy(pc, target);
		testInvoke(proxy);

		System.out.println("Test 3"); // тест 3
		pc.intersection(new GetAgeMethodMatcher());//или
		proxy = getProxy(pc, target);
		testInvoke(proxy);
	}

	private static SampleBean getProxy(ComposablePointcut pc, SampleBean target) {
		// Создать экземпляр DefaultPointcutAdvisor.
		Advisor advisor = new DefaultPointcutAdvisor(pc, new SimpleBeforeAdvice());
		// Создать прокси.
		ProxyFactory pf = new ProxyFactory();
		pf.setTarget(target);
		pf.addAdvisor(advisor);
		return (SampleBean) pf.getProxy();
	}

	private static void testInvoke(SampleBean proxy) {
		proxy.getAge();
		proxy.getName();
		proxy.setName("Clarence Ho");
	}

	private static class GetterMethodMatcher extends StaticMethodMatcher {
		public boolean matches(Method method, Class<?> els) {
			return (method.getName().startsWith("get"));
		}
	}

	private static class GetAgeMethodMatcher extends StaticMethodMatcher {
		public boolean matches(Method method, Class<?> els) {
			return "getAge".equals(method.getName());
		}
	}

	private static class SetterMethodMatcher extends StaticMethodMatcher {
		public boolean matches(Method method, Class<?> els) {
			return (method.getName().startsWith("set"));
		}
	}
}
