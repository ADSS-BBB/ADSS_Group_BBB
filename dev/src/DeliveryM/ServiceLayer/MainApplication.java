package DeliveryM.ServiceLayer;

import DeliveryM.BusinessLayer.Controllers.*;
import DeliveryM.BusinessLayer.Objects.Item;
import DeliveryM.BusinessLayer.Objects.Location;

import java.sql.SQLException;
import java.time.LocalDateTime;
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
        locationController= new LocationController();
        deliveryController = new DeliveryController();
        driverController = new DriverController();
        truckController = new TruckController();
        mainController= new MainController(driverController,deliveryController,truckController,locationController);
        this.truckService= new TruckService(truckController);
        this.delievryService= new DelievryService(deliveryController);
        this.driverService=new DriverService(driverController);
        this.locationService= new LocationService(locationController);

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
    public String addDriver(int id,String name,String licenseType) throws Exception {
        return this.driverService.addDriver(id,name, licenseType);
    }
    public String addTruck(int number,String model,int weight,int maxWeight) throws SQLException {
        return this.truckService.addTruck(number, model, weight, maxWeight);

    }
    public String addLocation(String address,String contactnumber,String contactname,String area,int id ) throws SQLException {
        return this.locationService.addLocation( address,contactnumber,contactname,area,id );
    }



    public String addDelivery(LocalDateTime leavingtime, int truck, int driver, String superLee,LocalDateTime arrivetime) throws SQLException {
       return  mainController.addDelivery(leavingtime,truck,driver,superLee,arrivetime);

    }
    public String addDoc(HashMap<Item,Integer> quantity,int delid,int l) throws InterruptedException, SQLException {

       return mainController.addDoc(quantity,delid,locationController.getLocation(l));
    }
    public String addShiftsStoreKeeper( String workaddress, LocalDateTime start,LocalDateTime end) throws SQLException {
        return mainController.addShiftsStoreKeeper(workaddress,start,end);

    }
    public String addShiftDriver(int driverid,LocalDateTime start,LocalDateTime end) throws SQLException {
        return mainController.updateshifts(driverid,start,end);

    }
    public String removeLocation(String address) throws SQLException {

        return this.locationService.removeLocation(address);
    }
    public String deleteDriver(int id) throws SQLException {
        return mainController.removeDriver(id);
    }


    public String deleteTruck(int number)  {
        return this.truckService.deleteTruck(number);
    }
    public String printalldrivers(){
       return this.driverService.printAllDrivers();
    }
    public String printalltrucks(){
        return this.truckService.printalltrucks();
    }
    public String printalldeliveries(){
        return this.delievryService.printalldeliveries();
    }
    public String printAllLocations(){ return this.locationService.printAllLocations(); }
    public String printalldoc(int deliveryid){
        return this.delievryService.printallDoc(deliveryid);
    }
    public String printShifts(){
        return mainController.printshifts();
    }

    public String deletedelivery(int id) throws SQLException {
        return this.delievryService.deletedelivery(id);

    }

    public Location getLocationbyADD(String  address) {
        return this.locationService.getLocationbyADD(address);
    }
    public Location getLocation(int id) {
        return this.locationService.getLocation(id);
    }
}
