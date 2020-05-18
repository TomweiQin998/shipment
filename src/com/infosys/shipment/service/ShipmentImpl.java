package com.infosys.shipment.service;

import com.infosys.shipment.pojo.Ship;

/**
 * 先判断货运总吨数大小，批次数量，然后按照从大型船到小型船这样方式分配货物运输
 * @author Tomwei_Qin
 *
 */
public class ShipmentImpl implements ShipmentInterface {
	Ship ship=new Ship();
	public double[] split(double totalQuantity,int version){
		double[] d=null;
		
		if(totalQuantity>ship.bigShipMaxload[0]&&version==2){
			d={160.0, parseDouble(totalQuantity-160.0)};
		}
		if(totalQuantity>ship.bigShipMaxload[0]&&version==3){
			d={160, 100, parseDouble(totalQuantity-160.0-100)};
		}
		return d;
	}
	
	public double merge(double aQuantity,double bQuantity){
		double d=0.0;
		d=aQuantity+bQuantity;
		
		return d;
	}
	
	
	public double[] increateRootQuantity(double totalQuantity,int version){
		double[] d=null;
		
		
		return d;
	}
	
	public double[] decreaseRootQuantity(double totalQuantity,int version){
		double[] d=null;
		
		
		return d;
	}
}
