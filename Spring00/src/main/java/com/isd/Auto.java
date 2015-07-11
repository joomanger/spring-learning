package com.isd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("auto")
public class Auto {
	private String model = "Lada";
	private float price = 2500.65f;

	public Auto() {

	}
	
	public Auto(String model) {
		this.model = model;
	}

	public String getModel() {
		return model;
	}
	@Autowired
	public void setModel(String model) {
		this.model = model;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String toString() {
		return "model: " + model + "; price: " + price;
	}

}
