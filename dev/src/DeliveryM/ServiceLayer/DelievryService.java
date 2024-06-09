package DeliveryM.ServiceLayer;

import DeliveryM.BusinessLayer.Controllers.DeliveryController;
import DeliveryM.BusinessLayer.Objects.Delivery;

import java.util.HashMap;
import java.util.List;

public class DelievryService {
    private DeliveryController deliveryController;

    public DelievryService(DeliveryController deliveryController) {
        this.deliveryController=deliveryController;
    }


    public void addDelievry(String itemName,int quantity,String address){

    }
    public HashMap<Integer,Delivery> ALLdeliveries(){
        return deliveryController.getAllDeliveries();
    }
    public void addArea(String area){

    }

}
