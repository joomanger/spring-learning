package com.isd;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * Конфигурирование AOP аннотациями. Надо заметить, что для аннотаций
		 * используются другие библиотеки cglib и weaver
		 */
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("appl-context-annot.xml");

		MyBean bean = ctx.getBean("myBean", MyBean.class);
		bean.execute();

		ctx.close();
	}

}
