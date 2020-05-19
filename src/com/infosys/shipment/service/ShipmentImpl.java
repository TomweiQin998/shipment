package com.infosys.shipment.service;


import java.util.ArrayList;
import java.util.List;

import com.infosys.shipment.pojo.Order;
import com.infosys.shipment.pojo.OrderDetail;
import com.infosys.shipment.pojo.Ship;
import com.infosys.shipment.shipInit.ShipList;


/**
 * 先判断货运总吨数大小，批次数量，然后再判断是否需要拆分或合并，按照从大型船到小型船这样方式分配货物运输
 * 货物重量不能超过船只最大负载量
 * @author Tomwei_Qin
 *
 */
public class ShipmentImpl implements ShipmentInterface {
	
	public Order createOrder(int totalQuantity,int version,int splitOrMerge,int increateRootQuantity,int decreaseRootQuantity) {
        Order order = null;
        if (totalQuantity > 0) {
              final ShipList instance = ShipList.getInstance();
              final List<Ship> shipList = instance.getShipList();
              order = new Order();
              final long orderNo = System.currentTimeMillis();
              order.setOrderNo(orderNo);
              order.setOrderQuantity(totalQuantity);
              int transQuantity = totalQuantity;
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
                   Ship ship=null;
                   for (int k=0;k<shipList.size();k++) {
                	   ship=new Ship();
                       ship.setMaximumload(shipList.get(k).getMaximumload());
                       ship.setFreeWeight(0);
                       ship.setVersion(k);
                   orderShips.add(ship);
                   }                   
                   
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
	public Order split(int totalQuantity,int version,int splitOrMerge,int increateRootQuantity,int decreaseRootQuantity){
		Order order=null;
		order=createOrder(totalQuantity, version, splitOrMerge, increateRootQuantity, decreaseRootQuantity);
		OrderDetail orderDetail=order.getOrderDetail();
		long orderNo1= orderDetail.getOrderNo();
		   System.out.println("货运量订单号:"+orderNo1); 
		
		Ship[] ships=orderDetail.getShips();
		Ship ship=null;
		for(int i=0;i<ships.length;i++){
			 ship=ships[i];	 
		   
		   double maximumload=ship.getMaximumload();
		   System.out.println("最大货运量:"+maximumload); 
		   
		   double freeWeight=ship.getFreeWeight();
		   System.out.println("空闲货运量:"+freeWeight); 
		   
		}

		   return order;
	}
	
	/**
	 * 合并
	 */
	public Order merge(int totalQuantity,int version,int splitOrMerge,int increateRootQuantity,int decreaseRootQuantity){		
		Order order=null;
		order=createOrder(totalQuantity, version, splitOrMerge, increateRootQuantity, decreaseRootQuantity);
		OrderDetail orderDetail=order.getOrderDetail();
		long orderNo1= orderDetail.getOrderNo();
		   System.out.println("货运量订单号:"+orderNo1); 
		
		Ship[] ships=orderDetail.getShips();
		Ship ship=null;
		for(int i=0;i<ships.length;i++){
			 ship=ships[i];	 
		   
		   double maximumload=ship.getMaximumload();
		   System.out.println("最大货运量:"+maximumload); 
		   
		   double freeWeight=ship.getFreeWeight();
		   System.out.println("空闲运量:"+freeWeight); 
		   
		}

	
		return order;
	}
	
	
	/**
	 * 添加货运根数据
	 */
	public Order increateRootQuantity(int totalQuantity, int version, int splitOrMerge, int increateRootQuantity,
			int decreaseRootQuantity){
		Order order=null;
		order=createOrder(totalQuantity+decreaseRootQuantity, version, splitOrMerge, increateRootQuantity, decreaseRootQuantity);
		OrderDetail orderDetail=order.getOrderDetail();
		long orderNo1= orderDetail.getOrderNo();
		   System.out.println("货运量订单号:"+orderNo1); 
		
		Ship[] ships=orderDetail.getShips();
		Ship ship=null;
		for(int i=0;i<ships.length;i++){
			 ship=ships[i];	 
		   
		   double maximumload=ship.getMaximumload();
		   System.out.println("最大货运量:"+maximumload); 
		   
		   double freeWeight=ship.getFreeWeight();
		   System.out.println("空闲运量:"+freeWeight); 
		   
		}

	
		return order;
	}
	
	/**
	 * 减少货运根数据
	 */
	public Order decreaseRootQuantity(int totalQuantity, int version, int splitOrMerge, int increateRootQuantity,
			int decreaseRootQuantity){
		Order order=null;
		order=createOrder(totalQuantity-decreaseRootQuantity, version, splitOrMerge, increateRootQuantity, decreaseRootQuantity);
	
		OrderDetail orderDetail=order.getOrderDetail();
		long orderNo1= orderDetail.getOrderNo();
		   System.out.println("货运量订单号:"+orderNo1); 
		
		Ship[] ships=orderDetail.getShips();
		Ship ship=null;
		for(int i=0;i<ships.length;i++){
			 ship=ships[i];	 
		   
		   double maximumload=ship.getMaximumload();
		   System.out.println("最大货运量:"+maximumload); 
		   
		   double freeWeight=ship.getFreeWeight();
		   System.out.println("空闲运量:"+freeWeight); 
		   
		}	
		return order;
	}

}
