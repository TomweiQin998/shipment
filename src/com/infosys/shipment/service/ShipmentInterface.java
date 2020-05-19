package com.infosys.shipment.service;

import com.infosys.shipment.pojo.Order;

/**
 * 
 * @author Tomwei_Qin
 *
 */
public interface ShipmentInterface {

	public Order split(int totalQuantity,int version,int splitOrMerge,int increateRootQuantity,int decreaseRootQuantity);
	
	public Order merge(int totalQuantity,int version,int splitOrMerge,int increateRootQuantity,int decreaseRootQuantity);
	
	public Order increateRootQuantity(int totalQuantity,int version,int splitOrMerge,int increateRootQuantity,int decreaseRootQuantity);
	
	public Order decreaseRootQuantity(int totalQuantity,int version,int splitOrMerge,int increateRootQuantity,int decreaseRootQuantity);
	
	public Order createOrder(int totalQuantity,int version,int splitOrMerge,int increateRootQuantity,int decreaseRootQuantity);
	
}
