package DeliveryM.BusinessLayer.Controllers;


import DeliveryM.BusinessLayer.Objects.Delivery;

import java.util.*;

public class DeliveryController{
	private HashMap<Integer, Delivery> deliveries;
	private int deliveryID;

	//constructor
	public DeliveryController(){
		deliveries=new HashMap<Integer,Delivery>();
		deliveryID=0;
	}

	public HashMap<Integer, Delivery> getDeliveries() {
        return deliveries;
    }
	//delivery
	public int createDelivery(String date, String time,String source,List<String> destinations,int driverid, int truckNumber,int truckWeight,HashMap<String,Integer> items,boolean status ){
        Delivery delivery=new Delivery(this.deliveryID,date,time,source,destinations,driverid,truckNumber,truckWeight,items,status);
        deliveries.put(this.deliveryID ,delivery);//id of del and its all information
        this.deliveryID++;
		return deliveryID;
    }
	public boolean  removeDelivery(int deliveryid){
		deliveries.remove(deliveryid);
		return true;
	}
	//driver
	public void addDriver(int driver,int deliveryid){
		//we need to check license
		deliveries.get(deliveryid).setdriver(driver);	
	}
	public int getDriverid(int deliveryid){
		return deliveries.get(deliveryid).getdriverid();
	}
	//truck
	public int getTruck(int deliveryid){
		return deliveries.get(deliveryid).getrucknumber();
	}
	//destinations
	public List<String> getalldest(int deliveryid){
		return deliveries.get(deliveryid).getdestinations();
	}
	public void adddest(int deliveryid,String dest){
		deliveries.get(deliveryid).addestination(dest);
	}
	public void removedest(int deliveryid,String dest){
		deliveries.get(deliveryid).removedest(deliveryid,dest);// the deliveryid not important  

	}
	//time
	public void updatetime(int deliveryid,String time ){
		deliveries.get(deliveryid).setTime(time);
	}
	public String getTime(int deliveryid){
		return deliveries.get(deliveryid).gettime();
	}
	//date
	public void updatedate(int deliveryid,String date){
		deliveries.get(deliveryid).setdate(date);
	}
	public String getDate(int deliveryid){
		return deliveries.get(deliveryid).getdate();

    }





	
}
