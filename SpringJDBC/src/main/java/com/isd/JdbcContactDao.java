package com.isd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository("jdbcContactDao")
public class JdbcContactDao implements ContactDAO, InitializingBean {
	private static final Log log = LogFactory.getLog(JdbcContactDao.class);

	private DataSource dataSource;
	private SelectAllContacts selectAllContacts;
	private SelectContactByFirstName selectContactByFirstName;
	private UpdateContact updateContact;
	private InsertContact insertContact;
	private JdbcTemplate jt;

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		if (dataSource == null)
			throw new BeanCreationException("Должен быть установлен dataSource в ContactDao");
		if (jt == null)
			throw new BeanCreationException("Null JdbcTemplate");
	}

	@Resource(name = "dataSource")
	public void setDataSource(DataSource dataSource) {
		log.info("");
		this.dataSource = dataSource;
		this.selectAllContacts = new SelectAllContacts(dataSource);
		this.selectContactByFirstName = new SelectContactByFirstName(dataSource);
		this.updateContact = new UpdateContact(dataSource);
		this.insertContact = new InsertContact(dataSource);
		this.jt = new JdbcTemplate(dataSource);
	}

	@Override
	public void updateContact(Contact contact) {
		// TODO Auto-generated method stub
		log.info("");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("first_name", contact.getFirst_name());
		map.put("last_name", contact.getLast_name());
		map.put("birth_date", contact.getBirth_date());
		map.put("id", contact.getId());

		updateContact.updateByNamedParam(map);
	}

	@Override
	public void insertContact(Contact contact) {
		// TODO Auto-generated method stub
		log.info("");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("first_name", contact.getFirst_name());
		map.put("last_name", contact.getLast_name());
		map.put("birth_date", contact.getBirth_date());

		KeyHolder keyHolder = new GeneratedKeyHolder();
		insertContact.updateByNamedParam(map, keyHolder);
		contact.setId(keyHolder.getKey().longValue());
		
		
	}

	@Override
	public String findLastNameById(Long id) {
		// TODO Auto-generated method stub
		log.info("");
		return jt.queryForObject("select first_name from contact where id=?", new Object[] { id }, String.class);
	}

	@Override
	public List<Contact> findByFirstName(String first_name) {
		// TODO Auto-generated method stub
		log.info("");
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("first_name", first_name);

		return this.selectContactByFirstName.executeByNamedParam(param);
	}

	@Override
	public List<Contact> findAll() {
		log.info("");
		// TODO Auto-generated method stub
		// return jt.query("select id, first_name, last_name, birth_date from
		// contact", new ContactMapper());
		return this.selectAllContacts.execute();
	}

	private static final class ContactMapper implements RowMapper<Contact> {

		@Override
		public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			Contact contact = new Contact();
			contact.setId(rs.getLong("id"));
			contact.setBirth_date(rs.getDate("birth_date"));
			contact.setFirst_name(rs.getString("first_name"));
			contact.setLast_name(rs.getString("last_name"));

			return contact;
		}

	}

	@Override
	public List<Contact> findAllWithDetail() {
		// TODO Auto-generated method stub
		log.info("");
		String sql = "select c.id, c.first_name, c.last_name, c.birth_date, d.id as contact_tel_id, d.tel_type, d.tel_number "
				+ "from contact c left join contact_tel_detail d on c.id=d.contact_id";

		return jt.query(sql, new ContactWithDetailExtractor());
	}

	private static final class ContactWithDetailExtractor implements ResultSetExtractor<List<Contact>> {

		@Override
		public List<Contact> extractData(ResultSet rs) throws SQLException, DataAccessException {
			// TODO Auto-generated method stub

			Map<Long, Contact> map = new HashMap<Long, Contact>();
			Contact contact = null;

			while (rs.next()) {
				Long id = rs.getLong("id");
				contact = map.get(id);

				if (contact == null) {
					contact = new Contact();
					contact.setId(id);
					contact.setFirst_name(rs.getString("first_name"));
					contact.setLast_name(rs.getString("last_name"));
					contact.setBirth_date(rs.getDate("birth_date"));
					contact.setContactTelDetail(new ArrayList<ContactTelDetail>());
					map.put(id, contact);
				}

				Long contactTelDetailId = rs.getLong("contact_tel_id");

				if (contactTelDetailId > 0) {
					ContactTelDetail cd = new ContactTelDetail();
					cd.setId(contactTelDetailId);
					cd.setContactID(id);
					cd.setTelType(rs.getString("tel_type"));
					cd.setTelNumber(rs.getString("tel_number"));
					contact.getContactTelDetail().add(cd);
				}
			}

			return new ArrayList<Contact>(map.values());
		}

	}

}
