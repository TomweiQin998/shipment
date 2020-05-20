package com.infosys.shipment.test;

import com.infosys.shipment.pojo.Order;
import com.infosys.shipment.pojo.OrderDetail;
import com.infosys.shipment.pojo.Ship;
import com.infosys.shipment.service.ShipmentImpl;

public class ShipmentImplTest {

	public static void main(String[] args) {
		ShipmentImpl shipmentImpl = new ShipmentImpl();
		Order order1 = (Order) shipmentImpl.split(100, 0, 1, 0, 0);
		OrderDetail orderDetail = order1.getOrderDetail();
		long orderNo1 = orderDetail.getOrderNo();
		System.out.println("货运量订单号:" + orderNo1);

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

		Order order2 = (Order) shipmentImpl.merge(150, 0, 2, 0, 0);
		OrderDetail orderDetail2 = order2.getOrderDetail();
		long orderNo2 = orderDetail2.getOrderNo();
		System.out.println("货运量订单号:" + orderNo2);

		Ship[] ships2 = orderDetail.getShips();
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

		Order order3 = (Order) shipmentImpl.increateRootQuantity(100, 0, 1, 100, 0);
		OrderDetail orderDetail3 = order3.getOrderDetail();
		long orderNo3 = orderDetail3.getOrderNo();
		System.out.println("货运量订单号:" + orderNo3);

		Ship[] ships3 = orderDetail.getShips();
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

		Order order4 = (Order) shipmentImpl.decreaseRootQuantity(100, 0, 1, 50, 0);
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

		Order order5 = (Order) shipmentImpl.createOrder(100, 0, 0, 0, 0);
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
