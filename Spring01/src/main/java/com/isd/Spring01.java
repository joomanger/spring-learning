package com.isd;

import java.util.Date;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Spring01 {

	private Name name;
	private Date datez;

	public Date getDatez() {
		return datez;
	}

	public void setDatez(Date datez) {
		this.datez = datez;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		/*
		 * краткая аннотация к проекту: Рассмотрено как регистрировать
		 * пользовательские свойства. как имя Oleksiy Savin разложить на свойста
		 * firstName и lastName объекта Name. также показан устанавливать свой
		 * формат даты
		 */

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("META-INF/spring/appl-context.xml");
		Spring01 spr = ctx.getBean("main", Spring01.class);

		System.out.println(spr.getName() + " date: " + spr.getDatez());

		ctx.close();
	}

}
