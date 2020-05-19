package com.infosys.shipment.shipInit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.infosys.shipment.pojo.Ship;
import com.infosys.shipment.util.Constant;

/**
 * 采用双锁机制，安全且在多线程情况下能保持高性能。
 * 
 * @author Tomwei_Qin
 *
 */

public final class ShipList implements Serializable {

	private static final long serialVersionUID = 566155905768880L;

	private List<Ship> shipList;

	private volatile static ShipList instance;

	private ShipList() {
		shipList = new ArrayList<Ship>(3); // 初始化3只船
		final Ship ship1 = new Ship();
		ship1.setShipNo("shipNo" + 1); // 船只编号
		ship1.setMaximumload(Constant.middleShipMaxload_60); // 船只最大负载量		
		ship1.setFreeWeight(Constant.middleShipMaxload_60);
		shipList.add(ship1);
		
		final Ship ship2 = new Ship();
		ship2.setShipNo("shipNo" + 2); // 船只编号
		ship2.setMaximumload(Constant.middleShipMaxload_40); // 船只最大负载量		
		ship2.setFreeWeight(Constant.middleShipMaxload_40);
		shipList.add(ship2);
		
		final Ship ship3 = new Ship();
		ship3.setShipNo("shipNo" + 3); // 船只编号
		ship3.setMaximumload(Constant.bigShipMaxload_100); // 船只最大负载量		
		ship3.setFreeWeight(Constant.bigShipMaxload_100);
		shipList.add(ship3);
	}

	public static ShipList getInstance() {
		if (instance == null) {
			synchronized (ShipList.class) {
				if (instance == null) {
					instance = new ShipList();
				}
			}
		}
		return instance;
	}

	public List<Ship> getShipList() {
		return shipList;
	}

}
