package DeliveryM.ServiceLayer;

import DeliveryM.BusinessLayer.Controllers.DeliveryController;
import DeliveryM.BusinessLayer.Objects.Delivery;

import java.sql.SQLException;
import java.util.HashMap;

public class DelievryService {
    private DeliveryController deliveryController;

    public DelievryService(DeliveryController deliveryController) {
        this.deliveryController=deliveryController;
    }

    public String deletedelivery(int id) throws SQLException {
        if(deliveryController.deleteDeliveryById(id)){
            return "delievry has been deleted sucssefully";
        }
        else return "could not delete the delivery";
    }

    public HashMap<Integer,Delivery> ALLdeliveries(){
        return deliveryController.getAllDeliveries();
    }

    public String printalldeliveries(){
        return this.deliveryController.printAllDeliveries();

    }

    public String printallDoc(int delivery){
       return deliveryController.printall(delivery);
    }


}
