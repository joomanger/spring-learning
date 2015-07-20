package com.isd;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("appl-context.xml");
		JdbcContactDao jc = ctx.getBean("jdbcContactDao", JdbcContactDao.class);

		List<Contact> contacts = /* jc.findAllWithDetail(); */jc.findAll();
		for (Contact c : contacts) {
			System.out.println(c);
			if (c.getContactTelDetail() != null) {
				for (ContactTelDetail ct : c.getContactTelDetail()) {
					System.out.println(ct);
				}
			}
		}
		
		System.out.println();

		contacts =  jc.findAllWithDetail(); 
		for (Contact c : contacts) {
			System.out.println(c);
			if (c.getContactTelDetail() != null) {
				for (ContactTelDetail ct : c.getContactTelDetail()) {
					System.out.println(ct);
				}
			}
		}
		
		System.out.println();
		
		contacts =  jc.findByFirstName("Sofia");
		for (Contact c : contacts) {
			System.out.println(c);
			if (c.getContactTelDetail() != null) {
				for (ContactTelDetail ct : c.getContactTelDetail()) {
					System.out.println(ct);
				}
			}
		}
		
		System.out.println();
		
		Contact c=new Contact();
		c.setId(1l);
		c.setFirst_name("Oleksiy");
		c.setLast_name("Savinok");
		c.setBirth_date(new SimpleDateFormat("dd.MM.yyyy").parse("01.08.1981"));
		
		jc.updateContact(c);
		contacts = /* jc.findAllWithDetail(); */jc.findAll();
		for (Contact cc : contacts) {
			System.out.println(cc);
			if (cc.getContactTelDetail() != null) {
				for (ContactTelDetail ct : cc.getContactTelDetail()) {
					System.out.println(ct);
				}
			}
		}
		
		System.out.println();
		
		Contact contact=new Contact();
		contact.setFirst_name("Bro");
		contact.setLast_name("Pepper");
		contact.setBirth_date(new SimpleDateFormat("dd.MM.yyyy").parse("01.02.2006"));
		
		jc.insertContact(contact);
		contacts = /* jc.findAllWithDetail(); */jc.findAll();
		for (Contact cc : contacts) {
			System.out.println(cc);
			if (cc.getContactTelDetail() != null) {
				for (ContactTelDetail ct : cc.getContactTelDetail()) {
					System.out.println(ct);
				}
			}
		}		
		ctx.close();
	}

}
