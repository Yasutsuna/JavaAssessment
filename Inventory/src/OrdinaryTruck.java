/**
 * ordinary truck
 * @author Administrator
 *
 */
public class OrdinaryTruck extends Truck {

	public OrdinaryTruck() {
		// TODO Auto-generated constructor stub
		capacity=1000;
	}
	@Override
	public double getCost() {
		// TODO Auto-generated method stub
		
		return 750+0.25*usedSpace()+super.getCost();
	}

}
