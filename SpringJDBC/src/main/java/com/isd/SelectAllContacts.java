package com.isd;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.object.MappingSqlQuery;

public class SelectAllContacts extends MappingSqlQuery<Contact> {
	private static final String SQL_SELECT_ALL_CONTACTS = "select id, first_name, last_name, birth_date from contact";

	public SelectAllContacts(DataSource dataSource) {
		super(dataSource, SQL_SELECT_ALL_CONTACTS);
	}

	@Override
	protected Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
		Contact contact = new Contact();
		contact.setId(rs.getLong("id"));
		contact.setBirth_date(rs.getDate("birth_date"));
		contact.setFirst_name(rs.getString("first_name"));
		contact.setLast_name(rs.getString("last_name"));
		return contact;
	}

}
