package com.infosys.shipment.service;

import java.util.ArrayList;
import java.util.List;

import com.infosys.shipment.pojo.Order;
import com.infosys.shipment.pojo.OrderDetail;
import com.infosys.shipment.pojo.Ship;
import com.infosys.shipment.shipInit.ShipList;


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
		order = new Order();		
		
		int sumQuantity = totalQuantity;
		System.out.println("待运货物总吨数："+sumQuantity);
		
		System.out.println("总运输批次："+version);		
	
		System.out.println("拆分或合并操作："+splitOrMerge);
		
		System.out.println("新增运货总吨数："+increateRootQuantity);
		
		System.out.println("减少运货总吨数："+increateRootQuantity);
		
		if (sumQuantity > 0 && sumQuantity <=600 && version==1) {//13只船总运量600吨所有船装满		

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
		}else if (sumQuantity > 600 && version > 1) {//13只船总运量600吨所有船装满  则需要 分批次运货 version批次
			System.out.println("货运量超过所有船只最大负载总量  分批次运货");

			order.setOrderNo(orderNo);
			order.setOrderQuantity(sumQuantity);
			int transQuantity = sumQuantity;
		
			final List<Ship> orderShips = new ArrayList<Ship>();
			for(int k=0;k<version;k++){		
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
		splitOrMerge=1;
		order = createOrder(totalQuantity, version, splitOrMerge, increateRootQuantity, decreaseRootQuantity);
		return order;
	}

	/**
	 * 合并
	 */
	@Override
	public Order merge(int totalQuantity, int version, int splitOrMerge, int increateRootQuantity,
			int decreaseRootQuantity) {
		Order order = null;
		splitOrMerge=2;
		order = createOrder(totalQuantity, version, splitOrMerge, increateRootQuantity, decreaseRootQuantity);

		return order;
	}

	/**
	 * 添加货运根数据
	 */
	public Order increateRootQuantity(int totalQuantity, int version, int splitOrMerge, int increateRootQuantity,
			int decreaseRootQuantity) {
		Order order = null;
		int sumTotalQuantity=totalQuantity+increateRootQuantity;
		order = createOrder(sumTotalQuantity, version, splitOrMerge, increateRootQuantity,
				decreaseRootQuantity);


		return order;
	}

	/**
	 * 减少货运根数据
	 */
	@Override
	public Order decreaseRootQuantity(int totalQuantity, int version, int splitOrMerge, int increateRootQuantity,
			int decreaseRootQuantity) {
		Order order = null;
		int sumTotalQuantity=totalQuantity+decreaseRootQuantity;
		order = createOrder(sumTotalQuantity, version, splitOrMerge, increateRootQuantity,
				decreaseRootQuantity);

		return order;
	}

	

}
