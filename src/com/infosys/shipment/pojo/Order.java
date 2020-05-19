package com.infosys.shipment.pojo;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * @author Tomwei_Qin
 *
 */
public class Order implements Serializable{

	private long orderNo;

	private int orderQuantity;

	private OrderDetail orderDetail;

	private double price;

	private List<Ship> ship;
	


	public long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(long orderNo) {
		this.orderNo = orderNo;
	}



	public Order() {
		
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

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	
	
}
