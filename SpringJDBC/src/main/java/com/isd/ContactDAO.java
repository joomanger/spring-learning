package com.isd;

import java.util.List;

public interface ContactDAO {
	
	String findLastNameById(Long id);

	List<Contact> findAll();
	
	List<Contact> findAllWithDetail();
	
	List<Contact> findByFirstName(String first_name);
	
	void updateContact(Contact contact);
	
	void insertContact(Contact contact);
}
