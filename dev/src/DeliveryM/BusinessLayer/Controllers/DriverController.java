package DeliveryM.BusinessLayer.Controllers;

import DeliveryM.BusinessLayer.Objects.Driver;
import DeliveryM.DataAccessLayer.DAOs.DriverDAO;
import DeliveryM.DataAccessLayer.DTOs.DriverDTO;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class DriverController {

    private List<Driver> drivers;
    private DriverDAO driverDao;
    private static DriverController instance;


    public DriverController() throws Exception {
        this.driverDao=new DriverDAO();
        this.drivers=new LinkedList<>();
        //this.loadData();
    }

    public static DriverController getInstance() throws Exception {
        if (instance == null) {
            instance = new DriverController();

        }
        return instance;
    }
    public DriverDAO getdriverDao(){
        return this.driverDao;
    }
    public void loadData(){
        List<DriverDTO> driverDTOS=driverDao.getAllDrivers();
        for(DriverDTO d:driverDTOS){
            Driver dc=new Driver(d.getHumantid(),d.getName(),d.getLicenseType());

            drivers.add(dc);
            if(d.getIsAvailable().equals("true")) {
                dc.setAvailable(true);
            }
            else dc.setAvailable(false);

        }
    }

        //add driver
    public String addDriver(Driver driver) throws Exception {
        String response="driver added successfully!!";
        boolean check=false;
        for(Driver d:drivers){
            if((d.getHumanId()==driver.getHumanId())){
                response=("driver already exists!");
                check=true;
            }
        }
        if(!check) {
            drivers.add(driver);
            if(driver.isAvailable()){
                driverDao.addDriver(new DriverDTO(driver.getHumanId(),driver.getName(),driver.getLicenseType(),"true"));
            }
            else{driverDao.addDriver(new DriverDTO(driver.getHumanId(),driver.getName(),driver.getLicenseType(),"false"));}
        }
        return response;

    }

    //remove driver
    public Driver removeDriver(int driverId) throws SQLException {
        for(Driver d:drivers){
            if(d.getHumanId()==driverId){
                drivers.remove(d);
                driverDao.deleteDriver(driverId);
                return d;

            }
        }
        return null;
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
//    public void updateshifts(int driverid,int shiftid, LocalDateTime start, LocalDateTime end){
//        if(getDriverById(driverid)==null) return;
//        this.getDriverById(driverid).updateshift(shiftid,start,end);
//    }
    public String printAllDrivers() {
        String str="";
        for (Driver d : drivers) {
            str+="\nDriver ID: " + d.getHumanId() +
                    ", Name: " + d.getName() +
                    ", License Type: " + d.getLicenseType() +
                    //", Driver shift: " + System.out.println("shift id :"+d.getShiftid()+"   startingtime:"+s.getStartingTime()+"   endingtime  :"+s.getEndingTime() + "   the day is:  "+ s.getStartingTime().getDayOfWeek());//s.getStartingTime().getDayOfWeek().getValue()+1
                    ", Is Available: " + d.isAvailable();
        }
        return str;
    }



}
