package com.isd;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component("main")
public class Spring01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * Краткая аннотация к проекту: пример создании фабрики бина.
		 */

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("META-INF/spring/appl-context.xml");
		MessageDigester md = ctx.getBean("digester", MessageDigester.class);
		md.digest("Hello World!!!!");
		ctx.close();
	}
}
