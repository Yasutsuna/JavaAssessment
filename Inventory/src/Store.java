import java.util.ArrayList;
import java.util.Map.Entry;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

public class Store {
	private Double capital;
	private String name;
	private ArrayList<Entry<Item, Integer>> inventory;
	private Stock stock=new Stock();
	private DefaultTableModel defaultTableModel;//table model
	private JLabel lblCapital;//capital label
	
	public Store() {
		// TODO Auto-generated constructor stub
		inventory=stock.getInventory();
		capital=stock.getCapital();
	}
	
	
	public DefaultTableModel getDefaultTableModel() {
		return defaultTableModel;
	}
	public void setDefaultTableModel(DefaultTableModel defaultTableModel) {
		this.defaultTableModel = defaultTableModel;
		initTable();
	}
	/**
	 * init table
	 */
	private void initTable() {
		String[] headers=new String[] {"Name","ManufacturingCost","SellPrice","ReorderPoint","ReorderAmount","Temperature","Quantities"};
		for (String string : headers) {
			defaultTableModel.addColumn(string);
		}
		displayData();
		
	}
	/**
	 * display data
	 */
	private void displayData() {
		defaultTableModel.setRowCount(0);
		for (Entry<Item, Integer>entry:inventory) {
			defaultTableModel.addRow(getStringData(entry));
			
		}
	}
	/**
	 * inventory entry to string list
	 * @param entry
	 * @return
	 */
	private String[] getStringData(Entry<Item, Integer>entry) {
		Item item=entry.getKey();
		Integer num=entry.getValue();
		return new String[] {item.getName(),item.getManufcturingCost().toString(),item.getSellPrice().toString(),item.getRecorderPoint().toString(),
				item.getRecorderAmount().toString(),item.getTemperature()==null?"":item.getTemperature().toString(),num.toString()};
	}
	/**
	 * export manifest
	 */
	public void export() {
		stock.generateMnifest(true);
		inventory=stock.getInventory();
		setCapital(stock.getCapital());
		displayData();
	}
	/**
	 * load log from file
	 */
	public void loadLog() {
		JFileChooser jFileChooser=new JFileChooser("Data");
		jFileChooser.showOpenDialog(null);
		try {
			stock.loadLog(jFileChooser.getSelectedFile().getName());
		} catch (StockException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		inventory=stock.getInventory();
		setCapital(stock.getCapital());
		displayData();
	}





	public void setLblCapital(JLabel lblCapital) {
		this.lblCapital = lblCapital;
		lblCapital.setText("$"+capital);
	}




	public void setCapital(Double capital) {
		this.capital = capital;
		if (lblCapital!=null) {
			lblCapital.setText("$"+String.format("%.2f", capital));
		}
	}
	
	
	
	
	
	
	
	
	

}
