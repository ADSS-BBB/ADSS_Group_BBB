package DeliveryM.BusinessLayer.Controllers;

import DeliveryM.BusinessLayer.Objects.*;
import DeliveryM.DataAccessLayer.DAOs.*;
import DeliveryM.DataAccessLayer.DTOs.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

public class MainController {
    private final DriverController driverController;
    private final DeliveryController deliveryController;
    private final TruckController truckController;
    private final LocationController locationController;
    private HashMap<Driver,Shift> shiftD;
    private HashMap<StoreKeeper,Shift>shiftS;
    private ShiftDAO shiftDAO;
    private StorekeeperDAO storekeeperDAO;
    private ItemsQuantityDAO itemsQuantityDAO;
    private LocItemDocDAO locItemDocDAO;
    private LocationDAO locationDAO;
    private DeliveryDAO deliveryDAO;
    private HashMap<Integer,StoreKeeper>storekeepers;
    private int shiftId;
    private int stroekeeperid;
    private int docID;


    // Constructor
    public MainController(DriverController driverController, DeliveryController deliveryController, TruckController truckController, LocationController locationController) throws ClassNotFoundException {
        this.driverController = driverController;
        this.truckController = truckController;
        this.locationController = locationController;
        this.deliveryController = deliveryController;
        this.storekeeperDAO=new StorekeeperDAO();
        this.locationDAO=new LocationDAO();
        this.shiftDAO=new ShiftDAO();
        this.itemsQuantityDAO=new ItemsQuantityDAO();
        this.locItemDocDAO=new LocItemDocDAO();
        this.deliveryDAO=new DeliveryDAO();
        shiftS=new HashMap<>();
        shiftD=new HashMap<>();
        storekeepers=new HashMap<>();
        docID=0;
        shiftId=0;
        stroekeeperid=0;
        loadData();
    }
    private void loadData(){

        //loading all the other data
        LinkedList<Shift> currShifts= new LinkedList<>();
        //1.shifts:
        List<ShiftDTO> shiftsDto=shiftDAO.getAllShifts();
        for(ShiftDTO objShift:shiftsDto){
            LocalDateTime starting = LocalDateTime.parse(objShift.getStartingTime());
            LocalDateTime ending = LocalDateTime.parse(objShift.getEndingTime());
            Shift shToAdd=new Shift(objShift.getShiftId(),starting,ending);
            shiftId=objShift.getShiftId();
            currShifts.add(shToAdd);
        }
        this.shiftId++;

        //2.storeKeeper:
        List<StorekeeperDTO> storeKeepers=storekeeperDAO.getAllStoreKeepes();
        for(StorekeeperDTO sk:storeKeepers){
            //must add the shifts
            stroekeeperid=sk.getStoreKeeperId();
            for (ShiftDTO sh: shiftsDto){
                if(sh.getStoreKeeperId()==sk.getStoreKeeperId()){
                    for (Shift objSh:currShifts) {
                        if (objSh.getShiftid() == sh.getShiftId()) {
                            StoreKeeper storeKeeper = new StoreKeeper(sk.getStoreKeeperId(), sk.getWorkAddress(), objSh);
                            this.stroekeeperid=storeKeeper.getStoreKeeperId();
                            shiftS.put(storeKeeper,objSh);
                        }
                    }
                }
            }
        }
        this.stroekeeperid++;

        //3.ashMap<Driver,Shift> shiftD --> adding shifts to the driver
        for(ShiftDTO objShift:shiftsDto) {
            if(objShift.getDriverId()!= -1){
                LocalDateTime starting = LocalDateTime.parse(objShift.getStartingTime());
                LocalDateTime ending = LocalDateTime.parse(objShift.getEndingTime());
                driverController.updateshifts(objShift.getDriverId(),objShift.getShiftId(),starting,ending);
                Shift sh=new Shift(objShift.getShiftId(),starting,ending);
                shiftD.put(driverController.getDriverById(objShift.getDriverId()),sh);
            }
        }



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
                        deliveryController.getDeliveryById(j.getDeliveryId()).addDestinationAndItems(j.getQid(),location,itemsMap);
                        docID=j.getQid();
                    }

                }

            }
    }

    public String addDelivery(LocalDateTime leavingtime, int truck, int driver, String superLee,LocalDateTime arrivetime) throws SQLException {

        String response=" delivery has been added successfully!";
        int thedayofexit=leavingtime.getDayOfWeek().getValue()+1;
        int thedayofarrive=arrivetime.getDayOfWeek().getValue()+1;
        Driver d1=driverController.getDriverById(driver);
        if(d1.getshift()== null || d1.getshift().isEmpty()){
            response= "the driver has no shifts!";
            return response;
        }
        if(! d1.isAvailable()){
            response=" the driver is not available";
            return  response;
        }
        if(!truckController.getTruckByNumber(truck).isAvailable()){
            response=" the truck is not available";
            return  response;
        }
        LocalDateTime startshiftoddriver=d1.getshift().get(thedayofexit).getStartingTime();
        LocalDateTime endshiftoddriver=d1.getshift().get(thedayofexit).getEndingTime();
        if(leavingtime.isBefore(startshiftoddriver) || arrivetime.isAfter(endshiftoddriver)){
            response= "the driver can't arrive this delivery due to his shifts time!";
            return response;
        }
        //if(truckController.getTruckByNumber(truck).isAvailable()&&driverController.getDriverById(driver).isAvailable()) {
        response+= deliveryController.addDelivery(leavingtime, truckController.getTruckByNumber(truck), driverController.getDriverById(driver), new Location(0, "superLee", "", "", ""),arrivetime);
            //update in the database
            for (Driver d : driverController.getAllDrivers()) {
                if (d.getHumanId() == driver) {
                    d.setAvailable(false);
                    //update in the database
                    driverController.getdriverDao().updateisAvailable(d.getHumanId(),"false");
                    response+= "+ the driver's availability has been updated!";
                }
            }
            for (Truck t : truckController.getAllTrucks()) {
                if (t.getNumber() == truck) {
                    t.setAvailable(!t.isAvailable());
                    //update in the database
                    truckController.getTruckDao().updateisAvailable(t.getNumber(),"false");
                    response+= "+ the driver's availability has been updated!";
                }
            }

        return response;
    }

    public String addDoc(HashMap<Item,Integer> toadd,int deliveryid,Location l) throws InterruptedException, SQLException {
        String response="documentation has been added successfully!\n";
        for(StoreKeeper s:storekeepers.values()){
            if(s.getWorkAddress().equals(l.getAddress())){

                int theday=deliveryController.getDeliveryById(deliveryid).getTime().getDayOfWeek().getValue()+1;
                int thestoreday=shiftS.get(s).getStartingTime().getDayOfWeek().getValue()+1;
                if(thestoreday!=theday){
                    response="there is no storekeeper to get the delivery";
                    return response;
                }
                else {
                    LocalDateTime deliveryend=deliveryController.getDeliveryById(deliveryid).getArrivetime();
                    LocalDateTime startstorekeeper = shiftS.get(s).getStartingTime();
                    LocalDateTime endstorekeeper = shiftS.get(s).getEndingTime();
                    if(deliveryend.isAfter(endstorekeeper)||deliveryend.isBefore(startstorekeeper)){
                        response= "there is no storekeeper to get the delivery at this time";
                        return response;
                    }
                    else {


                        response ="the delivery can go";
                    }

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
        return response;
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
                    truckController.getTruckDao().updateisAvailable(deliveryController.getDeliveryById(deliveryid).getTruck().getNumber(),"true");
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


    public String printshifts(){
        String str="";
        for(Shift s:shiftS.values()){
            str+="shift id :"+s.getShiftid()+"   startingtime:"+s.getStartingTime()+"   endingtime  :"+s.getEndingTime() + "   the day is:  "+ s.getStartingTime().getDayOfWeek();//s.getStartingTime().getDayOfWeek().getValue()+1
        }
        for(Shift s:shiftD.values()){
            str+="shift id :"+s.getShiftid()+"   startingtime:"+s.getStartingTime()+"   endingtime  :"+s.getEndingTime() + "   the day is:  "+ s.getStartingTime().getDayOfWeek();//s.getStartingTime().getDayOfWeek().getValue()+1
        }

        return str;
    }



    public String addShiftsStoreKeeper( String workaddress, LocalDateTime start, LocalDateTime end) throws SQLException {
        //shift id is not unique
        if(storekeepers.isEmpty()){
            Shift s=new Shift(shiftId,start,end);
            StoreKeeper st=new StoreKeeper(stroekeeperid,workaddress,s);
            shiftS.put(st,s);
            storekeepers.put(stroekeeperid,st);
            StorekeeperDTO store=new StorekeeperDTO(stroekeeperid,workaddress);
            storekeeperDAO.addStorekeeper(store);
            ShiftDTO shift=new ShiftDTO(shiftId, start.toString(),end.toString(),-1,stroekeeperid);
            shiftDAO.addshift(shift);
            stroekeeperid++;
            shiftId++;
            return "added storekeeper and it's shift successfully!";
        }
        else{
            for(StoreKeeper i:storekeepers.values()){
            if(!i.getWorkAddress().equals(workaddress)){
                Shift s=new Shift(shiftId,start,end);
                StoreKeeper st=new StoreKeeper(stroekeeperid,workaddress,s);
                shiftS.put(st,s);
                storekeepers.put(stroekeeperid,st);
                StorekeeperDTO store=new StorekeeperDTO(stroekeeperid,workaddress);
                storekeeperDAO.addStorekeeper(store);
                ShiftDTO shift=new ShiftDTO(shiftId, start.toString(),end.toString(),-1,stroekeeperid);
                shiftDAO.addshift(shift);
                stroekeeperid++;
                shiftId++;
                return "added storekeeper and it's shift successfully!";
            }
        }
        }
        return "storekeeper already exist";
    }
    public String updateshifts(int driverid, LocalDateTime start, LocalDateTime end) throws SQLException {
        if(driverController.getDriverById(driverid)== null)
            return "could not add shift to this driver: driver does not exist!";
        driverController.updateshifts(driverid,shiftId,start,end);
        shiftD.put(driverController.getDriverById(driverid),new Shift(shiftId,start,end));
        ShiftDTO shift=new ShiftDTO(shiftId, start.toString(),end.toString(),driverid,-1);
        shiftDAO.addshift(shift);
        shiftId++;
        return "shift has been added successfully to this driver!";
    }
    public String removeDriver(int id) throws SQLException {
        String response="";
        Driver toDelete=driverController.removeDriver(id);
        if( toDelete != null){
            //delete his shifts
            for (Shift shift: toDelete.getshift().values()){
                shiftDAO.deleteshift(shift.getShiftid(),toDelete.getHumanId(),-1);
            }
            response+= " all the shits has been deleted successfully!";

        }else response+= "could not delete the driver: the driver does not exist";
        return response;
    }
    //public String removeStore

}