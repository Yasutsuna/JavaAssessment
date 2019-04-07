import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ItemTest {

	@Before
	public void setUp() throws Exception {
	}

	/**
	 * test item exception
	 */
	@Test
	public void testItemException() {
		String data="rice,2,3,225,300";
		try {
			Item item=new Item(data.split(","));
		} catch (Exception e) {
			// TODO: handle exception
			fail("Throw Exception!");
		}
		
		String data1="rice,2,3,225";
		try {
			Item item=new Item(data1.split(","));
			fail("Not Throw Exception!");
		} catch (Exception e) {
			// TODO: handle exception
			
		}
	}
	
	/**
	 * test item get method
	 * @throws CSVFormatException
	 */
	@Test
	public void testGet() throws CSVFormatException {
		String data="rice,2,3,225,300,10";
		Item item=new Item(data.split(","));
		assertEquals(item.getName(), "rice");
		assertEquals(item.getManufcturingCost(), (Double)2.0);
		assertEquals(item.getSellPrice(),  (Double)3.0);
		assertEquals(item.getRecorderPoint(),  (Integer)225);
		assertEquals(item.getRecorderAmount(), (Integer)300);
		assertEquals(item.getTemperature(),  (Double)10.0);
	}
	

	/**
	 * test compareto
	 * @throws CSVFormatException
	 */
	@Test
	public void testCompareTo() throws CSVFormatException {
		String data1="rice,2,3,225,300";
		String data2="rice,2,3,225,300,10";
		String data3="rice,2,3,225,300,2";
		Item item1=new Item(data1.split(","));
		Item item2=new Item(data2.split(","));
		Item item3=new Item(data3.split(","));
		
		assertEquals(item1.compareTo(item2), 1);
		assertEquals(item3.compareTo(item2),-1);
	}

}
