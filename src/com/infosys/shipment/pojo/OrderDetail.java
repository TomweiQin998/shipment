package com.infosys.shipment.pojo;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * @author Tomwei_Qin
 *
 */
public class OrderDetail implements Serializable{
	private String orderNo;

	private String orderContent;

	private List<Ship> ship;

	private double price;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderContent() {
		return orderContent;
	}

	public void setOrderContent(String orderContent) {
		this.orderContent = orderContent;
	}

	public List<Ship> getShip() {
		return ship;
	}

	public void setShip(List<Ship> ship) {
		this.ship = ship;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
