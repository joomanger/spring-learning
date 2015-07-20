/*Обнаружение модификаций объекта при помощи введений*/

package com.isd;

import org.springframework.aop.IntroductionAdvisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultIntroductionAdvisor;

public class SpringAOPIntr {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		TargetBean target = new TargetBean();
		target.setName("Oleksiy Savin");

		// IntroductionAdvisor advisor=new IsModifiedAdvisor();
		IntroductionAdvisor advisor = new DefaultIntroductionAdvisor(new IsModifiedMixin());

		ProxyFactory pf = new ProxyFactory();
		pf.addAdvisor(advisor);
		pf.setTarget(target);

		/*
		 * чтобы обеспечить использование прокси CGLIB Причина в том, что если
		 * выбрать для введения смеси прокси JDK, то результирующий прокси не
		 * будет экземпляром клас- са (в данном случае TargetBean); такой прокси
		 * реализует только интерфейсы смеси, но не исходный класс. В случае
		 * прокси CGLIB исходный класс реализуется прокси наряду с интерфейсами
		 * смеси.
		 */
		pf.setOptimize(true);

		TargetBean proxy = (TargetBean) pf.getProxy();
		IsModified proxyInterface = (IsModified) proxy;

		// Протестировать интерфейсы
		System.out.println("Is TargetBean?: " + (proxy instanceof TargetBean));
		System.out.println("Is IsModified?: " + (proxy instanceof IsModified));

		// Тестим реализацию проверки модификаций
		System.out.println("Has been modified?: " + proxyInterface.isModified());

		proxy.setName("Oleksiy Savin");
		System.out.println("Has been modified?: " + proxyInterface.isModified());

		proxy.setName("Mark Savin");
		System.out.println("Has been modified?: " + proxyInterface.isModified());

	}

}
