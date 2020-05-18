package com.infosys.shipment.pojo;

import java.io.Serializable;

/**
 * 
 * @author Tomwei_Qin
 *
 */
public class Ship implements Serializable{
	private String shipNo;// 船只编号
	private double maximumload;// 船只最大负载量
	private double FreeWeight;// 船只空闲负载量
	private String type;// 船型号大小:1大型，2中型，3小型
	private int version;// 货运批次
	private String status;// 船只使用状态
	
	
	public static double[] bigShipMaxload={160,100,80};//大型 船只的最大负载量为80,100,160各一艘
	public static double[] middleShipMaxload={60,50,40};//中型船只的最大负载量为60,50,40各一艘
	public static double[] smallShipMaxload={30,25,20,15,10};//小型 船只的最大负载量为30,20,25,15,10各一艘
	public Ship(){};
	public Ship(String shipNo, double maximumload, double freeWeight, String type, int version, String status) {
		super();
		this.shipNo = shipNo;
		this.maximumload = maximumload;
		FreeWeight = freeWeight;
		this.type = type;
		this.version = version;
		this.status = status;
	}

	public String getShipNo() {
		return shipNo;
	}

	public void setShipNo(String shipNo) {
		this.shipNo = shipNo;
	}

	public double getMaximumload() {
		return maximumload;
	}

	public void setMaximumload(double maximumload) {
		this.maximumload = maximumload;
	}

	public double getFreeWeight() {
		return FreeWeight;
	}

	public void setFreeWeight(double freeWeight) {
		FreeWeight = freeWeight;
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

}
