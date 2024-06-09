package DeliveryM.BusinessLayer.Controllers;

import DeliveryM.BusinessLayer.Objects.*;

import java.util.*;

public class DeliveryController {
    private HashMap<Integer,Delivery> deliveries;
    private int deliveryID;
    public DeliveryController(){
        deliveries=new HashMap<>();
        deliveryID=0;

    }


    //add delivery
    public void addDelivery(Date dDelivery, String leavingtime, Truck truck, Driver driver, Location LOC){
        if(truck.getMaxWeight()<=driver.getMaxWeightToDriver(driver.getHumanId())){
            Delivery d=new Delivery(deliveryID,dDelivery,leavingtime,driver,truck,LOC);
            deliveries.put(deliveryID,d);
            deliveryID++;
        }else{
            System.out.println("The driver can drive this truck");
        }

    }


    //delete delivery
    public boolean deleteDeliveryById(int id) {
        deliveries.get(id).getTruck().setAvailable();
        deliveries.get(id).getDriver().setAvailable(true);
        deliveries.remove(id);
        return false;
    }

    //get delivery
    public Delivery getDeliveryById(int id) {
        return deliveries.get(id);
    }
    //get all deliveries
    public HashMap<Integer, Delivery> getAllDeliveries() {
        return deliveries;
    }

    //1)delete dest
    public boolean deleteDestById(int deliveryid,int idDest,int docid){
        deliveries.get(deliveryid).deleteDestinationById(idDest,docid);
        return false;
    }


    //3)remove items
    public boolean removeItems(int deliveryId){
        deliveries.get(deliveryId).removeItemsFromDelivery();
        return true;
    }

    //4)change truck
    public boolean changeTruckInDelivery(int deliveryId, Truck truck){
        deliveries.get(deliveryId).changeTruck(truck);
        return true;
    }
    public HashMap<Item,Integer> getDocOfDestUsingId(int deliveryid,int destid){
        if(deliveries.get(deliveryid)!=null)
            return deliveries.get(deliveryid).getDocUsingDestId(destid);
        else return new HashMap<Item,Integer>();
    }

    public HashMap<Item,Integer> getDocOfDestUsingLocation(int deliveryid,Location dest) throws Exception {
        if(deliveries.get(deliveryid)!=null)
            return deliveries.get(deliveryid).getDocUsingDestLocation(dest);
        else return new HashMap<Item,Integer>();
    }



    public void printAllDeliveries() {
        for (Delivery d : deliveries.values()) {
            System.out.println("Delivery ID: " + d.getId() +
                    ", Date: " + d.getDate() +
                    ", Time: " + d.getTime() +
                    ", Driver ID: " + d.getDriverid() +
                    ", Truck Weight: " + d.getTruck().getTruckWeight() +
                    ", Truck Number: " + d.getTruckNumber() +
                    ", Source: " + d.getSource().toString());
        }
    }


    public void printall(int deliveryid ){
        HashMap<Integer,LocItemDoc> a=this.getDeliveryById(deliveryid).getdoc();
        for(LocItemDoc i:a.values()){
            i.printDetails();
        }
    }

}
