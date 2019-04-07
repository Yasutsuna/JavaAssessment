import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Map.Entry;

public abstract class Truck {
	public int capacity;
	public ArrayList<Entry<Item, Integer>> cargo=new ArrayList<>();
	/**
	 * ManufcturingCost
	 * @return
	 */
	public double getCost() {
		double cost=0;
		for (Entry<Item, Integer> entry : cargo) {
			cost+=entry.getKey().getManufcturingCost()*entry.getValue();
		}
		return cost;
		
	}
	/**
	 * truck space
	 * @return
	 */
	public int space() {
		return capacity-usedSpace();
	}
	/**
	 * truck used space
	 * @return
	 */
	public int usedSpace() {
		int count=0;
		for (Entry<Item, Integer> entry : cargo) {
			count+=entry.getValue();
			
		}
		return count;
	}
	/**
	 * add item
	 * @param item
	 * @param num
	 * @throws DeliveryException
	 */
	public void addItem(Item item,int num) throws DeliveryException {
		
		if (num>space()) {
			
			throw new DeliveryException();
		}
		SimpleEntry< Item, Integer> simpleEntry=new SimpleEntry<Item, Integer>(item, num);
		cargo.add(simpleEntry);
	}
	/**
	 * load back inventory
	 * @param inventory
	 */
public void loadBack(ArrayList<Entry<Item, Integer>> inventory) {
		for (Entry<Item, Integer> entry : inventory) {
			Entry<Item, Integer> entry2=getEntry(entry.getKey());
			if (entry2!=null) {
				entry.setValue(entry.getValue()+entry2.getValue());
			}
		}
	}

/**
 * get trucks entry from item
 * @param item
 * @return
 */
private Entry<Item, Integer> getEntry(Item item) {
	for (Entry<Item, Integer> entry : cargo) {
		if (entry.getKey()==item) {
			return entry;
		}
	}
	return null;
	
}

}
