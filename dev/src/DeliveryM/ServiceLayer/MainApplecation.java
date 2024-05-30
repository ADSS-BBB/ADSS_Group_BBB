package DeliveryM.ServiceLayer;
import DeliveryM.BusinessLayer.Controllers.*;

public class MainApplecation {

    private MainController mainController;
    private DelievryService delievryService;
    private DriverService driverService;
    private TruckService truckService;
    private DriverController driverController;
    private DeliveryController deliveryController;
    private TruckController truckController;
    public MainApplecation(){
        deliveryController = new DeliveryController();
        driverController = new DriverController();
        truckController = new TruckController();
        mainController= new MainController(driverController,deliveryController,truckController);
        this.truckService= new TruckService(truckController);
        this.delievryService= new DelievryService(deliveryController);
        this.driverService=new DriverService(driverController);
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
    public void addDriver(String name,String licenseType){

        this.driverService.addDriver(name, licenseType);
    }
    public void deleteDriver(int id,String name){
        this.driverService.deleteDriver(id,name);
    }
    public void addTruck(int number,String model,int weight,int maxWeight){
        this.truckService.addTruck(number, model, weight, maxWeight);

    }
    public void deleteTruck(int number){
        this.truckService.deleteTruck(number);

    }
    public void addDelievry(String itemName,int quantity,String address){
        this.delievryService.addDelievry(itemName,quantity,address);

    }
    public void addArea(String area){
        this.delievryService.addArea(area);
    }



}
