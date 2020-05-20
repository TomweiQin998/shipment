package com.infosys.shipment.test;

import com.infosys.shipment.pojo.Order;
import com.infosys.shipment.pojo.OrderDetail;
import com.infosys.shipment.pojo.Ship;
import com.infosys.shipment.service.ShipmentImpl;

/**
 * 测试
 * @author Tomwei_Qin
 *
 */
public class ShipmentImplTest {

	public static void main(String[] args) {
		ShipmentImpl shipmentImpl = new ShipmentImpl();
		//拆分
		Order order1 = (Order) shipmentImpl.split(300, 1, 1, 0, 0);//第一个参数是货运总吨数，第2个是货运次数，第3个是拆分与合并，第4个是新增货物总吨数，第5个是减少货运总吨数
		OrderDetail orderDetail = order1.getOrderDetail();
		long orderNo1 = orderDetail.getOrderNo();
		System.out.println("货运订单号:" + orderNo1);

		Ship[] ships = orderDetail.getShips();
		Ship ship = null;
		for (int i = 0; i < ships.length; i++) {
			ship = ships[i];
			String ShipNo = ship.getShipNo();
			System.out.println("船的编号:" + ShipNo);
			
			double maximumload = ship.getMaximumload();
			System.out.println("最大货运量:" + maximumload);

			double freeWeight = ship.getFreeWeight();
			System.out.println("空闲货运量:" + freeWeight);

		}

		//合并
		Order order2 = (Order) shipmentImpl.merge(150, 1, 2, 0, 0);
		OrderDetail orderDetail2 = order2.getOrderDetail();
		long orderNo2 = orderDetail2.getOrderNo();
		System.out.println("货运订单号:" + orderNo2);

		Ship[] ships2 = orderDetail2.getShips();
		Ship ship2 = null;
		for (int i = 0; i < ships2.length; i++) {
			ship2 = ships2[i];
			String ShipNo = ship2.getShipNo();
			System.out.println("船的编号:" + ShipNo);
			
			double maximumload = ship2.getMaximumload();
			System.out.println("最大货运量:" + maximumload);

			double freeWeight = ship2.getFreeWeight();
			System.out.println("空闲货运量:" + freeWeight);

		}

		//新增货物
		Order order3 = (Order) shipmentImpl.increateRootQuantity(100, 1, 1, 200, 0);
		OrderDetail orderDetail3 = order3.getOrderDetail();
		long orderNo3 = orderDetail3.getOrderNo();
		System.out.println("货运订单号:" + orderNo3);

		Ship[] ships3 = orderDetail3.getShips();
		Ship ship3 = null;
		for (int i = 0; i < ships3.length; i++) {
			ship3 = ships3[i];
			String ShipNo = ship3.getShipNo();
			System.out.println("船的编号:" + ShipNo);

			double maximumload = ship3.getMaximumload();
			System.out.println("最大货运量:" + maximumload);

			double freeWeight = ship3.getFreeWeight();
			System.out.println("空闲货运量:" + freeWeight);

		}

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
