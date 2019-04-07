
public class Item implements Comparable<Item>{
	private String name;
	private Double manufcturingCost;
	private Double sellPrice;
	private Integer recorderPoint;
	private Integer recorderAmount;
	private Double temperature;
	/**
	 * create item
	 * @param data
	 * @throws CSVFormatException
	 */
	public Item(String[]data) throws CSVFormatException {
		// TODO Auto-generated constructor stub
		if (!(data.length==5||data.length==6)) {
			throw new CSVFormatException();
		}
		name=data[0];
		manufcturingCost=Double.parseDouble(data[1]);
		sellPrice=Double.parseDouble(data[2]);
		recorderPoint=Integer.parseInt(data[3]);
		recorderAmount=Integer.parseInt(data[4]);
		if (data.length==5) {
			temperature=null;
		}else {
			temperature=Double.parseDouble(data[5]);
		}
	}
	
	
	
	public String getName() {
		return name;
	}
	
	public Double getManufcturingCost() {
		return manufcturingCost;
	}
	
	public Double getSellPrice() {
		return sellPrice;
	}
	
	public Integer getRecorderPoint() {
		return recorderPoint;
	}
	
	public Integer getRecorderAmount() {
		return recorderAmount;
	}
	
	public Double getTemperature() {
		return temperature;
	}
	
	
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%s,%f,%f,%d,%d,%f", name,manufcturingCost,sellPrice,recorderPoint,recorderAmount,temperature);
	}

	/**
	 * implements Comparable
	 */
	@Override
	public int compareTo(Item o) {
		// TODO Auto-generated method stub
		double dif;
		if (o.getTemperature()==null&&getTemperature()!=null) {
			return -1;
		}
        if (getTemperature()==null&&o.getTemperature()!=null) {
			return 1;
		}
        if (getTemperature()==null&&o.getTemperature()==null) {
	        return 0;
        }
        dif=getTemperature()-o.getTemperature();

		return dif>0?1:dif<0?-1:0;
	}
	

}
