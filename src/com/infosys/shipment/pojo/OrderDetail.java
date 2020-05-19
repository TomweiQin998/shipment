package com.infosys.shipment.pojo;

import java.io.Serializable;

/**
 * 
 * @author Tomwei_Qin
 *
 */
public class OrderDetail implements Serializable{
	private long orderNo;

	private String orderContent;

	private Ship[] ships;

	private double price;


	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderContent() {
		return orderContent;
	}

	public void setOrderContent(String orderContent) {
		this.orderContent = orderContent;
	}


	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Ship[] getShips() {
		return ships;
	}

	public void setShips(Ship[] ships) {
		this.ships = ships;
	}



}
