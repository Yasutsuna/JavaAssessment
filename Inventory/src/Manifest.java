import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Map.Entry;

public class Manifest {
	ArrayList<Truck> trucks=new ArrayList<>();
	public Manifest(ArrayList<Item> order) {
		// TODO Auto-generated constructor stub
		ArrayList<Entry<Item, Integer>> arrayList=copyitem(order);
		loading(arrayList);
		
	}
	/**
	 * loading item
	 * @param arrayList
	 */
	private void loading(ArrayList<Entry<Item, Integer>> arrayList) {
		while (!arrayList.isEmpty()) {
			Entry<Item, Integer> entry=arrayList.get(0);
			Truck truck=null;
			if (entry.getKey().getTemperature()==null) {
				truck=new OrdinaryTruck();
			}else {
				truck=new RefrigeratedTruck();
			}
			
			do {
				entry=arrayList.remove(0);
				
					try {
						truck.addItem(entry.getKey(), entry.getValue());
						
					} catch (DeliveryException e) {
						// TODO Auto-generated catch block
						try {
							SimpleEntry< Item, Integer> simpleEntry=new SimpleEntry<Item, Integer>(entry.getKey(), entry.getValue()-truck.space());
							arrayList.add(0, simpleEntry);
							
							truck.addItem(entry.getKey(), truck.space());
							
						} catch (DeliveryException e1) {
							// TODO Auto-generated catch block
							
						}
					}
				
				
			} while (!arrayList.isEmpty()&&truck.space()>0);
			
			trucks.add(truck);
			
			
		}
		
	}
	/**
	 * copy item
	 * @param order
	 * @return
	 */
	private ArrayList<Entry<Item, Integer>> copyitem(ArrayList<Item> order) {
		ArrayList<Entry<Item, Integer>> arrayList=new ArrayList<>();
		for (Item item: order) {
			SimpleEntry<Item, Integer> simpleEntry=new SimpleEntry<Item, Integer>(item, item.getRecorderAmount());
			arrayList.add(simpleEntry);
		}
		return arrayList;
	}
	
	public double getCost() {
		double cost=0;
		for (Truck truck : trucks) {
			cost+=truck.getCost();
			
		}
		
		return cost;
	}
	/**
	 * load back to inventory
	 * @param inventory
	 */
	public void loadBack(ArrayList<Entry<Item, Integer>> inventory) {
		for (Truck truck:trucks) {
			truck.loadBack(inventory);
		}
	}

	public ArrayList<Truck> getTrucks() {
		return trucks;
	}

	
	
	

}
