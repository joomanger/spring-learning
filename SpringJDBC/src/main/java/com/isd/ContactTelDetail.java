package com.isd;

import java.io.Serializable;

public class ContactTelDetail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2580312066766701727L;
	private Long id;
	private Long contactID;
	private String telType;
	private String telNumber;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getContactID() {
		return contactID;
	}

	public void setContactID(Long contactID) {
		this.contactID = contactID;
	}

	public String getTelType() {
		return telType;
	}

	public void setTelType(String telType) {
		this.telType = telType;
	}

	public String getTelNumber() {
		return telNumber;
	}

	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}

	public String toString() {
		return "id: " + id + " " + telNumber + " " + telType;
	}

}
