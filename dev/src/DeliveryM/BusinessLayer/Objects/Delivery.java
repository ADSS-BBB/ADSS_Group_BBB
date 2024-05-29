package DeliveryM.BusinessLayer.Objects;

import java.util.HashMap;
import java.util.List;

public class Delivery {
	
	private int id;
	private String date;
	private String time;
	private String source;
	private List<String> destinations;
	private Driver driver;
	private int driverid;
	private Truck truck;
	private int truckNumber;
	private int truckWeight;
	private HashMap<String,Integer> items; //name , quantity according to moodle
	private boolean status;


	public Delivery(int id , String date , String time  , String source, List<String> destinations, int driverid, int truckNumber, int truckWeight, HashMap<String,Integer> items, boolean status ){
		this.id=id;
		this.date=date;
		this.time=time;
		this.truckNumber=truckNumber;
		this.driverid=driverid;
		this.source=source;
		this.destinations=destinations;
		this.truckWeight=truckWeight;
		this.items=items;//need to import item
		this.status=status;//or delete it from the constructor and automatic false

	
	}
	public int getid(){
		return id;
	}
	public void setid(int id){
		this.id=id;
	}
	public String getdate(){
		return date;
	}
	public void setdate(String date){
		this.date=date;
	}

	public String gettime(){
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	public String getsource(){
		return source;
	}
	public void setsource(String source) {
		this.source = source;
	}

	public List<String> getdestinations(){return destinations;}
	public void addestination(String dest){destinations.add(dest);}
	public boolean removedest(int id ,String dest){destinations.remove(dest); return true;}
	
	public int getruckweight(){
		return truckWeight;
	}
	public void setTruckWeight(int truckWeight) {
		this.truckWeight = truckWeight;
	}

	public HashMap<String,Integer> getitems(){return items;}
	
	
	public boolean getstatusD(){
		return status;
	}
	public void setstatusD(boolean status1){
		status=status1;
	}

	public int getrucknumber(){
		return truckNumber;
	}
	public void setTruckNumber(int truckNumber) {
		this.truckNumber = truckNumber;
	}

	public int getdriverid(){
		return driverid;
	}
	public void setdriver(int id){
		driverid=id;
	}//need to fix after checking license

	// more correct to delete the arg in all gets


}