package com.isd;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Contact implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3633613628256135379L;
	private String first_name;
	private String last_name;
	private Long id;
	private Date birth_date;
	private List<ContactTelDetail> contactTelDetail;

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}

	public List<ContactTelDetail> getContactTelDetail() {
		return contactTelDetail;
	}

	public void setContactTelDetail(List<ContactTelDetail> contactTelDetail) {
		this.contactTelDetail = contactTelDetail;
	}

	public String toString() {
		return first_name + " " + last_name + " " + birth_date + " id: " + id;
	}

}
