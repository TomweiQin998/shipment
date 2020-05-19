package com.infosys.shipment.service;


import com.infosys.shipment.util.Constant;

/**
 * 先判断货运总吨数大小，批次数量，然后再判断是否需要拆分或合并，按照从大型船到小型船这样方式分配货物运输
 * 货物重量不能超过船只最大负载量
 * @author Tomwei_Qin
 *
 */
public class ShipmentImpl implements ShipmentInterface {

	/**
	 * 拆分
	 */
	public double[] split(double totalQuantity,int version,int splitOrMerge,double increateRootQuantity,double decreaseRootQuantity){
		double[] d=null;
		
		if(totalQuantity>Constant.middleShipMaxload_40&&version==2){
			d=new double[]{40, totalQuantity-40.0};
		}
		if(totalQuantity>Constant.middleShipMaxload_50&&version==3){
			d=new double[]{50, 40, totalQuantity-50-40};
		}
		if(totalQuantity>Constant.middleShipMaxload_60&&version==4){
			d=new double[]{60, 50, 40, totalQuantity-60-50-40};
		}
		
		return d;
	}
	
	/**
	 * 合并
	 */
	public double[] merge(double totalQuantity,int version,int splitOrMerge,double increateRootQuantity,double decreaseRootQuantity){		
		double[] d=null;		
		
		if(totalQuantity>Constant.middleShipMaxload_40&&version==2){
			d=new double[]{40, totalQuantity-40.0};
		}
		if(totalQuantity>Constant.middleShipMaxload_50&&version==3){
			d=new double[]{50, 40, totalQuantity-50-40};
		}
		if(totalQuantity>Constant.middleShipMaxload_60&&version==4){
			d=new double[]{60, 50, 40, totalQuantity-60-50-40};
		}
		return d;
	}
	
	
	/**
	 * 添加货运根数据
	 */
	public double[] increateRootQuantity(double totalQuantity,int version,int splitOrMerge,double increateRootQuantity,double decreaseRootQuantity){
		double[] d=null;
		if(totalQuantity>Constant.bigShipMaxload_160&&version==2){
			d=new double[]{160.0, totalQuantity-160.0};
		}
		if(totalQuantity>Constant.bigShipMaxload_160&&version==3){
			d=new double[]{160, 100, totalQuantity-160-100};
		}
		if(totalQuantity>Constant.bigShipMaxload_160&&version==4){
			d=new double[]{160, 100, 80, totalQuantity-160-100-80};
		}
		if(totalQuantity>Constant.bigShipMaxload_80&&totalQuantity<Constant.bigShipMaxload_160){
			d=new double[]{160, 100, 80, totalQuantity-160-100-80};
		}
		return d;
	}
	
	/**
	 * 减少货运根数据
	 */
	public double[] decreaseRootQuantity(double totalQuantity,int version,int splitOrMerge,double increateRootQuantity,double decreaseRootQuantity){
		double[] d=null;

		if(totalQuantity>=Constant.smallShipMaxload_10&&totalQuantity<=Constant.smallShipMaxload_30){
			d=new double[]{160, 100, 80, totalQuantity-160-100-80};
		}
		return d;
	}
}
