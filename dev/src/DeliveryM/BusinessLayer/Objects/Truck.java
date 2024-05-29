package BusinessLayer.Objects;

public class Truck{

	private int truckId;
	private String model;
	private int truckWeight;
	private int maxWeight;
	private boolean isAvailable;
	public Truck(int idtruck,String model,int truckWeight,int maxWeight,boolean isAvailable){
		this.truckId =idtruck;
		this.model=model;
		this.truckWeight=truckWeight;
		this.maxWeight=maxWeight;
		this.isAvailable=isAvailable;
	
	}
	public int getTruckId(){return truckId;}
	public String getModel(){return model;}
	public int getTruckWeight(){return truckWeight;}
	public int getMaxWeight(){return maxWeight;}
	public boolean getIsAvailableTruck(){return isAvailable;}
	public void setIsAvailableTruck(boolean status){isAvailable=status;}



}