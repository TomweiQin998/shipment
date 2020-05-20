package com.infosys.shipment.service;

import java.util.ArrayList;
import java.util.List;

import com.infosys.shipment.pojo.Order;
import com.infosys.shipment.pojo.OrderDetail;
import com.infosys.shipment.pojo.Ship;
import com.infosys.shipment.shipInit.ShipList;
import com.sun.xml.internal.bind.annotation.OverrideAnnotationOf;

/**
 * 先判断货运总吨数大小，批次数量，然后再判断是否需要拆分或合并，按照从大型船到小型船这样方式分配货物运输 货物重量不能超过船只最大负载量
 * 
 * @author Tomwei_Qin
 *
 */
public class ShipmentImpl implements ShipmentInterface {
	
	/**
	 * 创建订单
	 */
	@Override
	public Order createOrder(int totalQuantity, int version, int splitOrMerge, int increateRootQuantity,
			int decreaseRootQuantity) {
		Order order = null;
		final ShipList instance = ShipList.getInstance();
		final List<Ship> shipList = instance.getShipList();
		final long orderNo = System.currentTimeMillis();
		//System.out.println("订单编号orderNo："+orderNo);
		order = new Order();
		
		int sumQuantity = totalQuantity + increateRootQuantity - decreaseRootQuantity;
		System.out.println("待遇货物总吨数："+sumQuantity);
		if (sumQuantity > 0 && sumQuantity <=590 && version==1) {//11只船总运量590吨所有船装满
		

			order.setOrderNo(orderNo);
			order.setOrderQuantity(sumQuantity);
			int transQuantity = sumQuantity;
			final List<Ship> orderShips = new ArrayList<Ship>();
			for (final Ship ship : shipList) {
				final int capacity = ship.getMaximumload();
				transQuantity -= capacity;
				if (transQuantity < 0) {// 货运量小于船负载量
					final int freeWeight =  - transQuantity;// 空闲运量=货运量-船负载量
					final int goodsLoaded = capacity - freeWeight;
					ship.setMaximumload(goodsLoaded);
					ship.setFreeWeight(freeWeight);
					orderShips.add(ship);
					break;
				} else if (transQuantity == 0) {// 货运量等于船负载量
					ship.setMaximumload(capacity);
					ship.setFreeWeight(0);
					orderShips.add(ship);
					break;
				} else {// 货运量大于船负载量
					ship.setMaximumload(capacity);
					ship.setFreeWeight(0);
					orderShips.add(ship);
				}
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
		}else if (sumQuantity > 590 && version > 1) {//11只船总运量590吨所有船装满  则需要 分批次运货 version批次
			System.out.println("货运量超过船只负载总量  分批次运货");

			order.setOrderNo(orderNo);
			order.setOrderQuantity(sumQuantity);
			int transQuantity = sumQuantity;
			for(int k=0;k<version;k++){		
			final List<Ship> orderShips = new ArrayList<Ship>();
			for (final Ship ship : shipList) {
				final int capacity = ship.getMaximumload();
				transQuantity -= capacity;
				if (transQuantity < 0) {// 货运量小于船负载量
					final int freeWeight =  - transQuantity;// 空闲运量=货运量-船负载量
					final int goodsLoaded = capacity - freeWeight;
					ship.setMaximumload(goodsLoaded);
					ship.setFreeWeight(freeWeight);
					orderShips.add(ship);
					break;
				} else if (transQuantity == 0) {// 货运量等于船负载量
					ship.setMaximumload(capacity);
					ship.setFreeWeight(0);
					orderShips.add(ship);
					break;
				} else {// 货运量大于船负载量
					ship.setMaximumload(capacity);
					ship.setFreeWeight(0);
					orderShips.add(ship);
				}
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
		}
		return order;
	}

	/**
	 * 拆分
	 */
	@Override
	public Order split(int totalQuantity, int version, int splitOrMerge, int increateRootQuantity,
			int decreaseRootQuantity) {
		Order order = null;
		order = createOrder(totalQuantity, version, splitOrMerge, increateRootQuantity, decreaseRootQuantity);
		OrderDetail orderDetail = order.getOrderDetail();
		long orderNo1 = orderDetail.getOrderNo();
		//System.out.println("货运量订单号:" + orderNo1);

		Ship[] ships = orderDetail.getShips();
		Ship ship = null;
		for (int i = 0; i < ships.length; i++) {
			ship = ships[i];
			
			String ShipNo = ship.getShipNo();
			//System.out.println("船的编号:" + ShipNo);

			double maximumload = ship.getMaximumload();
			//System.out.println("最大货运量:" + maximumload);

			double freeWeight = ship.getFreeWeight();
			//System.out.println("空闲货运量:" + freeWeight);

		}

		return order;
	}

	/**
	 * 合并
	 */
	@Override
	public Order merge(int totalQuantity, int version, int splitOrMerge, int increateRootQuantity,
			int decreaseRootQuantity) {
		Order order = null;
		order = createOrder(totalQuantity, version, splitOrMerge, increateRootQuantity, decreaseRootQuantity);
		OrderDetail orderDetail = order.getOrderDetail();
		long orderNo1 = orderDetail.getOrderNo();
		//System.out.println("货运量订单号:" + orderNo1);

		Ship[] ships = orderDetail.getShips();
		Ship ship = null;
		for (int i = 0; i < ships.length; i++) {
			ship = ships[i];
			String ShipNo = ship.getShipNo();
			//System.out.println("船的编号:" + ShipNo);
			
			double maximumload = ship.getMaximumload();
			//System.out.println("最大货运量:" + maximumload);

			double freeWeight = ship.getFreeWeight();
			//System.out.println("空闲运量:" + freeWeight);

		}

		return order;
	}

	/**
	 * 添加货运根数据
	 */
	public Order increateRootQuantity(int totalQuantity, int version, int splitOrMerge, int increateRootQuantity,
			int decreaseRootQuantity) {
		Order order = null;
		order = createOrder(totalQuantity, version, splitOrMerge, increateRootQuantity,
				decreaseRootQuantity);
		OrderDetail orderDetail = order.getOrderDetail();
		long orderNo1 = orderDetail.getOrderNo();
		//System.out.println("货运量订单号:" + orderNo1);

		Ship[] ships = orderDetail.getShips();
		Ship ship = null;
		for (int i = 0; i < ships.length; i++) {
			ship = ships[i];
			String ShipNo = ship.getShipNo();
			//System.out.println("船的编号:" + ShipNo);
			
			double maximumload = ship.getMaximumload();
			//System.out.println("最大货运量:" + maximumload);

			double freeWeight = ship.getFreeWeight();
			//System.out.println("空闲运量:" + freeWeight);

		}

		return order;
	}

	/**
	 * 减少货运根数据
	 */
	@Override
	public Order decreaseRootQuantity(int totalQuantity, int version, int splitOrMerge, int increateRootQuantity,
			int decreaseRootQuantity) {
		Order order = null;
		order = createOrder(totalQuantity, version, splitOrMerge, increateRootQuantity,
				decreaseRootQuantity);

		OrderDetail orderDetail = order.getOrderDetail();
		long orderNo1 = orderDetail.getOrderNo();
		//System.out.println("货运量订单号:" + orderNo1);

		Ship[] ships = orderDetail.getShips();
		Ship ship = null;
		for (int i = 0; i < ships.length; i++) {
			ship = ships[i];
			String ShipNo = ship.getShipNo();
			//System.out.println("船的编号:" + ShipNo);
			
			double maximumload = ship.getMaximumload();
			//System.out.println("最大货运量:" + maximumload);

			double freeWeight = ship.getFreeWeight();
			//System.out.println("空闲运量:" + freeWeight);

		}
		return order;
	}

	public static void main(String[] args) {
		ShipmentImpl shipmentImpl = new ShipmentImpl();
		//拆分
//		Order order1 = (Order) shipmentImpl.split(300, 1, 1, 0, 0);//第一个参数是货运总吨数，第2个是货运次数，第3个是拆分与合并，第4个是新增货物总吨数，第5个是减少货运总吨数
//		OrderDetail orderDetail = order1.getOrderDetail();
//		long orderNo1 = orderDetail.getOrderNo();
//		System.out.println("货运订单号:" + orderNo1);
//
//		Ship[] ships = orderDetail.getShips();
//		Ship ship = null;
//		for (int i = 0; i < ships.length; i++) {
//			ship = ships[i];
//			String ShipNo = ship.getShipNo();
//			System.out.println("船的编号:" + ShipNo);
//			
//			double maximumload = ship.getMaximumload();
//			System.out.println("最大货运量:" + maximumload);
//
//			double freeWeight = ship.getFreeWeight();
//			System.out.println("空闲货运量:" + freeWeight);
//
//		}
//
//		//合并
//		Order order2 = (Order) shipmentImpl.merge(150, 1, 2, 0, 0);
//		OrderDetail orderDetail2 = order2.getOrderDetail();
//		long orderNo2 = orderDetail2.getOrderNo();
//		System.out.println("货运订单号:" + orderNo2);
//
//		Ship[] ships2 = orderDetail2.getShips();
//		Ship ship2 = null;
//		for (int i = 0; i < ships2.length; i++) {
//			ship2 = ships2[i];
//			String ShipNo = ship2.getShipNo();
//			System.out.println("船的编号:" + ShipNo);
//			
//			double maximumload = ship2.getMaximumload();
//			System.out.println("最大货运量:" + maximumload);
//
//			double freeWeight = ship2.getFreeWeight();
//			System.out.println("空闲货运量:" + freeWeight);
//
//		}
//
//		//新增货物
//		Order order3 = (Order) shipmentImpl.increateRootQuantity(100, 1, 1, 200, 0);
//		OrderDetail orderDetail3 = order3.getOrderDetail();
//		long orderNo3 = orderDetail3.getOrderNo();
//		System.out.println("货运订单号:" + orderNo3);
//
//		Ship[] ships3 = orderDetail3.getShips();
//		Ship ship3 = null;
//		for (int i = 0; i < ships3.length; i++) {
//			ship3 = ships3[i];
//			String ShipNo = ship3.getShipNo();
//			System.out.println("船的编号:" + ShipNo);
//
//			double maximumload = ship3.getMaximumload();
//			System.out.println("最大货运量:" + maximumload);
//
//			double freeWeight = ship3.getFreeWeight();
//			System.out.println("空闲货运量:" + freeWeight);
//
//		}

		//减少货物
		Order order4 = (Order) shipmentImpl.decreaseRootQuantity(300, 1, 1, 0, 100);
		OrderDetail orderDetail4 = order4.getOrderDetail();
		long orderNo4 = orderDetail4.getOrderNo();
		System.out.println("货运量订单号:" + orderNo4);

		Ship[] ships4 = orderDetail4.getShips();
		Ship ship4 = null;
		for (int i = 0; i < ships4.length; i++) {
			ship4 = ships4[i];
			
			String ShipNo = ship4.getShipNo();
			System.out.println("船的编号:" + ShipNo);

			double maximumload = ship4.getMaximumload();
			System.out.println("最大货运量:" + maximumload);

			double freeWeight = ship4.getFreeWeight();
			System.out.println("空闲货运量:" + freeWeight);

		}
		
		//超过所有船只运输货物总吨数590
		Order order5 = (Order) shipmentImpl.decreaseRootQuantity(800, 2, 1, 0, 0);
		OrderDetail orderDetail5 = order5.getOrderDetail();
		long orderNo5 = orderDetail5.getOrderNo();
		System.out.println("货运量订单号:" + orderNo5);

		Ship[] ships5 = orderDetail5.getShips();
		Ship ship5 = null;
		for (int i = 0; i < ships5.length; i++) {
			ship5 = ships5[i];
			
			String ShipNo = ship5.getShipNo();
			System.out.println("船的编号:" + ShipNo);

			double maximumload = ship5.getMaximumload();
			System.out.println("最大货运量:" + maximumload);

			double freeWeight = ship5.getFreeWeight();
			System.out.println("空闲货运量:" + freeWeight);

		}
		
	}

}
