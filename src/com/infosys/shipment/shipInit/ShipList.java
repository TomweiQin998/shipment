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
		shipList = new ArrayList<Ship>(3); // 初始化13只船
		final Ship ship1 = new Ship();
		ship1.setShipNo("shipNo" + 1); // 船只编号
		ship1.setMaximumload(Constant.bigShipMaxload_160); // 船只最大负载量		
		ship1.setFreeWeight(Constant.bigShipMaxload_160);
		shipList.add(ship1);
		
		final Ship ship2 = new Ship();
		ship2.setShipNo("shipNo" + 2); // 船只编号
		ship2.setMaximumload(Constant.bigShipMaxload_100); // 船只最大负载量		
		ship2.setFreeWeight(Constant.bigShipMaxload_100);
		shipList.add(ship2);
		
		final Ship ship3 = new Ship();
		ship3.setShipNo("shipNo" + 3); // 船只编号
		ship3.setMaximumload(Constant.bigShipMaxload_80); // 船只最大负载量		
		ship3.setFreeWeight(Constant.bigShipMaxload_80);
		shipList.add(ship3);
		
		final Ship ship4 = new Ship();
		ship4.setShipNo("shipNo" + 4); // 船只编号
		ship4.setMaximumload(Constant.middleShipMaxload_60); // 船只最大负载量		
		ship4.setFreeWeight(Constant.middleShipMaxload_60);
		shipList.add(ship4);
		
		final Ship ship5 = new Ship();
		ship5.setShipNo("shipNo" + 5); // 船只编号
		ship5.setMaximumload(Constant.middleShipMaxload_50); // 船只最大负载量		
		ship5.setFreeWeight(Constant.middleShipMaxload_50);
		shipList.add(ship5);
		
		final Ship ship6 = new Ship();
		ship6.setShipNo("shipNo" + 6); // 船只编号
		ship6.setMaximumload(Constant.middleShipMaxload_50); // 船只最大负载量		
		ship6.setFreeWeight(Constant.middleShipMaxload_50);
		shipList.add(ship6);
		
		final Ship ship7 = new Ship();
		ship7.setShipNo("shipNo" + 7); // 船只编号
		ship7.setMaximumload(Constant.middleShipMaxload_40); // 船只最大负载量		
		ship7.setFreeWeight(Constant.middleShipMaxload_40);
		shipList.add(ship7);
		
		final Ship ship8 = new Ship();
		ship8.setShipNo("shipNo" + 8); // 船只编号
		ship8.setMaximumload(Constant.smallShipMaxload_30); // 船只最大负载量		
		ship8.setFreeWeight(Constant.smallShipMaxload_30);
		shipList.add(ship8);
		
		final Ship ship9 = new Ship();
		ship9.setShipNo("shipNo" + 9); // 船只编号
		ship9.setMaximumload(Constant.smallShipMaxload_25); // 船只最大负载量		
		ship9.setFreeWeight(Constant.smallShipMaxload_25);
		shipList.add(ship9);
		
		final Ship ship10 = new Ship();
		ship10.setShipNo("shipNo" + 10); // 船只编号
		ship10.setMaximumload(Constant.smallShipMaxload_20); // 船只最大负载量		
		ship10.setFreeWeight(Constant.smallShipMaxload_20);
		shipList.add(ship10);
		
		final Ship ship11 = new Ship();
		ship11.setShipNo("shipNo" + 11); // 船只编号
		ship11.setMaximumload(Constant.smallShipMaxload_15); // 船只最大负载量		
		ship11.setFreeWeight(Constant.smallShipMaxload_15);
		shipList.add(ship11);
		
		final Ship ship12 = new Ship();
		ship12.setShipNo("shipNo" + 12); // 船只编号
		ship12.setMaximumload(Constant.smallShipMaxload_10); // 船只最大负载量		
		ship12.setFreeWeight(Constant.smallShipMaxload_10);
		shipList.add(ship12);
		
		final Ship ship13 = new Ship();
		ship13.setShipNo("shipNo" + 13); // 船只编号
		ship13.setMaximumload(Constant.smallShipMaxload_10); // 船只最大负载量		
		ship13.setFreeWeight(Constant.smallShipMaxload_10);
		shipList.add(ship13);
		
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
