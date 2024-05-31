
package DeliveryM.BusinessLayer.Objects;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import  java.time.Clock;

public class Delivery {
	
	private int id;
	private Date date;
	private int truckWeight;
	private int truckNumber;
	private Location source;//it's a location
	private List<Location> destinations;
	private List<Item> items;
	//private boolean status;
	private int driverid;
	private Date clock;

	public Delivery(int id , Date date  , int truckNumber , Location source, List<Location> destinations, int truckWeight, List<Item> items ){
		this.id=id;
		this.date=date;
		//this.time=time;
		this.truckNumber=truckNumber;
		this.source=source;
		this.destinations=destinations;
		this.truckWeight=truckWeight;
		this.items=items;
		//this.itemsDec=itemsDec;
		//this.status=status;//or delete it from the constructor and automatic false
		this.clock=null;

	
	}
	public int getId(){return id;}
	public Date getDate(){return date;}
	public void setDate(Date date){
		this.date=date;
	}
	public void updaterClock(){
		//this.clock=java.time.LocalTime.now();
	}
	//public String getTime(){return time;}
	public int getTruckNumber(){return truckNumber;}
	public Location getSource(){return source;}
	public List<Location> getDestinations(){return destinations;}
	public int getTruckWeight(){return truckWeight;}
	public List<Item>  getItems(){return this.items;}
//	public boolean getStatusD(){return status;}
//	public void setStatusD(boolean status1){status=status1;}
	public void addDestination(Location des){
		destinations.add(des);
	}
	public void removeDest(Location dest){
		destinations.remove(dest);
		for(Item i :items){
			if(i.getDestinationid()==dest.getlocationid()){
				items.remove(i);
			}
		}


	}
	public  int getDriverId(){
		return  this.driverid;
	}

	public void setdriver(int driver) {

	}
}