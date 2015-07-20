package com.isd;

public class MyBean2 {
	private String firstMame, lastName;

	public String getFirstMame() {
		return firstMame;
	}

	public void setFirstMame(String firstMame) {
		this.firstMame = firstMame;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void foo() {
		System.out.println("Бляха муха");
	}
	
	public void foo(int intValue) {
		System.out.println("Бляха муха "+intValue);
	}

}
