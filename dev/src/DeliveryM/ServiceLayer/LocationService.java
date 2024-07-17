package DeliveryM.ServiceLayer;

import DeliveryM.BusinessLayer.Controllers.LocationController;
import DeliveryM.BusinessLayer.Objects.Location;

import java.sql.SQLException;

public class LocationService {
    private LocationController locationController;

    public LocationService(LocationController locationController){
        this.locationController=locationController;
    }

    public String addLocation( String address,String contactnumber,String contactname,String area ,int id) throws SQLException {
       return this.locationController.addLocation(address,contactnumber,contactname,area,id);
    }


    public String removeLocation(String address) throws SQLException {
        return this.locationController.removeLocation(address);
    }
    public String printAllLocations(){
        return this.locationController.printAllLocations();
    }
    public Location getLocationbyADD(String address) {
        return locationController.getLocationbyADD(address);
    }
    public Location getLocation(int id) {
        return locationController.getLocation(id);
    }
}
