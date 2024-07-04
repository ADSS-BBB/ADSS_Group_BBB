package DeliveryM.BusinessLayer.Controllers;

import DeliveryM.BusinessLayer.Objects.*;
import DeliveryM.DataAccessLayer.DAOs.*;
import DeliveryM.DataAccessLayer.DTOs.DeliveryDTO;
import DeliveryM.DataAccessLayer.DTOs.DriverDTO;
import DeliveryM.DataAccessLayer.DTOs.LocationDTO;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

public class DeliveryController {
    private HashMap<Integer,Delivery> deliveries;
    private int deliveryID;
    private DeliveryDAO deliveryDao;
    private DriverDAO driverDao;
    private TruckDAO truckDao;

    private LocationDAO locationDao;
    private ItemsQuantityDAO itemsQuantityDAO;

    public DeliveryController() throws Exception {
        deliveries=new HashMap<>();
        this.deliveryDao=new DeliveryDAO();
        this.driverDao=new DriverDAO();
        this.truckDao=new TruckDAO();
        this.locationDao=new LocationDAO();
        deliveryID=0;
        this.loadData();
    }
    private void loadData() throws Exception {
        List<DeliveryDTO> delDTOs = deliveryDao.getAllDeliveries();
        for(DeliveryDTO d: delDTOs){
            LocalDateTime leaving = LocalDateTime.parse(d.getExitTime());
            LocalDateTime arrive = LocalDateTime.parse(d.getArrivalTime());
            Driver driver=DriverController.getInstance().getDriverById(d.getDriverId());
            Truck truck= TruckController.getInstance().getTruckByNumber(d.getTruckId());

            Delivery toAdd= new Delivery(d.getId(),leaving,driver,truck,new Location(-1,"here","00000","","superlee"),arrive);

            deliveries.put(d.getId(),toAdd);
            deliveryID++;
        }
    }


    //add delivery
    public String addDelivery( LocalDateTime leavingtime, Truck truck, Driver driver, Location LOC, LocalDateTime arrive) throws SQLException {
        if(truck.getMaxWeight()<=driver.getMaxWeightToDriver(driver.getHumanId())){
            Delivery d=new Delivery(deliveryID,leavingtime,driver,truck,LOC,arrive);
            deliveries.put(deliveryID,d);
            DeliveryDTO d1=new DeliveryDTO(deliveryID,leavingtime.toString(),arrive.toString(),truck.getNumber(),driver.getHumanId(),-1);
            deliveryDao.addDelivery(d1);
            //i need to fix is aavaliable
            deliveryID++;
            return "confirmed:add! ";
        }else{
            return "The driver can drive this truck due to his license and weights issues!";
        }

    }


    //delete delivery
    public boolean deleteDeliveryById(int id) throws SQLException {
        int t=deliveries.get(id).getTruck().getNumber();
        int d=deliveries.get(id).getDriver().getHumanId();
        deliveries.get(id).getTruck().setAvailable(true);
        deliveries.get(id).getDriver().setAvailable(true);
        //i need to fix available in the database
        deliveries.remove(id);
        driverDao.updateisAvailable(d,"true");
        truckDao.updateisAvailable(t,"true");
        deliveryDao.deleteDelivery(id);

        return true;
    }

    //get delivery
    public Delivery getDeliveryById(int id) {
        return deliveries.get(id);
    }
    //get all deliveries
    public HashMap<Integer, Delivery> getAllDeliveries() {
        return deliveries;
    }

    //3)remove items
    public boolean removeItems(int deliveryId){
        deliveries.get(deliveryId).removeItemsFromDelivery();
        return true;
    }

    public String printAllDeliveries() {
        String str="";
        for (Delivery d : deliveries.values()) {
            str+="\nDelivery ID: " + d.getId() +
                    ", ExitTime: " + d.getTime() +
                    ", Driver ID: " + d.getDriverid() +
                    ", Truck Weight: " + d.getTruck().getTruckWeight() +
                    ", Truck Number: " + d.getTruckNumber() +
                    ", Source: " + d.getSource().toString() +
                    ",ArriveTime:" + d.getArrivetime().toString();
        }
        return str;
    }

    public String printall(int deliveryid ){
        String str="";
        HashMap<Integer,LocItemDoc> a=this.getDeliveryById(deliveryid).getdoc();
        for(LocItemDoc i:a.values()){
            str+= i.printDetails();
        }
        return str;
    }
    public void updateQuantities(int deliveryID,String itemName,int q,int itemWeight){
        Item toAdd=new Item(itemName,itemWeight,-1);
        getDeliveryById(deliveryID).addItem(toAdd,q);
    }

}
