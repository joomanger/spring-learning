package com.isd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("user")
public class User {
	private String username = "Alex";
	private String password = "password";
	@Autowired
	private Auto auto;

	public User() {

	}

	public Auto getAuto() {
		return auto;
	}

	public void setAuto(Auto auto) {
		this.auto = auto;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String toString() {
		return "username: " + username + "; password: " + password + "; auto model: " + auto.getModel()
				+ "; auto price: " + auto.getPrice();
	}
}
