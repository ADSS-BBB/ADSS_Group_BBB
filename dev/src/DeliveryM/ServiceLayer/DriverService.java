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
//    public String deleteDriver(int id) throws SQLException {
//        //must delete it's shifts and its delivers!
////        if (mainController.removeDriver(id)) {
////            //here must delete his shifts
////
////            return "driver has been removed successfully!";
////        }
//        return "can not remove this driver: driver does not exist!";
//    }
    public String printAllDrivers(){
        return this.driverController.printAllDrivers();
    }

}
