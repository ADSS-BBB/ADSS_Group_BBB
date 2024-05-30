package DeliveryM.ServiceLayer;

import DeliveryM.BusinessLayer.Controllers.TruckController;

public class TruckService {
    private TruckController truckController;

    public TruckService(TruckController truckController) {
        this.truckController = truckController;
    }

    public void addTruck(int number, String model, int weight, int maxWeight) {
            //truckController.addTruck(number,model,weight,maxWeight);

    }

    public void deleteTruck(int number) {
            //truckController.deleteTruck(number);

    }

}
