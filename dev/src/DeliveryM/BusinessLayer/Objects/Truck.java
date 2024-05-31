package DeliveryM.BusinessLayer.Objects;

public class Truck{

	private int number;
	private String model;
	private int truckWeight;
	private int maxWeight;
	private boolean isAvailable;
	public Truck(int number,String model,int truckWeight,int maxWeight,boolean isAvailable){
		this.number=number;
		this.model=model;
		this.truckWeight=truckWeight;
		this.maxWeight=maxWeight;
		this.isAvailable=isAvailable;
	
	}
	public int getNumber(){return  this.number;}
	public String getModel(){return model;}
	public int getTruckWeight(){return truckWeight;}
	public int getMaxWeight(){return maxWeight;}
	public boolean getIsAvailableTruck(){return isAvailable;}
	public void setIsAvailableTruck(){isAvailable=!isAvailable;}



}