public class Truck{

	private int idtruck;
	private String model;
	private int truckWeight;
	private int maxWeight;
	private boolean isAvailable;
	public Truck(int idtruck,String model,int truckWeight,int maxWeight,boolean isAvailable){
		this.idtruck=idtruck;
		this.model=model;
		this.truckWeight=truckWeight;
		this.maxWeight=maxWeight
		this.isAvailable=isAvailable;
	
	}
	public int getIdtruck(){return idtruck;}
	public String getmodel(){return model;}
	public int getruckWeight(){return truckWeight;}
	public int getmaxWeight(){return maxWeight;}
	public boolean getisAvailableTruck(){return isAvailable;}
	public void setisAvailableTruck(boolean status){isAvailable=status;}



}