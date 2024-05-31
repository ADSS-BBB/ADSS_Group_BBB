package DeliveryM.BusinessLayer.Controllers;
import DeliveryM.BusinessLayer.Objects.*;

import java.util.*;

public class MainController {
    private DriverController driverController;
    private DeliveryController deliveryController;
    private TruckController truckController;
    private ItemsController itemsController;


    public MainController(DriverController driverController, DeliveryController deliveryController, TruckController truckController, ItemsController itemsController) {
        this.deliveryController=deliveryController;
        this.driverController=driverController;
        this.truckController=truckController;
        this.itemsController = itemsController;


    }
    // public String prepareDelivery(Map<Item, List<Location>>itemsToDel){
    //map1: area, destenations map2: destenation, items
//    public String prepareDelivery(Map<String, List<Location>>areas,Map<Location,List<Item>> items) {
//        //deliveryController.createDelievries(areas,items);
//        for (Map.Entry<String, List<Location>> entry : areas.entrySet()) {
//            String area = entry.getKey();
//            List<Location> dests = entry.getValue();
//            while (!dests.isEmpty()) {
//                Location currentLoc = dests.getFirst();
//                List<Item> itemsList = items.get(currentLoc);
//                int sum = 0;
//                while (!itemsList.isEmpty()) {
//                    sum += itemsList.getFirst().getItemWeight();
//                    itemsList.removeFirst();
//                }
//                //must find a truck here && sutable driver
//                Truck truck = truckController.findTruck(sum);
//                Driver driver = driverController.findDriver(truck.getModel());//must change it
//                //must check if its null and update is avalibale
//                if (truck == null || driver == null) {
//                    //notaficate there is no delievry
//                } else
//                    deliveryController.createDelievries();
//                dests.removeFirst();
//            }
//            //then foreach area must create a delivery
//
//        }
//        return "";
//    }
    public Response prepareDeliveries(){
        Response response=new Response();
        List<Delivery> deliveries= new LinkedList<>();
        Map<String,List<Item>> itemsByArea=this.itemsController.getItemsToDeliver();
        //for each one must create a delivery
        for (Map.Entry<String, List<Item>> entry : itemsByArea.entrySet()) {
            String area = entry.getKey();
            List<Item> items = entry.getValue();
            //must get all the des in this area
            List<Location> dests=this.itemsController.getDestinationsByArea(area);
            //getting the sum of the items
            int itemsWeight= itemsController.getWeightOfItems(items);
            //find a truck and driver for that delivery
            Truck truck=this.truckController.findTruck(itemsWeight);
            //check
            if(truck!= null&&truck.getTruckWeight()+itemsWeight>truck.getMaxWeight()){

                //found a suitable truck and must find a driver
                Driver driver=driverController.findDriver(itemsWeight);
                if(driver != null)
                {
                    Location src=itemsController.addSourceLoc("here","000","blah","here");
                    Delivery singleOne=this.deliveryController.addDelivery(new Date(),src,
                            dests,driver.getIdDriver(),truck.getNumber(),truck.getTruckWeight(),items);
                    //must send a doc for the driver
                    driverController.createSendDoc(singleOne,driver.getIdDriver());


                }
            }

            else {
                System.out.println("We have an issue with the delivery \nPlease choose a option :");
                System.out.println("1)delete destination.");
                System.out.println("1)change destination.");
                System.out.println("1)change truck.");
                System.out.println("1)delete items.");




            }


            //now we create a single delivery-> after getting the truck and the driver
            //Delivery singleOne=this.deliveryController.addDelivery(new Date(),itemsController.addSourceLoc("here","blah","here"),dests,);


        }

        return response;
    }
    //int deliveryid,int docid,String addressloc,Map<Integer,Integer> locItems,int truckweight,String contactName,String contactNumber){

    //1)
    //review destinations to choose which one

    public boolean deleteDest(int deliveryId,int destId){
        //check if exist at the objects better
        Delivery deliveryRelatedDest=this.deliveryController.getDeliveries().get(deliveryId);
        List<Location> L=deliveryRelatedDest.getDestinations();
        Location toDel=new Location(-1,"","","","");
        for(Location l:L){
            if(l.getlocationid()==destId)
                toDel=l;
        }
        if(toDel.getlocationid()==-1)return false;
        List<Item> currentItems=deliveryRelatedDest.getItems();

        List<Item> itemToDel=new LinkedList<>();
        for(Item a: currentItems) {
            if(a.getDestinationid()==destId){
                this.itemsController.deleteItemsWithoutItsLoc(a.getIdItem());
            }
        }
        deliveryRelatedDest.removeDest(toDel);
        return true;
    }
    //2)
    //int delId, List<Location> dests, int truckWeight,List<Item> items
    //
    public boolean changeDest(int deliveryid,int prevdest,int newdest,String address,String contactName,String contactNumber,String area,List<Item> itemsToAdd){

        //there is any function to add destination!
        Delivery deliveryRelatedDest=this.deliveryController.getDeliveries().get(deliveryid);
        List<Location> L=deliveryRelatedDest.getDestinations();
        L.add(new Location(newdest,address,contactNumber,contactName,area));

        if(deleteDest(deliveryid,prevdest)){
            if(this.deliveryController.getDeliveries().get(deliveryid)!=null){
                for(int i=0;i<itemsToAdd.size();i++){
                    this.itemsController.addItem(itemsToAdd.get(i).getNameItem(),itemsToAdd.get(i).getItemWeight(),itemsToAdd.get(i).getQuantity(),address,contactNumber,contactName,area);

                }
            }else return false;//this del not exist

            return true;
        }
        return  false;
    }
    //REVIEW ALL TRUCK WITH MORE WIEGHT
    public boolean changeTruck(int deliveryid,int weight,int truckId){


        return false;
    }

    public boolean deleteItem(int deliveryId,int weight){
        return false;
    }


    public boolean deleteDeliveryAll(int delievryId){
        Delivery toDel=this.deliveryController.getDelievryById(delievryId);
        if(this.deliveryController.removeDelivery(delievryId)) {
            for (int i = 0; i < toDel.getItems().size(); i++)
                this.itemsController.deleteItemsWithoutItsLoc(toDel.getItems().get(i).getIdItem());
            int truckNum=toDel.getTruckNumber();
            int driverId= toDel.getDriverId();
            truckController.updateIsAvaliable(truckNum);
            driverController.updateIsAvalible(driverId);
            driverController.deleteDoc(driverId,delievryId);
            return true;
        }
        return false;
    }
    public DeliveryController getDeliveryController(){
        return this.deliveryController;
    }
    public DriverController getDriverController(){
        return this.driverController;
    }
    public TruckController getTruckController(){
        return this.truckController;
    }

}