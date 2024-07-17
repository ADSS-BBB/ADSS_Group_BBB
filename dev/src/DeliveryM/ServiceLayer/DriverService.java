package DeliveryM.ServiceLayer;


import DeliveryM.BusinessLayer.Controllers.DriverController;
import DeliveryM.BusinessLayer.Objects.Driver;

import java.sql.SQLException;

public class DriverService {
    private DriverController driverController;
    public DriverService(DriverController driverController){
        this.driverController=driverController;
    }

    public String addDriver(int id,String name,String licenseType) throws Exception {
        return driverController.addDriver(new Driver(id,name, licenseType));
    }

    public String printAllDrivers(){
        return this.driverController.printAllDrivers();
    }

}
