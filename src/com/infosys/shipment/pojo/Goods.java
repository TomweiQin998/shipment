package com.infosys.shipment.pojo;

import java.io.Serializable;

/**
 * 
 * @author Tomwei_Qin
 *
 */
public class Goods implements Serializable{
	
	private double weight;
	
	private String status;
	
	

	public Goods(double weight, String status) {
		super();
		this.weight = weight;
		this.status = status;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
