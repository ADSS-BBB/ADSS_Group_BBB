package DeliveryM.BackendTest;

import DeliveryM.BusinessLayer.Controllers.*;
import DeliveryM.DataAccessLayer.DAOs.*;
import DeliveryM.DataAccessLayer.DTOs.*;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Test2 {

    private DriverDAO driverDAO= new DriverDAO();
    private ShiftDAO shiftDAO= new ShiftDAO();
    private TruckDAO truckDao= new TruckDAO();
    private LocationDAO locationDAO= new LocationDAO();
    private StorekeeperDAO storekeeperDAO= new StorekeeperDAO();
    private DriverController driverController= new DriverController();
    private DeliveryController deliveryController = new DeliveryController();
    private TruckController truckController= new TruckController();
    private LocationController locationController= new LocationController();
    private MainController mainController= new MainController(driverController,deliveryController,truckController,locationController);
    private DriverDTO driver= new DriverDTO(212683635,"TESTING","C1","true");

    public Test2() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
        //driver=new DriverDTO(212683635,"TESTING","C1","true");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void addDriverDAOtest() throws SQLException {
        if(driverController.getDriverById(212683635)== null)
            driverDAO.addDriver(driver);
        DriverDTO driverToCheck=  driverDAO.getDriverById(driver.getHumantid());
        assertNotNull(driverToCheck);
        assertEquals(212683635,driverToCheck.getHumantid());

    }
    @Test
    public void removeDriverDAOtest() throws SQLException {
        driverDAO.deleteDriver(212683635);
        DriverDTO testObject=  driverDAO.getDriverById(driver.getHumantid());
        //driver has been deleted so we can not get it
        assertNull(testObject, "The object should be null");

    }
    @Test
    public void updateIsAvaliableDriverDTO() throws SQLException {
        DriverDTO driver2= new DriverDTO(678900,"TESTING","C1","true");
        driverDAO.addDriver(driver2);
        driverDAO.updateisAvailable(driver2.getHumantid(),"false");
        DriverDTO driverToCheck=  driverDAO.getDriverById(driver2.getHumantid());
        assertNotNull("the object is not null",driverToCheck);
        assertEquals("false",driverToCheck.getIsAvailable());
        driverDAO.updateisAvailable(driver2.getHumantid(),"true");
        DriverDTO driverToCheck2=  driverDAO.getDriverById(driver2.getHumantid());
        assertNotNull("the object is not null",driverToCheck);
        assertEquals("true",driverToCheck2.getIsAvailable());
        driverDAO.deleteDriver(driver2.getHumantid());


    }
    @Test
    public void addDriverShiftDAOtest() throws SQLException {
        DriverDTO driver2= new DriverDTO(-1,"TESTING","C1","true");
        if(driverController.getDriverById(-1)== null) {
            driverDAO.addDriver(driver2);
        }
        //ShiftDTO shift1= new ShiftDTO(00000,"12/12/2024","13/12/2024",driver2.getHumantid(),-1);
        String dateTimeString = "12/12/2024 12:34:56"; // Example string including time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime starting = LocalDateTime.parse(dateTimeString, formatter);
        String dateTimeString2 = "13/12/2024 12:34:56"; // Example string including time
        LocalDateTime ending = LocalDateTime.parse(dateTimeString2, formatter);
        mainController.updateshifts(driver2.getHumantid(),starting,ending);
        List<ShiftDTO> shifts=shiftDAO.getAllShifts();
        //System.out.println(shifts.isEmpty());
        int driverId=-1;
        int shId=0;
        for (ShiftDTO sh:shifts){
            if(sh.getDriverId()==driver2.getHumantid()) {
                driverId = sh.getDriverId();
                break;
            }
        }
        assertEquals(driver2.getHumantid(),driverId,"shift must be added into database");
        mainController.removeDriver(driver2.getHumantid());
        //driverDAO.deleteDriver(678900);

    }

    @Test
    public void addTruckDTOtest() throws SQLException {
        TruckDTO truck=new TruckDTO(-1,"qwe",123,123,"true");
        truckDao.addTruck(truck);
        List<TruckDTO> trucks= truckDao.getAllTrucks();
        TruckDTO toCheck=null;
        for(TruckDTO current: trucks){
            if(current.getNumber()==-1)
                toCheck=truck;
        }
        assertNotNull(toCheck);
        truckDao.deleteTruck(-1);

    }
    @Test
    public void checkTrucksAvaliabilty() throws SQLException {
        TruckDTO truck=new TruckDTO(-1,"qwe",123,123,"true");
        truckDao.addTruck(truck);
        truckDao.updateisAvailable(-1,"false");
        List<TruckDTO> trucks= truckDao.getAllTrucks();
        String toCheck="true";
        for(TruckDTO current: trucks){
            if(current.getNumber()==-1)
                toCheck=current.isAvailable();
        }
        assertEquals("false",toCheck);
        truckDao.deleteTruck(-1);
    }
    @Test
    public void checkRemoveTruck() throws SQLException {
        TruckDTO truck=new TruckDTO(-1,"qwe",123,123,"true");
        truckDao.addTruck(truck);
        truckDao.deleteTruck(-1);
        List<TruckDTO> trucks= truckDao.getAllTrucks();
        TruckDTO toCheck=null;
        for(TruckDTO current: trucks){
            if(current.getNumber()==-1)
                toCheck=current;
        }
        assertNull(toCheck, "The object should be null");

    }
    //must check if there is a storekeeper in a specific location

    @Test
    public void addLocationDTOTest() throws SQLException {
        locationController.addLocation("TESTING","123","123","TESTING",-1);
        List<LocationDTO> locations= locationDAO.getAllLocations();
        boolean here=false;
        int locId=-1;
        for(LocationDTO loc:locations){
            if(loc.getAddress().equals("TESTING")) {
                here = true;
                locId=loc.getId();
                break;
            }
        }
        assertEquals(true,here);
        locationDAO.deleteLocation(locId);

    }
    @Test
    public void removeLocationDTOTest() throws SQLException {
        locationController.addLocation("TESTING","123","123","TESTING",-1);
        List<LocationDTO> locations= locationDAO.getAllLocations();
        boolean here=false;
        int locId=-1;
        for(LocationDTO loc:locations){
            if(loc.getAddress().equals("TESTING")) {
                locId=loc.getId();
                break;
            }
        }
        locationDAO.deleteLocation(locId);
        locations= locationDAO.getAllLocations();
        for(LocationDTO loc:locations){
            if(loc.getAddress().equals("TESTING")) {
                here = true;
                break;
            }
        }
        assertEquals(false,here);


    }
    @Test
    public void addStoreKeeperDTO() throws SQLException {
        //if(mainController.)
        StorekeeperDTO keeper=new StorekeeperDTO(-1,"TESTING");
        storekeeperDAO.addStorekeeper(keeper);
        List<StorekeeperDTO> keepers= storekeeperDAO.getAllStoreKeepes();
        boolean here=false;
        for(StorekeeperDTO cuu:keepers){
            if(cuu.getStoreKeeperId()==-1 ) {
                here = true;
                break;
            }
        }
        assertEquals(true,here);
        storekeeperDAO.deleteStorekeeper(-1);

    }

}
