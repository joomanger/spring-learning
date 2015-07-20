package com.isd;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class UpdateContact extends SqlUpdate {
	private static final String sql = "update contact set first_name=:first_name, last_name=:last_name, birth_date=:birth_date where id=:id";

	public UpdateContact(DataSource dataSource) {
		super(dataSource, sql);
		super.declareParameter(new SqlParameter("first_name", Types.VARCHAR));
		super.declareParameter(new SqlParameter("last_name", Types.VARCHAR));
		super.declareParameter(new SqlParameter("birth_date", Types.DATE));
		super.declareParameter(new SqlParameter("id", Types.INTEGER));
	}

}
