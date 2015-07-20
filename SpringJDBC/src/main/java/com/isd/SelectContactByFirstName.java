package com.isd;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;
import java.sql.Types;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

public class SelectContactByFirstName extends MappingSqlQuery<Contact> {
	private static final String sql = "select id, first_name, last_name, birth_date from contact where first_name=:first_name";

	public SelectContactByFirstName(DataSource dataSource) {
		// TODO Auto-generated constructor stub
		super(dataSource, sql);
		super.declareParameter(new SqlParameter("first_name", Types.VARCHAR));
	}

	@Override
	protected Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Contact contact = new Contact();
		contact.setId(rs.getLong("id"));
		contact.setBirth_date(rs.getDate("birth_date"));
		contact.setFirst_name(rs.getString("first_name"));
		contact.setLast_name(rs.getString("last_name"));
		return contact;
	}

}
