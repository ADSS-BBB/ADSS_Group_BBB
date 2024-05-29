package BusinessLayer.Controllers;
public class MainController {
    private DreiverController dreiverController;
    private DeliveryController deliveryController;
    private TruckController truckController;

    public MainController() {
        deliveryController = new DeliveryController();
        dreiverController = new DreiverController();
        truckController = new TruckController();
    }

    public String prepareDelivery(){
        return("not implemented");
    }


}
