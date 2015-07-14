package com.isd;

import java.util.Locale;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Spring02 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello world!");

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("appl-context.xml");

		System.out.println(ctx.getMessage("msg", null, Locale.ENGLISH));
		System.out.println(ctx.getMessage("msg", null, new Locale("ru", "RU")));
		System.out.println(ctx.getMessage("msgName", new Object[] { "Hello", "World" }, Locale.ENGLISH));

		// msg1 нет в файле, значит выдается сообщение соответствющее 3-му
		// параметру
		System.out.println(ctx.getMessage("msg1", null, "ключ не найден!", new Locale("ru", "RU")));

		ctx.publishEvent(new MessageEvent(Spring02.class, "Событие 1"));
		ctx.publishEvent(new MessageEvent(Spring02.class, "Событие 2"));

		ctx.close();

	}

}
