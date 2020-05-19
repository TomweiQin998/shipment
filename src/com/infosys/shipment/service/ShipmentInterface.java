package com.infosys.shipment.service;
/**
 * 
 * @author Tomwei_Qin
 *
 */
public interface ShipmentInterface {

	public double[] split(double totalQuantity,int version,int splitOrMerge,double increateRootQuantity,double decreaseRootQuantity);
	
	public double[] merge(double totalQuantity,int version,int splitOrMerge,double increateRootQuantity,double decreaseRootQuantity);
	
	public double[] increateRootQuantity(double totalQuantity,int version,int splitOrMerge,double increateRootQuantity,double decreaseRootQuantity);
	
	public double[] decreaseRootQuantity(double totalQuantity,int version,int splitOrMerge,double increateRootQuantity,double decreaseRootQuantity);
	
	
}
