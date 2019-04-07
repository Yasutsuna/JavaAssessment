import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

public class ManifestTest {

	ArrayList<Item> order;
	Manifest manifest;
	@Before
	public void setUp() throws Exception {
		Stock stock=new Stock();
		order=stock.reorder();
		manifest=new Manifest(order);
		
	}

	/**
	 * test getcost
	 */
	@Test
	public void testGetCost() {
		
		assertTrue(Math.abs(manifest.getCost()-57282.12)<0.01);
	}

	/**
	 * test loadback
	 */
	@Test
	public void testLoadBack() {
		ArrayList<Entry<Item, Integer>> inventory=new ArrayList<>();
		for (Item item : order) {
			SimpleEntry<Item, Integer> simpleEntry=new SimpleEntry<Item, Integer>(item, 0);
		}
		manifest.loadBack(inventory);
		for (Entry<Item, Integer> entry : inventory) {
			assertTrue(entry.getKey().getRecorderAmount()==entry.getValue());
		}
		
	}

	/**
	 * test gettrucks
	 */
	@Test
	public void testGetTrucks() {
		int count=0,count1=0;
		ArrayList<Truck> trucks=manifest.trucks;
		for (Truck truck : trucks) {
			if (truck instanceof OrdinaryTruck) {
				count++;
			}else {
				count1++;
			}
		}
		assertTrue(count==3);
		assertTrue(count1==8);
	}

	

}
