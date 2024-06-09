package DeliveryM.ServiceLayer;


import DeliveryM.BusinessLayer.Controllers.DriverController;
import DeliveryM.BusinessLayer.Objects.Driver;

public class DriverService {
    private DriverController driverController;
    public DriverService(DriverController driverController){
        this.driverController=driverController;
    }

    public void addDriver(int id,String name,String licenseType) throws Exception {
        driverController.addDriver(new Driver(id,name, licenseType));
    }
    public void deleteDriver(int id) {
        driverController.removeDriver(id);
    }
}
