package DeliveryM.BusinessLayer.Controllers;

import DeliveryM.BusinessLayer.Objects.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class MainController {
    private final DriverController driverController;
    private final DeliveryController deliveryController;
    private final TruckController truckController;
    private final LocationController locationController;
    private int docID;
    // Constructor
    public MainController(DriverController driverController, DeliveryController deliveryController, TruckController truckController, LocationController locationController) {
        this.driverController = driverController;
        this.deliveryController = deliveryController;
        this.truckController = truckController;
        this.locationController = locationController;
        docID=0;
    }

    //            System.out.println("1: Add Driver");
//            System.out.println("2: Add Location");
//            System.out.println("3: Add Truck");
//            System.out.println("4: Add documents");
//            System.out.println("5: Add Delivery");
//            System.out.println("6: Remove Driver");
//            System.out.println("7: Remove truck");
//            System.out.println("8: Remove Delivery");
//            System.out.println("9: Remove Location");
//            System.out.println("10: Change Location");
//            System.out.println("11: Print All Drivers");
//            System.out.println("12: Print All Trucks");
//            System.out.println("13: Print All Locations");
//            System.out.println("14: Print All deliveries");
//            System.out.println("15:Exit");
    public void addDriver(Driver driver)  {
        driverController.addDriver(driver);
    }
    public void addTruck(Truck truck){
        truckController.addTruck(truck);
    }
    public void addLocation(String address,String contactnumber,String contactname,String area){
        locationController.addLocation(address,contactnumber,contactname,area);
    }

    public void addDoc(HashMap<Item,Integer> toadd,int deliveryid,Location l) throws InterruptedException {

        deliveryController.getDeliveryById(deliveryid).addDestinationAndItems(docID,l,toadd);
        checkWeight(deliveryid);
        docID++;
    }
    //public void addDocument()
    public void checkWeight(int deliveryid) throws InterruptedException {
        int currWeight =deliveryController.getAllDeliveries().get(deliveryid).getWeightDelivery()+deliveryController.getDeliveryById(deliveryid).getTruck().getTruckWeight();
        int maxWeight =deliveryController.getAllDeliveries().get(deliveryid).getTruck().getMaxWeight();

        if (currWeight > maxWeight) {
            System.out.println("1) Remove destination");
            System.out.println("2) Change destination");
            System.out.println("3) Change truck");
            System.out.println("4) Delete items");
            System.out.print("Choose an option: ");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("which doc you want to remove: ");
                    int docToRemove = scanner.nextInt();
                    LocItemDoc doc=deliveryController.getDeliveryById(deliveryid).getdocusingIdofDoc(docToRemove);
                    Location l=new Location(docToRemove,doc.getAddressLoc(),doc.getContactNumber(),doc.getContactName(),"area");
                    deliveryController.getDeliveryById(deliveryid).deleteDestinationById(l.getLocationId(),docToRemove);
                    checkWeight(deliveryid);
                    System.out.println("Destination removed.");
                    break;
                case 2:

                    System.out.println("Which destination do you want to remove?");
                    int destToRemove1 = scanner.nextInt();
                    System.out.println("Which document do you want to remove?");
                    int docToRemove1 = scanner.nextInt();
                    System.out.println("From which delivery do you want to take?");
                    int delivery = scanner.nextInt();
                    System.out.println("Which destination do you want to add?");
                    int destToAdd = scanner.nextInt();
                    System.out.println("Which document do you want to add?");
                    int docToAdd = scanner.nextInt();

                    LocItemDoc toRemove = deliveryController.getDeliveryById(deliveryid).getdocusingIdofDoc(docToRemove1);
                    LocItemDoc toAdd = deliveryController.getDeliveryById(delivery).getdocusingIdofDoc(docToAdd);

                    if (toRemove == null) {
                        System.out.println("Document to remove not found.");
                        return;
                    }
                    if (toAdd == null) {
                        System.out.println("Document to add not found.");
                        return;
                    }


                    Location added = new Location(destToAdd, toAdd.getAddressLoc(), toAdd.getContactNumber(), toAdd.getContactName(), "area");
                    Location changed = new Location(destToRemove1, toRemove.getAddressLoc(), toRemove.getContactNumber(), toRemove.getContactName(), "area");

                    deliveryController.getDeliveryById(deliveryid).addDestinationAndItems(docToAdd, added, toAdd.getLocItems());

                    deliveryController.getDeliveryById(delivery).addDestinationAndItems(docToRemove1, changed, toRemove.getLocItems());

                    deliveryController.getDeliveryById(deliveryid).deleteDestinationById(destToRemove1, docToRemove1);

                    deliveryController.getDeliveryById(delivery).deleteDestinationById(destToAdd, docToAdd);

                    System.out.println("Destination changed.");

                    System.out.println("Destination changed.");

                    break;
                case 3:
                    deliveryController.getAllDeliveries().get(deliveryid).getTruck().setAvailable();
                    Truck t=truckController.getSuitTruck(currWeight);
                    if(deliveryController.getAllDeliveries().get(deliveryid).changeTruck(t)) {
                        t.setAvailable();

                    }
                    checkWeight(deliveryid);
                    System.out.println("Truck changed.");
                    break;
                case 4:
                    if(deliveryController.removeItems(deliveryid)){
                        System.out.println("Item deleted.");
                    }else System.out.println("there is a problem i cant delete items");
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }else {
            System.out.println("The delivery weight is within the truck's capacity.");
        }
    }

    public void removeLocation(String address){
        locationController.removeLocation(address);
    }




    public boolean removeDeliveryById(int deliveryId) {
        return deliveryController.deleteDeliveryById(deliveryId);
    }


    public HashMap<Item,Integer> getDocOfDestUsingLocation(int deliveryId, Location dest) throws Exception {
        return deliveryController.getDocOfDestUsingLocation(deliveryId, dest);
    }




    public Driver getDriverById(int driverId) {
        return driverController.getDriverById(driverId);
    }

    public boolean updateDriverAvailability(int driverId, boolean isAvailable) {
        return driverController.updateDriverAvailability(driverId, isAvailable);
    }

    public boolean removeDriverById(int driverId) {
        return driverController.removeDriver(driverId);
    }




    public Truck getTruckByNumber(int truckNumber) throws Exception {
        return truckController.getTruckByNumber(truckNumber);
    }

    public boolean updateTruckAvailability(int truckNumber) throws Exception {
        return truckController.updateTruckAvailability(truckNumber);
    }


    public boolean removeTruckByNumber(int truckNumber) throws Exception {
        return truckController.removeTruckByNumber(truckNumber);
    }


    //printing
    public void printAllDelivery(){
        deliveryController.printAllDeliveries();
    }
    public void printAllDrivers(){
        driverController.printAllDrivers();
    }
    public void printAllTruck(){
        truckController.printAllTruck();
    }
    public void printAllLocations(){locationController.printAllLocations();}
    public void printallDoc(int delivery){deliveryController.printall(delivery);}





    public DeliveryController getDeliveryController(){
        return this.deliveryController;
    }
    public DriverController getDriverController(){
        return this.driverController;
    }
    public TruckController getTruckController(){
        return this.truckController;
    }

    public Location getLocation(int id) {
        return locationController.getLocation(id);
    }
    public Location getLocationbyADD(String address) {
        return locationController.getLocationbyADD(address);
    }

    //public void printall()
}