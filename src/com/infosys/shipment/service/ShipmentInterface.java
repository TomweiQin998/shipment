package com.infosys.shipment.service;
/**
 * 
 * @author Tomwei_Qin
 *
 */
public interface ShipmentInterface {

	public double[] split(double totalQuantity,int version);
	
	public double merge(double aQuantity,double bQuantity);
	
	public double[] increateRootQuantity(double totalQuantity,int version);
	
	public double[] decreaseRootQuantity(double totalQuantity,int version);
	
	
}
