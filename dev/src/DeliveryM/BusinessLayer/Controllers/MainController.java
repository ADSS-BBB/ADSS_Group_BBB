package DeliveryM.BusinessLayer.Controllers;

import DeliveryM.BusinessLayer.Objects.*;
import DeliveryM.DataAccessLayer.DAOs.*;
import DeliveryM.DataAccessLayer.DTOs.*;

import HR.DataAccessLayer.HRData.SuperLeeDataController;
import HR.DomainLayer.BranchPackage.Branch;
import HR.DomainLayer.BranchPackage.BranchController;
import HR.DomainLayer.EmployeePackage.Employee;
import HR.DomainLayer.EmployeePackage.EmployeeController;
import HR.DomainLayer.ShiftPackage.Shift;
import HR.DomainLayer.ShiftPackage.ShiftController;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.util.*;

public class MainController {
    private  DriverController driverController;
    private  DeliveryController deliveryController;
    private  TruckController truckController;
    private  LocationController locationController;
    private DriverDAO driverDAO;
    private TruckDAO truckDAO;
    private LocationDAO locationDAO;
    private DeliveryDAO deliveryDAO;
    private ItemsQuantityDAO itemsQuantityDAO;
    private LocItemDocDAO locItemDocDAO;

    //private HashMap<Integer,StoreKeeper>storekeepers;
    private int shiftId;
    private int stroekeeperid;
    private int docID;
    private static MainController instance;

    public MainController() throws Exception {
    }


    public static MainController getInstance() throws Exception {
        if (instance == null) {
            instance = new MainController();
        }
        return instance;
    }
    // Constructor
    public MainController(DriverController driverController, DeliveryController deliveryController, TruckController truckController, LocationController locationController) throws Exception {



        this.driverController = driverController;
        this.truckController = truckController;
        this.locationController = locationController;
        this.deliveryController = deliveryController;
        //this.storekeeperDAO=new StorekeeperDAO();
        this.driverDAO=new DriverDAO();
        this.truckDAO=new TruckDAO();
        this.locationDAO=new LocationDAO();
        //this.shiftDAO=new ShiftDAO();
        this.itemsQuantityDAO=new ItemsQuantityDAO();
        this.locItemDocDAO=new LocItemDocDAO();
        this.deliveryDAO=new DeliveryDAO();
        //shiftS=new HashMap<>();

        //storekeepers=new HashMap<>();
        docID=0;
        shiftId=0;
        stroekeeperid=0;

    }
    public String deletedata()throws Exception{
        try {
            locationDAO.deleteAllLocations();

            truckDAO.deleteAllTrucks();
            driverDAO.deleteAllDrivers();
            deliveryDAO.deleteAllDeliveries();
            locItemDocDAO.deleteAllDocs();
            itemsQuantityDAO.deleteAllItemQuantities();
        }catch (Exception e){

        }
        return "";
    }
    public String loadData() throws Exception {


        driverController.loadData();
        truckController.loadData();
        locationController.loadData();
        deliveryController.loadData();
        //1.shifts:
//        List<ShiftDTO> shiftsDto=shiftDAO.getAllShifts();
//        for(ShiftDTO objShift:shiftsDto){
//            LocalDateTime starting = LocalDateTime.parse(objShift.getStartingTime());
//            LocalDateTime ending = LocalDateTime.parse(objShift.getEndingTime());
//            DeliveryM.BusinessLayer.Objects.Shift shToAdd=new DeliveryM.BusinessLayer.Objects.Shift(objShift.getShiftId(),starting,ending);
//            shiftId=objShift.getShiftId();
//            currShifts.add(shToAdd);
//        }
//        this.shiftId++;



        //3.ashMap<Driver,Shift> shiftD --> adding shifts to the driver
//        for(ShiftDTO objShift:shiftsDto) {
//            if(objShift.getDriverId()!= -1){
//                LocalDateTime starting = LocalDateTime.parse(objShift.getStartingTime());
//                LocalDateTime ending = LocalDateTime.parse(objShift.getEndingTime());
//                driverController.updateshifts(objShift.getDriverId(),objShift.getShiftId(),starting,ending);
//                DeliveryM.BusinessLayer.Objects.Shift sh=new DeliveryM.BusinessLayer.Objects.Shift(objShift.getShiftId(),starting,ending);
//                shiftD.put(driverController.getDriverById(objShift.getDriverId()),sh);
//            }
//        }
        //5.itemlocdosc
        List<LocItemDocDTO> locDocs = locItemDocDAO.getAlllocitemdoc();
        List<ItemsQuantityDTO> locQuantities = itemsQuantityDAO.getAllItemsQuantity();

            for(int i=0;i<locDocs.size();i++){
                for(LocItemDocDTO j:locDocs){
                    HashMap<Item, Integer> itemsMap = new HashMap<>();
                    Location location=null;
                    if(j.getQid()==i){
                        for(ItemsQuantityDTO iq:itemsQuantityDAO.getspecfic(j.getQid())) {
                                int l=locationController.getLocationbyadd(j.getLocationId()).getLocationId();
                                location=locationController.getLocationbyadd(j.getLocationId());
                                itemsMap.put(new Item(iq.getItemName(), iq.getItemWeight(),l), iq.getQuantity());
                        }
                        //deliveryController.deleteDeliveryById(de)
                        deliveryController.getDeliveryById(j.getDeliveryId()).addDestinationAndItems(j.getQid(),location,itemsMap);
                        docID=j.getQid();
                    }

                }

            }
       return "";
    }


    public String addDelivery(LocalDateTime leavingtime, int truck, int driver, String superLee,LocalDateTime arrivetime) throws Exception {

        StringBuilder response= new StringBuilder(" delivery has been added successfully!");

//        int thedayofexit=leavingtime.getDayOfWeek().getValue()+1;
//        int thedayofarrive=arrivetime.getDayOfWeek().getValue()+1;

        Driver d1=driverController.getDriverById(driver);
        if (d1 == null)
            return "cannot get the driver for the delivey!";

//        LocalDateTime startshiftoddriver=d1.getshift().get(thedayofexit).getStartingTime();
//        LocalDateTime endshiftoddriver=d1.getshift().get(thedayofexit).getEndingTime();
//        if(leavingtime.isBefore(startshiftoddriver) || arrivetime.isAfter(endshiftoddriver)){
//            response = new StringBuilder("the driver can't arrive this delivery due to his shifts time!");
//            return response.toString();
//        }
//        if(d1.getshift()== null || d1.getshift().isEmpty()){
//            response = new StringBuilder("the driver has no shifts!");
//            return response.toString();
//        }
        HashMap <Integer,Employee> employeeHashMap=EmployeeController.getInstance().getEmployees();
        HashMap <Integer, Shift> shiftHashMap=ShiftController.getInstance().getShifts();
        String address="";
        //String moneve="";
        for(Employee i:employeeHashMap.values()){
            if(i.getEmployeeID()==driver){
                address=BranchController.getInstance().getBranch(i.getBranchId()).getLocation();
                for(Shift j:shiftHashMap.values()){
                    if(j.getTime().isEqual(ChronoLocalDate.from(leavingtime))){
                        if(j.getType().equals("Morning")&&leavingtime.toLocalTime().isAfter(LocalTime.of(11, 59)) &&
                                leavingtime.toLocalTime().isBefore(LocalTime.of(23, 59))){
                            if(j.getEmployees().contains(driver)){
                                return "the delivery can go";
                            }
                        }
                        if(j.getType().equals("Evening") &&
                                (leavingtime.toLocalTime().isAfter(LocalTime.of(23, 59)) ||
                                        leavingtime.toLocalTime().isBefore(LocalTime.of(11, 59)))){
                            if(j.getEmployees().contains(driver)){
                                return "the delivery can go";
                            }
                        }
                    }
                }

            }
        }




        if(! d1.isAvailable()){
            response = new StringBuilder(" the driver is not available");
            return response.toString();
        }
        if(!TruckController.getInstance().getTruckByNumber(truck).isAvailable()){
            response = new StringBuilder(" the truck is not available");
            return response.toString();
        }




        response.append(deliveryController.addDelivery(leavingtime, truckController.getTruckByNumber(truck), driverController.getDriverById(driver), new Location(-1, address, "", "", ""), arrivetime));
            //update in the database
            for (Driver d : driverController.getAllDrivers()) {
                if (d.getHumanId() == driver) {
                    d.setAvailable(false);
                    //update in the database
                    driverController.getdriverDao().updateisAvailable(d.getHumanId(),"false");
                    response.append("+ the driver's availability has been updated!");
                }
            }
            for (Truck t : truckController.getAllTrucks()) {
                if (t.getNumber() == truck) {
                    t.setAvailable(!t.isAvailable());
                    //update in the database
                    truckController.getTruckDao().updateisAvailable(t.getNumber(),"false");
                    response.append("+ the driver's availability has been updated!");
                }
            }

        return response.toString();
    }

    public String addDoc(HashMap<Item,Integer> toadd,int deliveryid,Location l) throws Exception {
        String response="documentation has been added successfully!\n";


        HashMap<Integer,HR.DomainLayer.ShiftPackage.Shift> shifts= ShiftController.getInstance().getShifts();

        for(HR.DomainLayer.ShiftPackage.Shift i:shifts.values()){
                String branchNAME=BranchController.getInstance().getBranch(i.getBranchId()).getLocation();
                if(branchNAME.equals(l.getAddress())&&i.getTime().isEqual(deliveryController.getDeliveryById(deliveryid).getArrivetime().toLocalDate())){
                    if(i.getType().equals("Morning")&&deliveryController.getDeliveryById(deliveryid).getArrivetime().toLocalTime().isAfter(LocalTime.of(11, 59)) &&
                            deliveryController.getDeliveryById(deliveryid).getArrivetime().toLocalTime().isBefore(LocalTime.of(23, 59))){
                         response ="the delivery can go";
                    }
                    else if(i.getType().equals("Evening")&&(deliveryController.getDeliveryById(deliveryid).getArrivetime().toLocalTime().isAfter(LocalTime.of(23, 59)) ||
                            deliveryController.getDeliveryById(deliveryid).getArrivetime().toLocalTime().isBefore(LocalTime.of(11, 59)))){
                        response ="the delivery can go";
                    }
                    else {
                        response="there is no storekeeper to get the delivery";
                        return response;
                    }
                }

        }

        deliveryController.getDeliveryById(deliveryid).addDestinationAndItems(docID,l,toadd);
        locationDAO.updateDelivery(l.getAddress(),deliveryid);
        for(Item j: toadd.keySet()){
            ItemsQuantityDTO itemsQuantityDTO=new ItemsQuantityDTO(docID,j.getNameItem(),toadd.get(j),deliveryid,j.getItemWeight());
            itemsQuantityDAO.addItemquantity(itemsQuantityDTO);

            LocItemDocDTO locdoc=new LocItemDocDTO(deliveryid,docID,l.getAddress(),deliveryController.getDeliveryById(deliveryid).getTruck().getTruckWeight(),docID,deliveryController.getDeliveryById(deliveryid).getDriverid());
            locItemDocDAO.addLocDocs(locdoc);
            Location lolo=new Location(l.getLocationId(),l.getAddress(),l.getContactNumber(),l.getContactName(),l.getArea());
            if(locationController.getLocationbyadd(l.getAddress())==null){
            LocationDTO loc=new LocationDTO(l.getLocationId(),l.getAddress(),l.getContactNumber(),l.getContactName(),deliveryid,l.getArea());
            locationDAO.addLocation(loc);
            }


        }
        response += "+docs/items has been added successfully to this delivery!\n";
        docID++;
        checkWeight(deliveryid);
        locItemDocDAO.updateWeight(deliveryid,deliveryController.getDeliveryById(deliveryid).getTruck().getTruckWeight());

        return response;
    }

    public DeliveryController getDelibvery(){
        return this.deliveryController;
    }

    public void checkWeight(int deliveryid) throws InterruptedException, SQLException {
        int currWeight =deliveryController.getDeliveryById(deliveryid).getTruck().getTruckWeight();
        int maxWeight =deliveryController.getAllDeliveries().get(deliveryid).getTruck().getMaxWeight();
        if (currWeight > maxWeight) {
            System.out.println("1) Remove destination");
            System.out.println("2) Change destination");
            System.out.println("3) Change truck");
            System.out.println("4) Delete items");
            System.out.print("Choose an option: ");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("which doc you want to remove: ");
                    int docToRemove = scanner.nextInt();
                    LocItemDoc doc=deliveryController.getDeliveryById(deliveryid).getdocusingIdofDoc(docToRemove);
                    Location l=new Location(docToRemove,doc.getAddressLoc(),doc.getContactNumber(),doc.getContactName(),"area");
                    deliveryController.getDeliveryById(deliveryid).deleteDestinationById(l.getLocationId(),docToRemove);
                    itemsQuantityDAO.deleteitemquantity(docID);
                    locItemDocDAO.deleteDocs(docID);
                    checkWeight(deliveryid);
                    System.out.println("Destination removed.");
                    break;
                case 2:
                    //rewrite as delete the first and put the second only
                    System.out.println("Which destination do you want to remove?");
                    int destToRemove1 = scanner.nextInt();
                    System.out.println("Which document do you want to remove?");
                    int docToRemove1 = scanner.nextInt();
                    System.out.println("From which delivery do you want to take?");
                    int delivery = scanner.nextInt();
                    System.out.println("Which destination do you want to add?");
                    int destToAdd = scanner.nextInt();
                    System.out.println("Which document do you want to add?");
                    int docToAdd = scanner.nextInt();

                    LocItemDoc toRemove = deliveryController.getDeliveryById(deliveryid).getdocusingIdofDoc(docToRemove1);
                    LocItemDoc toAdd = deliveryController.getDeliveryById(delivery).getdocusingIdofDoc(docToAdd);

                    if (toRemove == null) {
                        System.out.println("Document to remove not found.");
                        return;
                    }
                    if (toAdd == null) {
                        System.out.println("Document to add not found.");
                        return;
                    }


                    Location added = new Location(destToAdd, toAdd.getAddressLoc(), toAdd.getContactNumber(), toAdd.getContactName(), "area");
                    Location changed = new Location(destToRemove1, toRemove.getAddressLoc(), toRemove.getContactNumber(), toRemove.getContactName(), "area");

                    deliveryController.getDeliveryById(deliveryid).addDestinationAndItems(docToAdd, added, toAdd.getLocItems());
                    deliveryController.getDeliveryById(delivery).addDestinationAndItems(docToRemove1, changed, toRemove.getLocItems());
                    deliveryController.getDeliveryById(deliveryid).deleteDestinationById(destToRemove1, docToRemove1);
                    deliveryController.getDeliveryById(delivery).deleteDestinationById(destToAdd, docToAdd);
                    deliveryDAO.updatetruck(deliveryid,deliveryController.getDeliveryById(delivery).getTruck().getNumber());
                    deliveryDAO.updatetruck(delivery,deliveryController.getDeliveryById(deliveryid).getTruck().getNumber());
                    deliveryDAO.updatedriver(deliveryid,deliveryController.getDeliveryById(delivery).getDriverid());
                    deliveryDAO.updatedriver(delivery,deliveryController.getDeliveryById(deliveryid).getDriverid());
                    System.out.println("Destination changed.");

                    System.out.println("Destination changed.");

                    break;
                case 3:
                    Truck tt=deliveryController.getDeliveryById(deliveryid).getTruck();
                    Truck t=truckController.getSuitTruck(currWeight);
                    if(deliveryController.getAllDeliveries().get(deliveryid).changeTruck(t)) {
                        t.setAvailable(!t.isAvailable());
                        tt.setAvailable(!tt.isAvailable());
                    }

                    deliveryController.getDeliveryById(deliveryid).getTruck();

                    truckController.getTruckDao().updateisAvailable(tt.getNumber(),"true");
                    truckController.getTruckDao().updateisAvailable(t.getNumber(),"false");
                    deliveryDAO.updatetruck(deliveryid,t.getNumber());

                    checkWeight(deliveryid);
                    System.out.println("Truck changed.");
                    break;
                case 4:
                    if(deliveryController.removeItems(deliveryid)){
                        System.out.println("Item deleted.");
                    }else System.out.println("there is a problem i cant delete items");
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }else {

            System.out.println("The delivery weight is within the truck's capacity.");
        }


    }










}