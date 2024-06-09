package DeliveryM.BackendTest;

import DeliveryM.BusinessLayer.Controllers.*;
import DeliveryM.BusinessLayer.Objects.*;
import org.junit.jupiter.api.*;

import java.util.Date;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class Test {

    private MainController mainController;
    private DriverController driverController;
    private DeliveryController deliveryController;
    private TruckController truckController;
    private LocationController locationController;

    @BeforeEach
    void setUp() {
        driverController = new DriverController();
        deliveryController = new DeliveryController();
        truckController = new TruckController();
        locationController = new LocationController();

        mainController = new MainController(driverController, deliveryController, truckController, locationController);
    }

    @AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void testAddDriver() {
        Driver driver = new Driver(1,"bashar","C1");
        mainController.addDriver(driver);
        assertEquals(driver, driverController.getDriverById(driver.getHumanId()));
    }

    @org.junit.jupiter.api.Test
    void testAddTruck() {
        Truck truck = new Truck(1,"mazda",2000,1000);
        mainController.addTruck(truck);
        assertEquals(truck, truckController.getTruckByNumber(truck.getNumber()));
    }
    @org.junit.jupiter.api.Test
    void testAddLocation() {
        String address = "nahef";
        String contactNumber = "0502555511";
        String contactName = "someone";
        String area = "north";

        mainController.addLocation(address, contactNumber, contactName, area);
        assertNotNull(locationController.getLocationbyADD(address));
    }

    @org.junit.jupiter.api.Test
    void testAddDelivery() {
        int deliveryId = 0;
        Driver d=new Driver(315282038,"bashar","C1");
        Truck t=new Truck(315282038,"mazda",2000,10000);
        Location l=new Location(0,"nahef","0502555511","mhmd","north");
        //Delivery delivery = new Delivery(deliveryId,new Date(),"15:00",d,t,l);

        deliveryController.addDelivery(new Date(),"15:00",t,d,l);
//        assertTrue(mainController.removeDeliveryById(deliveryId));
        assertEquals(1,deliveryController.getAllDeliveries().size());

    }

    @org.junit.jupiter.api.Test
    void testRemoveDriver(){
        mainController.addDriver(new Driver(315282038,"bashar","C1"));
        assertEquals(1,driverController.getAllDrivers().size());
        mainController.removeDriverById(315282038);
        assertEquals(0,driverController.getAllDrivers().size());
    }

    @org.junit.jupiter.api.Test
    void testRemoveLocation() {
        String address = "nahef";
        mainController.addLocation(address, "0502555511", "someone", "north");
        assertEquals(1,locationController.allLocations().size());
        locationController.removeLocation(address);
        assertEquals(0, locationController.allLocations().size());
    }

    @org.junit.jupiter.api.Test
    void testRemoveDelivery() {
        int deliveryId = 0;
        Driver d=new Driver(315282038,"bashar","C1");
        Truck t=new Truck(315282038,"mazda",2000,10000);
        Location l=new Location(0,"nahef","0502555511","mhmd","north");
        //Delivery delivery = new Delivery(deliveryId,new Date(),"15:00",d,t,l);

        deliveryController.addDelivery(new Date(),"15:00",t,d,l);
        assertEquals(1,deliveryController.getAllDeliveries().size());
        mainController.removeDeliveryById(deliveryId);
        assertEquals(0,deliveryController.getAllDeliveries().size());
    }

    @org.junit.jupiter.api.Test
    void testRemoveTruck() throws Exception {
        Truck truck = new Truck(1, "mazda", 2000, 1000);
        mainController.addTruck(truck);
        assertEquals(truck, truckController.getTruckByNumber(truck.getNumber()));
        mainController.removeTruckByNumber(1);
    }

    @org.junit.jupiter.api.Test
    void testAddDoc() throws InterruptedException {
        Truck t = new Truck(111, "mazda", 2000, 10000);
        Driver d = new Driver(111, "qais", "C1");
        Location l = new Location(0, "kfr bra", "0502555511", "mhmd", "0");

        // Add the truck, driver, and location to their respective controllers
        truckController.addTruck(t);
        driverController.addDriver(d);
        locationController.addLocation(l.getAddress(), l.getContactNumber(), l.getContactName(), l.getArea());

        // Add the delivery to the deliveryController
        deliveryController.addDelivery(new Date(), "12:00", t, d, l);

        // Verify the delivery has been added
        assertNotNull(deliveryController.getDeliveryById(0));

        // Create the document and add it to the delivery
        HashMap<Item, Integer> dox = new HashMap<>();
        Item i = new Item("milk", 2, 0);
        dox.put(i, 7);

        // Now add the document to the delivery
        deliveryController.getDeliveryById(0).addDestinationAndItems(0, l, dox);

        // Verify the document has been added
        assertEquals(1, deliveryController.getDeliveryById(0).getdoc().size());
    }


    @org.junit.jupiter.api.Test
    void testRemoveDoc() throws InterruptedException {
        // Set up the truck, driver, location, and delivery
        Truck t = new Truck(111, "mazda", 2000, 10000);
        Driver d = new Driver(111, "qais", "C1");
        Location l = new Location(0, "kfr bra", "0502555511", "mhmd", "0");

        // Add the truck, driver, and location to their respective controllers
        truckController.addTruck(t);
        driverController.addDriver(d);
        locationController.addLocation(l.getAddress(), l.getContactNumber(), l.getContactName(), l.getArea());

        // Add the delivery to the deliveryController
        deliveryController.addDelivery(new Date(), "12:00", t, d, l);

        // Verify the delivery has been added
        assertNotNull(deliveryController.getDeliveryById(0));

        // Create the document and add it to the delivery
        HashMap<Item, Integer> dox = new HashMap<>();
        Item i = new Item("milk", 2, 0);
        dox.put(i, 7);

        // Add the document to the delivery
        deliveryController.getDeliveryById(0).addDestinationAndItems(0, l, dox);

        // Verify the document has been added
        assertEquals(1, deliveryController.getDeliveryById(0).getdoc().size());

        // Now remove the document from the delivery
        deliveryController.getDeliveryById(0).deleteDestinationById(l.getLocationId(), 0);

        // Verify the document has been removed
        assertEquals(0, deliveryController.getDeliveryById(0).getdoc().size());
    }


}
