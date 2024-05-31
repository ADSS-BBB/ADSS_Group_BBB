package DeliveryM.ServiceLayer;

import DeliveryM.BusinessLayer.Controllers.ItemsController;

public class ItemsService {
    private ItemsController itemsController;

    public ItemsService(ItemsController itemsController){
        this.itemsController = itemsController;
    }

    public void addItem(String itemName, int weight, int quantity,String address,String contantNumber,String contantName,String area) {


    }

}
