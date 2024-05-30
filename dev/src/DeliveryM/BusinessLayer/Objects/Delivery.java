
package BusinessLayer.Objects;
//import   java.lang;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.PrimitiveIterator;

public class Delivery {
	
	private int id;
	private String date;
	private String time;
	private int truckNumber;
	private Location source;//its a location
	private List<Location> destinations;//its a location not string
	private int truckWeight;
	//private Map<String,Integer> items; //name , quantity according to moodle-> it won't work, string has to be unique
	//get name and get quantity :)
	//private List<Item> itemsDec;
	private Map<Integer,Integer>itemsQuantity;//id ->item, quantity
	private boolean status;


	public Delivery(int id , String date , String time , int truckNumber , Location source, List<Location> destinations, int truckWeight, Map<Integer,Integer> itemsDec, boolean status ){
		this.id=id;
		this.date=date;
		this.time=time;
		this.truckNumber=truckNumber;
		this.source=source;
		this.destinations=destinations;
		this.truckWeight=truckWeight;
		this.itemsQuantity=itemsQuantity;//need to import item
		//this.itemsDec=itemsDec;
		this.status=status;//or delete it from the constructor and automatic false

	
	}
	public int getId(){return id;}
	public String getDate(){return date;}
	public String getTime(){return time;}
	public int getTruckNumber(){return truckNumber;}
	public Location getSource(){return source;}
	public List<Location> getDestinations(){return destinations;}
	public int getTruckWeight(){return truckWeight;}
	public Map<Integer,Integer>  getItemsDec(){return itemsQuantity;}
	public boolean getStatusD(){return status;}
	public void setStatusD(boolean status1){status=status1;}



}