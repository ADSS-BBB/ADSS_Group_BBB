package DeliveryM.BusinessLayer.Controllers;

import DeliveryM.BusinessLayer.Objects.Driver;

import java.util.LinkedList;
import java.util.List;

public class DriverController {

    private List<Driver> drivers;

    public DriverController(){
        this.drivers=new LinkedList<>();
    }

    //add driver
    public void addDriver(Driver driver) {
        boolean check=false;
        for(Driver d:drivers){
            if((d.getHumanId()==driver.getHumanId())){
                System.out.println("driver already exists!");
                check=true;
            }
        }
        if(!check) {
            drivers.add(driver);
        }
    }

    //remove driver
    public boolean removeDriver(int driverId){
        for(Driver d:drivers){
            if(d.getHumanId()==driverId)
                drivers.remove(d);

        }
        return true;
    }
    public boolean removeDriver(Driver d){
        if(!drivers.contains(d)){ return false;}
        drivers.remove(d);
        return true;
    }

    public Driver getDriverById(int driverId) {
        for (Driver driver : drivers) {
            if (driver.getHumanId() == driverId) {
                return driver;
            }
        }
        return null;
    }


    public List<Driver> getAllDrivers() {
        return drivers;
    }
    //might change
    public boolean updateDriverAvailability(int driverId, boolean isAvailable) {
        Driver driver = getDriverById(driverId);
        if (driver != null) {
            driver.setAvailable(isAvailable);
            return true;
        }
        return false;
    }

    public void printAllDrivers() {
        for (Driver d : drivers) {
            System.out.println("Driver ID: " + d.getHumanId() +
                    ", Name: " + d.getName() +
                    ", License Type: " + d.getLicenseType() +
                    ", Is Available: " + d.isAvailable());
        }
    }




}
