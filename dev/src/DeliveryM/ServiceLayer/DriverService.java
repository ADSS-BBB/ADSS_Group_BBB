package DeliveryM.ServiceLayer;


import DeliveryM.BusinessLayer.Controllers.DriverController;

public class DriverService {
    private DriverController driverController;
    public DriverService(DriverController driverController){
        this.driverController=driverController;
    }

    public void addDriver(int humanId,String name,String licenseType) {
//        String msg;
//        Response res;
//        try {
//
//            boolean result = driverController.addDriver(name, licenseType);
//            if (result) {
//
//                res = new Response();
//            } else res = new Response("could not delete the board", null);
//        } catch (Exception exception) {
//            res = new Response(exception.getMessage(), null);
//        }
//       //must find a way to work with json
    }
    public void deleteDriver(int humanId){
    }
}
