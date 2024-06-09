package DeliveryM.ServiceLayer;

import DeliveryM.BusinessLayer.Controllers.TruckController;
import DeliveryM.BusinessLayer.Objects.Truck;

public class TruckService {
    private TruckController truckController;

    public TruckService(TruckController truckController) {
        this.truckController = truckController;
    }

    public void addTruck(int number, String model, int weight, int maxWeight) {
        truckController.addTruck(new Truck(number,model,weight,maxWeight));

    }

    public void deleteTruck(int number) {
        truckController.removeTruckByNumber(number);

    }

}
