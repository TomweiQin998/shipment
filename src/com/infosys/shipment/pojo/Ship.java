package com.infosys.shipment.pojo;

import java.io.Serializable;

/**
 * 
 * @author Tomwei_Qin
 *
 */
public class Ship implements Serializable {
	private String shipNo;// 船只编号
	private int maximumload;// 船只最大负载量
	private int FreeWeight;// 船只空闲负载量
	private String type;// 船型号大小:1大型，2中型，3小型
	private int version;// 货运批次
	private String status;// 船只使用状态
	private String orderNo;// 订单号

	public Ship() {
	}

	
	
	public int getFreeWeight() {
		return FreeWeight;
	}



	public void setFreeWeight(int freeWeight) {
		FreeWeight = freeWeight;
	}



	public void setMaximumload(int maximumload) {
		this.maximumload = maximumload;
	}

	public String getShipNo() {
		return shipNo;
	}

	public void setShipNo(String shipNo) {
		this.shipNo = shipNo;
	}

	public int getMaximumload() {
		return maximumload;
	}

	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	};

}
