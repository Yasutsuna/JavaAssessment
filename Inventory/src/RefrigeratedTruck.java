/**
 * refrigerated truck
 * @author Administrator
 *
 */
public class RefrigeratedTruck extends Truck {
	private Double temperature=null;
	
	public RefrigeratedTruck() {
		// TODO Auto-generated constructor stub
		capacity=800;
	}

	@Override
	public double getCost() {
		// TODO Auto-generated method stub
		return 900+200*Math.pow(0.7, temperature/5)+super.getCost();
	}
	
	@Override
	public void addItem(Item item, int num) throws DeliveryException {
		// TODO Auto-generated method stub
		super.addItem(item, num);
		if (item.getTemperature()!=null) {
			if (temperature==null) {
				temperature=item.getTemperature();
			}else {
				if (temperature>item.getTemperature()) {
					temperature=item.getTemperature();
				}
			}
		}
	}

}
