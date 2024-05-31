package DeliveryM.BusinessLayer.Controllers;
import DeliveryM.BusinessLayer.Objects.*;


import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class DeliveryController{
    private HashMap<Integer, Delivery> deliveries;//id del
    private int deliveryID;

    //constructor
    public DeliveryController(){
        deliveries =new HashMap<Integer,Delivery>();
        deliveryID=0;
    }

    public HashMap<Integer, Delivery> getDeliveries() {
        return deliveries;
    }

    public Delivery addDelivery(Date date, Location source, List<Location> destinations, int driverid, int truckNumber, int truckWeight, List<Item> items ){

        Delivery delivery=new Delivery(this.deliveryID,date,-1,source,destinations,-1,items);
        deliveries.put(this.deliveryID ,delivery);//id of del and it's all information
        this.deliveryID++;
        return delivery;

    }
    public boolean  removeDelivery(int deliveryid){
        deliveries.remove(deliveryid);
        //must delete items
        return true;
    }
    public Delivery getDelievryById(int id){
        return deliveries.get(id);
    }



    //------------------------------------------------------------------------------------------
//    //driver
//    public void addDriver(int driver,int deliveryid){
//        //we need to check license
//        deliveries.get(deliveryid).setdriver(driver);
//    }
//    public int getDriverid(int deliveryid){
//
//        return deliveries.get(deliveryid).getDriverId();
//
//
//    }
//    //truck
//    public int getTruck(int deliveryid){
//        return deliveries.get(deliveryid).getTruckNumber();
//    }
//    //destinations
//    public List<Location> getalldest(int deliveryid){
//        return deliveries.get(deliveryid).getDestinations();
//    }
//    public void addDestation(int deliveryid,Location dest){
//        deliveries.get(deliveryid).addDestination(dest);
//    }
//    public void removedest(int deliveryid,Location dest){
//        deliveries.get(deliveryid).removeDest(dest);// the deliveryid not important
//
//    }
//    //date
//    public void updatedate(int deliveryid,Date date){
//        deliveries.get(deliveryid).setDate(date);
//    }
//    public Date getDate(int deliveryid){
//        return deliveries.get(deliveryid).getDate();
//
//    }
//    public boolean deleteDest(Delivery todelete){
//        return false;
//    }
}