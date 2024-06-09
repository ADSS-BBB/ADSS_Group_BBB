package DeliveryM.ServiceLayer;

import DeliveryM.BusinessLayer.Controllers.LocationController;

public class LocationService {
    private LocationController locationController;

    public LocationService(LocationController locationController){
        this.locationController=locationController;
    }
}
