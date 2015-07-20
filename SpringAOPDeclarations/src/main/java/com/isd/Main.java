package com.isd;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("обычное объявление aop объектов в appl-context.xml");
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("appl-context.xml");
		MyBean bean1 = ctx.getBean("myBean1", MyBean.class);
		MyBean bean2 = ctx.getBean("myBean2", MyBean.class);

		bean1.execute();
		bean2.execute();

		System.out.println("объявление aop объектов в appl-aop-context.xml при помощи схемы имен aop");
		GenericXmlApplicationContext ctx2 = new GenericXmlApplicationContext("appl-aop-context.xml");
		MyBean bean = ctx2.getBean("myBean", MyBean.class);

		bean.execute();

		MyBean2 b2 = ctx2.getBean("myBean2", MyBean2.class);
		b2.foo();
		b2.foo(100);

		ctx.close();
		ctx2.close();

	}

}
