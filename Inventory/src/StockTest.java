import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;

public class StockTest {
	Stock stock;

	@Before
	public void setUp() throws Exception {
		stock=new Stock();
	}

	/**
	 * test generatemanifest
	 */
	@Test
	public void testGenerateMnifest() {
		stock.generateMnifest(false);
		assertTrue(Math.abs(stock.getCapital()-42717.88)<0.01);
	}

	/**
	 * test loadlog
	 * @throws StockException
	 */
	@Test
	public void testLoadLog() throws StockException {
		stock.generateMnifest(false);
		stock.loadLog("sales_log_0.csv");
		stock.generateMnifest(false);
		assertTrue(Math.abs(stock.getCapital()-27569.79)<0.01);
	}

	/**
	 * test getinventory
	 */
	@Test
	public void testGetInventory() {
		stock.generateMnifest(false);
		ArrayList<Entry<Item, Integer>> inventory=stock.getInventory();
		for (Entry<Item, Integer> entry : inventory) {
			assertTrue(entry.getKey().getRecorderAmount().equals(entry.getValue()));
		}
	}

	

	/**
	 * test getcapital
	 */
	@Test
	public void testGetCapital() {
		assertEquals(stock.getCapital(), (Double)100000.0);
		
	}


}
