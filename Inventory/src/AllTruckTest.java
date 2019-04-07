import static org.junit.Assert.*;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

public class AllTruckTest {

	Truck truck1;
	Truck truck2;
	String data1="rice,2,3,225,300";
	String data2="frozen vegetable mix,5,8,225,325,-12";
	Item item1,item2;
	
	@Before
	public void setUp() throws Exception {
		
		truck2=new RefrigeratedTruck();
		truck1=new OrdinaryTruck();
		item1=new Item(data1.split(","));
		item2=new Item(data2.split(","));
		
	}

	/**
	 * test truck cost
	 * @throws DeliveryException
	 */
	@Test
	public void testGetCost() throws DeliveryException {
		truck1.addItem(item1, 2);
		assertTrue(truck1.getCost()==754.5);
		truck2.addItem(item2, 2);
		assertTrue(Math.abs(truck2.getCost()-1380.75)<0.01);
		
		
	}

	/**
	 * test truck space
	 * @throws DeliveryException
	 */
	@Test
	public void testSpace() throws DeliveryException {
		truck1.addItem(item1, 2);
		assertTrue(truck1.space()==998);
		truck2.addItem(item2, 2);
		assertTrue(truck2.space()==798);
	}

	/**
	 * test usedspace
	 * @throws DeliveryException
	 */
	@Test
	public void testUsedSpace() throws DeliveryException {
		truck1.addItem(item1, 2);
		assertTrue(truck1.usedSpace()==2);
		truck2.addItem(item2, 2);
		assertTrue(truck2.usedSpace()==2);
	}

	/**
	 * deliveryexception test
	 */
	@Test
	public void testAddItem() {
		try {
			truck1.addItem(item1, 2);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			truck1.addItem(item1, 2000);
			fail("Not throw exception");
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		
		try {
			truck2.addItem(item2, 2);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			truck2.addItem(item2, 2000);
			fail("Not throw exception");
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		
	}

	/**
	 * test load back in
	 * @throws DeliveryException
	 */
	@Test
	public void testLoadBack() throws DeliveryException {
		truck1.addItem(item1, 2);
		ArrayList<Entry<Item, Integer>> inventory=new ArrayList<>();
		SimpleEntry<Item, Integer> simpleEntry=new SimpleEntry<Item, Integer>(item1, 0);
		inventory.add(simpleEntry);
		truck1.loadBack(inventory);
		assertTrue(inventory.get(0).getValue()==2);
	}

}
