import static org.junit.Assert.*;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.junit.Before;
import org.junit.Test;

public class StoreTest {

	
	Store store;
	@Before
	public void setUp() throws Exception {
		store=new Store();
		
	}



	/**
	 * test table model
	 */
	@Test
	public void testSetDefaultTableModel() {
		JTable jTable=new JTable();
		DefaultTableModel defaultTableModel=(DefaultTableModel) jTable.getModel();
		store.setDefaultTableModel(defaultTableModel);
		assertTrue(defaultTableModel.getColumnCount()==7);
	}

	/**
	 * test export
	 */
	@Test
	public void testExport() {
		Stock stock=new Stock();
		stock.generateMnifest(false);
		assertTrue(Math.abs(stock.getCapital()-42717.88)<0.01);
	}

	/**
	 * test load log
	 * @throws StockException
	 */
	@Test
	public void testLoadLog() throws StockException {
		Stock stock=new Stock();
		stock.generateMnifest(false);
		stock.loadLog("sales_log_0.csv");
		stock.generateMnifest(false);
		assertTrue(Math.abs(stock.getCapital()-27569.79)<0.01);
	}



	/**
	 * test setlblcapital
	 */
	@Test
	public void testSetLblCapital() {
		JLabel jLabel=new JLabel();
		store.setLblCapital(jLabel);
		assertEquals(jLabel.getText(), "$100000.0");
		
	}
	
	/**
	 * test display data
	 */
	@Test
	public void testDisplayData() {
		JTable jTable=new JTable();
		DefaultTableModel defaultTableModel=(DefaultTableModel) jTable.getModel();
		store.setDefaultTableModel(defaultTableModel);
		assertTrue(defaultTableModel.getRowCount()==24);
		
	}

	
}
