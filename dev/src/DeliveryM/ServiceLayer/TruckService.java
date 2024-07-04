package DeliveryM.ServiceLayer;

import DeliveryM.BusinessLayer.Controllers.TruckController;
import DeliveryM.BusinessLayer.Objects.Truck;

import java.sql.SQLException;

public class TruckService {
    private TruckController truckController;

    public TruckService(TruckController truckController) {
        this.truckController = truckController;
    }

    public String  addTruck(int number, String model, int weight, int maxWeight) throws SQLException {
       return truckController.addTruck(new Truck(number,model,weight,maxWeight));

    }

    public String deleteTruck(int number) {
        if(truckController.removeTruckByNumber(number)){
            return "truck has been removed successfully!";
        }else return "can not delete truck: truck does not exist!";

    }
    public String printalltrucks(){
        return this.truckController.printAllTruck();
    }

}
