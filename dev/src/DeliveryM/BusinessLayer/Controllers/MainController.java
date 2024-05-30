package DeliveryM.BusinessLayer.Controllers;
import DeliveryM.BusinessLayer.Objects.*;
import jdk.jshell.spi.ExecutionControl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MainController {
    private DriverController driverController;
    private DeliveryController deliveryController;
    private TruckController truckController;

    public MainController(DriverController driverController,DeliveryController deliveryController,TruckController truckController) {
        this.deliveryController=deliveryController;
        this.driverController=driverController;
        this.truckController=truckController;

    }
    // public String prepareDelivery(Map<Item, List<Location>>itemsToDel){
    //map1: area, destenations map2: destenation, items
    public String prepareDelivery(Map<String, List<Location>>areas,Map<Location,List<Item>> items) {
        //deliveryController.createDelievries(areas,items);
        for (Map.Entry<String, List<Location>> entry : areas.entrySet()) {
            String area = entry.getKey();
            List<Location> dests = entry.getValue();
            while (!dests.isEmpty()) {
                Location currentLoc = dests.getFirst();
                List<Item> itemsList = items.get(currentLoc);
                int sum = 0;
                while (!itemsList.isEmpty()) {
                    sum += itemsList.getFirst().getItemWeight();
                    itemsList.removeFirst();
                }
                //must find a truck here && sutable driver
                Truck truck = truckController.findTruck(sum);
                Driver driver = driverController.findDriver(truck.getModel());//must change it
                //must check if its null and update is avalibale
                if (truck == null || driver == null) {
                    //notaficate there is no delievry
                } else
                    deliveryController.createDelievries();
                dests.removeFirst();
            }
            //then foreach area must create a delivery

        }
        return "";
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