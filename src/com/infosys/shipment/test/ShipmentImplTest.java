package com.infosys.shipment.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.infosys.shipment.pojo.Order;
import com.infosys.shipment.service.ShipmentImpl;

public class ShipmentImplTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCreateOrder() {
		fail("Not yet implemented");
	}

	@Test
	public void testSplit() {
		fail("Not yet implemented");
	}

	@Test
	public void testMerge() {
		fail("Not yet implemented");
	}

	@Test
	public void testIncreateRootQuantity() {
		fail("Not yet implemented");
	}

	@Test
	public void testDecreaseRootQuantity() {
		fail("Not yet implemented");
	}
	
	public static void main() {
		ShipmentImpl shipmentImpl=new ShipmentImpl();
		 Order order1=(Order)shipmentImpl.split(100,0,1,0,0);
		
		Order order2=(Order)shipmentImpl.merge(100,0,2,0,0);
		
		Order order3=(Order)shipmentImpl.increateRootQuantity(100,0,1,100,0);
		
		Order order4=(Order)shipmentImpl.decreaseRootQuantity(100,0,1,50,0);
		
		Order order5=(Order)shipmentImpl.createOrder(100,0,0,0,0);
		
	}

	

}
