package com.infosys.shipment.service;


import java.util.ArrayList;
import java.util.List;

import com.infosys.shipment.pojo.Order;
import com.infosys.shipment.pojo.OrderDetail;
import com.infosys.shipment.pojo.Ship;
import com.infosys.shipment.servlet.ShipList;
import com.infosys.shipment.util.BusinessException;
import com.infosys.shipment.util.Constant;

/**
 * 先判断货运总吨数大小，批次数量，然后再判断是否需要拆分或合并，按照从大型船到小型船这样方式分配货物运输
 * 货物重量不能超过船只最大负载量
 * @author Tomwei_Qin
 *
 */
public class ShipmentImpl implements ShipmentInterface {
	
	public Order createOrder(final int orderQuantity) {
        Order order = null;
        if (orderQuantity > 0) {
              final ShipList instance = ShipList.getInstance();
              final List<Ship> shipList = instance.getShipList();
              order = new Order();
              final long orderNo = System.currentTimeMillis();
              order.setOrderNo(orderNo);
              order.setOrderQuantity(orderQuantity);
              int transQuantity = orderQuantity;
              final List<Ship> orderShips = new ArrayList<Ship>();
              for (final Ship ship : shipList) {
                    final int capacity = ship.getMaximumload();
                    transQuantity -= capacity;
                    if (transQuantity < 0) {//货运量小于船负载量
                          final int freeWeight = capacity-transQuantity;//空闲运量=船负载总量-货运量
                          final int goodsLoaded = capacity - freeWeight;
                          ship.setMaximumload(goodsLoaded);
                          ship.setFreeWeight(freeWeight);
                          orderShips.add(ship);
                          break;
                    } else if (transQuantity == 0) {//货运量等于船负载量
                          ship.setMaximumload(capacity);
                          ship.setFreeWeight(0);
                          orderShips.add(ship);
                          break;
                    } else {//货运量大于船负载量
                          ship.setMaximumload(capacity);
                          ship.setFreeWeight(0);
                          orderShips.add(ship);
                    }
              }
              if (transQuantity > 0) {//货运量超过船只负载总量  分批次运货
                   System.out.println("货运量超过船只负载总量  分批次运货");
                   return order;
              }
              final int size = orderShips.size();
              final Ship[] ships = new Ship[size];
              for (int i = 0; i < ships.length; i++) {
                    ships[i] = orderShips.get(i);
              }
              final OrderDetail details = new OrderDetail();
              details.setOrderNo(orderNo);
              details.setShips(ships);
              order.setOrderDetail(details);
        }
        return order;
  }


	/**
	 * 拆分
	 */
	public double[] split(double totalQuantity,String orderNo,int version,int splitOrMerge,double increateRootQuantity,double decreaseRootQuantity){
		double[] d=null;
		
		if(totalQuantity>Constant.middleShipMaxload_40&&splitOrMerge==1){
			d=new double[]{40, totalQuantity-40.0};
			System.out.println("按照订单编号来累计运送货物总量==待运货物总吨数100  则 完成一单货运任务");
			
		}
		if(totalQuantity>Constant.middleShipMaxload_50&&version==3){
		
			d=new double[]{50, 40, totalQuantity-50-40};
			System.out.println("按照订单编号来累计运送货物总量==待运货物总吨数100  则 完成一单货运任务");
		}
		if(totalQuantity>Constant.middleShipMaxload_60&&version==4){
			d=new double[]{60, 50, 40, totalQuantity-60-50-40};
			System.out.println("按照订单编号来累计运送货物总量==待运货物总吨数100  则 完成一单货运任务");
		}
		
		return d;
	}
	
	/**
	 * 合并
	 */
	public double[] merge(double totalQuantity,String orderNo,int version,int splitOrMerge,double increateRootQuantity,double decreaseRootQuantity){		
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
	public double[] increateRootQuantity(double totalQuantity,String orderNo,int version,int splitOrMerge,double increateRootQuantity,double decreaseRootQuantity){
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
	public double[] decreaseRootQuantity(double totalQuantity,String orderNo,int version,int splitOrMerge,double increateRootQuantity,double decreaseRootQuantity){
		double[] d=null;

		if(totalQuantity>=Constant.smallShipMaxload_10&&totalQuantity<=Constant.smallShipMaxload_30){
			d=new double[]{160, 100, 80, totalQuantity-160-100-80};
		}
		return d;
	}
}
