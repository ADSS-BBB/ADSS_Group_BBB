package DeliveryM.BusinessLayer.Objects;

public class Truck{

	private int number;
	private String model;
	private int truckWeight;
	private int maxWeight;
	private int availableweight;
	private boolean isAvailable;

	public Truck(int number,String model,int truckWeight,int maxWeight){
		this.number=number;
		this.model=model;
		this.truckWeight=truckWeight;
		this.maxWeight=maxWeight;
		this.availableweight=maxWeight-truckWeight;
		this.isAvailable=true;

	}
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getModel() {
		return model;
	}


	public int getTruckWeight() {
		return truckWeight;
	}

	public void setTruckWeight(int truckWeight) {
		this.truckWeight = truckWeight;
	}

	public int getMaxWeight() {

		return maxWeight;
	}

	public void setMaxWeight(int maxWeight) {
		this.maxWeight = maxWeight;
	}

	public boolean checkweight(){
		if(truckWeight>maxWeight)
			return false;
		return true;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean is) {
		this.isAvailable = is;

	}



}
