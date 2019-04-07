import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

public class Stock {
	
	private HashMap<String,Item> itemMap=new HashMap<>();
	private ArrayList<Entry<Item, Integer>> inventory=new ArrayList<>();
	private ArrayList<ArrayList<Item>> orders=new ArrayList<>();
	private ArrayList<Manifest> manifests=new ArrayList<>();
	private Double capital=100000.0;
	private ArrayList<ArrayList<Entry<Item, Integer>>> logs=new ArrayList<>();
	
	public Stock() {
		// TODO Auto-generated constructor stub
		init();
	}
	/**
	 * init items
	 */
	private void init() {
		readItems();
	}
	/**
	 * read items from file
	 */
	private void readItems() {
		Csv csv=new Csv();
		ArrayList<String[]> arrayList=csv.readData("item_properties.csv");
		
		for (String[] strings : arrayList) {
			Item item;
			try {
				item = new Item(strings);
				itemMap.put(item.getName(), item);
				inventory.add(new SimpleEntry(item, 0));
			} catch (CSVFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	/**
	 * get orders
	 * @return
	 */
	public ArrayList<Item> reorder() {
		
		ArrayList<Item> order=new ArrayList<>();
		for (Entry<Item, Integer> entry:inventory) {
			
			if (entry.getKey().getRecorderPoint()>=entry.getValue()) {
				order.add(entry.getKey());
			}
		}
		Collections.sort(order);
		orders.add(order);
		return order;
		
	}
	/**
	 * generate manifest
	 * @param file
	 */
	public void generateMnifest(boolean file) {
		ArrayList<Item> order=reorder();
		Manifest manifest=new Manifest(order);
		manifests.add(manifest);
		capital-=manifest.getCost();
		manifest.loadBack(inventory);
		if (file) {
			Csv csv=new Csv();
			csv.export(manifest);
		}
		
	}
	/**
	 * load log from file
	 * @param file
	 * @throws StockException
	 */
	public void loadLog(String file) throws StockException {
		
		Csv csv=new Csv();
		ArrayList<String[]> datas=csv.readData(file);
		ArrayList<Entry<Item, Integer>> log=new ArrayList<>();
		for (String[] strings : datas) {
			Entry<Item, Integer> entry=new SimpleEntry(itemMap.get(strings[0]),Integer.parseInt(strings[1]));
			log.add(entry);
		}
		
		for (Entry<Item, Integer> entry : log) {
			capital+=entry.getKey().getSellPrice()*entry.getValue();
			for (Entry<Item, Integer> entry1 : inventory) {
				if (entry.getKey()==entry1.getKey()) {
					if (entry1.getValue()-entry.getValue()<0) {
						throw new StockException();
					}
					entry1.setValue(entry1.getValue()-entry.getValue());
				}
			}
		}
		
		logs.add(log);
		
	}
	
	

	public ArrayList<Entry<Item, Integer>> getInventory() {
		return inventory;
	}

	

	public Double getCapital() {
		return capital;
	}

	
	
	

}
