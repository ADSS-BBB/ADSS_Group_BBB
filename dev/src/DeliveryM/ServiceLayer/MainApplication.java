package DeliveryM.ServiceLayer;
import DeliveryM.BusinessLayer.Controllers.*;
import DeliveryM.BusinessLayer.Objects.*;

import java.util.Date;
import java.util.HashMap;

public class MainApplication {

    private MainController mainController;
    private DelievryService delievryService;
    private DriverService driverService;
    private TruckService truckService;
    private DriverController driverController;
    private DeliveryController deliveryController;
    private TruckController truckController;
    private LocationController locationController;
    private LocationService locationService;
    public MainApplication() throws Exception {
        deliveryController = new DeliveryController();
        driverController = new DriverController();
        truckController = new TruckController();
        locationController= new LocationController();
        mainController= new MainController(driverController,deliveryController,truckController,locationController);
        this.truckService= new TruckService(truckController);
        this.delievryService= new DelievryService(deliveryController);
        this.driverService=new DriverService(driverController);
        this.locationService= new LocationService(locationController);
        addTruck(000,"mazda",2000,5000);
        addDriver(315282038,"bashar","C1");
        addLocation("nahef","0502555511","mhmd","north");
        addDelivery(new Date(),"1200",000,315282038,"superLee");

        addTruck(111,"mazda",2000,10000);
        addDriver(111,"qais","C1");
        addLocation("kfr bra","0502555511","mhmd","0");
        addDelivery(new Date(),"1200",111,111,"superLee");
        //deliveryController.getDeliveryById(0).addDestinationAndItems();
    }

    public TruckService getTruckService(){
        return this.truckService;
    }
    public DelievryService getDelievryService(){
        return this.delievryService;
    }
    public DriverService getDriverService(){
        return this.driverService;
    }

    //------------------------------------------------------------
    //must use response and update if succeed or noy
    public void addDriver(int id,String name,String licenseType) throws Exception {

        this.driverService.addDriver(id,name, licenseType);

    }
    public void addTruck(int number,String model,int weight,int maxWeight){
        this.truckService.addTruck(number, model, weight, maxWeight);

    }

    public void addLocation(String address,String contactnumber,String contactname,String area){
        mainController.addLocation( address,contactnumber,contactname,area );
    }
    public void addDelivery(Date dDelivery, String leavingtime, int truck, int driver, String superLee)  {
        if(truckController.getTruckByNumber(truck).isAvailable()&&driverController.getDriverById(driver).isAvailable()) {
            deliveryController.addDelivery(dDelivery, leavingtime, truckController.getTruckByNumber(truck), driverController.getDriverById(driver), new Location(0, "superLee", "", "", ""));
            for (Driver d : driverController.getAllDrivers()) {
                if (d.getHumanId() == driver) {
                    d.setAvailable(false);
                }
            }
            for (Truck t : truckController.getAllTrucks()) {
                if (t.getNumber() == truck) {
                    t.setAvailable();
                }
            }
        }
        else {
            System.out.println("cant add a delivery");
        }
    }
    public void addDoc(HashMap<Item,Integer> quantity,int delid,int l) throws InterruptedException {

        mainController.addDoc(quantity,delid,locationController.getLocation(l));
    }

    public void removeLocation(String address){
        mainController.removeLocation(address);
    }

    public void deleteDriver(int id){
        this.driverService.deleteDriver(id);
    }

    public void deleteTruck(int number)  {
        this.truckService.deleteTruck(number);

    }

    public void printalldrivers(){
        mainController.printAllDrivers();
    }
    public void printalltrucks(){
        mainController.printAllTruck();
    }
    public void printalldeliveries(){
        mainController.printAllDelivery();
    }
    public void printAllLocations(){
        mainController.printAllLocations();
    }
    public void printalldoc(int deliveryid){
        mainController.printallDoc(deliveryid);
    }

    public void addDelievry(String itemName,int quantity,String address){
        this.delievryService.addDelievry(itemName,quantity,address);

    }


    public void deletedelivery(int id){
        mainController.removeDeliveryById(id);
    }
    public void addArea(String area){
        this.delievryService.addArea(area);
    }

    public Location getLocationbyADD(String  address) {
        return mainController.getLocationbyADD(address);
    }
    public Location getLocation(int id) {
        return mainController.getLocation(id);
    }
}
