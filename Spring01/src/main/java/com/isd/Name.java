package com.isd;

public class Name {
	
	private String firstName;
	private String lastName;

	public Name() {
		// TODO Auto-generated constructor stub
	}

	public Name(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String toString() {
		return "lastName is " + lastName + "; firstName is " + firstName;
	}

}
