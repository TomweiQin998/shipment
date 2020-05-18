package com.infosys.shipment.pojo;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * @author Tomwei_Qin
 *
 */
public class Order implements Serializable{

	private String orderNo;

	private String orderContent;

	private OrderDetail orderDetail;

	private double price;

	private List<Ship> ship;
	
	
	

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

	public OrderDetail getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public List<Ship> getShip() {
		return ship;
	}

	public void setShip(List<Ship> ship) {
		this.ship = ship;
	}

}
