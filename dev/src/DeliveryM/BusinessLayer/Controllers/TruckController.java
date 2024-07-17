package DeliveryM.BusinessLayer.Controllers;

import DeliveryM.BusinessLayer.Objects.Driver;
import DeliveryM.BusinessLayer.Objects.Truck;
import DeliveryM.DataAccessLayer.DAOs.DriverDAO;
import DeliveryM.DataAccessLayer.DAOs.TruckDAO;
import DeliveryM.DataAccessLayer.DTOs.DriverDTO;
import DeliveryM.DataAccessLayer.DTOs.TruckDTO;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TruckController {
    private List<Truck> trucks;
    private TruckDAO truckDao;
    private static TruckController instance;

    public TruckController() throws ClassNotFoundException {
        this.trucks = new LinkedList<>();
        this.truckDao=new TruckDAO();
       // this.loadData();
    }

    public void loadData(){
        List<TruckDTO> TruckDTOs=truckDao.getAllTrucks();
        for(TruckDTO d:TruckDTOs){
            Truck dc=new Truck(d.getNumber(),d.getModel(),d.getWeight(),d.getMaxWeight());
            trucks.add(dc);
            if(d.isAvailable().equals("true")) {
                dc.setAvailable(true);
            }
            else dc.setAvailable(false);

        }
    }
    public static TruckController getInstance() throws Exception {
        if (instance == null) {
            instance = new TruckController();
        }
        return instance;
    }
    public TruckDAO getTruckDao(){
        return this.truckDao;
    }
    public String addTruck(Truck truck) throws SQLException {
        String response="truck has been added successfully!";
        boolean exists = false;
        for (Truck t : trucks) {
            if (t.getNumber()==(truck.getNumber())) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            trucks.add(truck);
            truckDao.addTruck(new TruckDTO(truck.getNumber(),truck.getModel(),truck.getTruckWeight(),truck.getMaxWeight(),"true"));
        } else {
            response= "Truck with the same ID already exists.";
        }
        return response;
    }

    public boolean  removeTruckByNumber(int number) {
        try{
            Iterator<Truck> iterator = trucks.iterator();
            while (iterator.hasNext()) {
                Truck truck = iterator.next();
                if (truck.getNumber() == number) {
                    iterator.remove();
                    truckDao.deleteTruck(number);
                    return true;
                }
            }
        }catch (Exception e){
            System.out.println("cant remove truck" + e.getMessage());
        }
        return false;
    }

    public Truck getTruckByNumber(int number) {
        try{for (Truck truck : trucks) {
            if (truck.getNumber() == number) {
                return truck;
            }
        }
        }catch (Exception e){
            System.out.println("the truck not exist");
        }

        return null;
    }

    public List<Truck> getAllTrucks() {
        return trucks;
    }
    public Truck getSuitTruck(int currweight){
        try{for(Truck t:trucks){
            if(t.getMaxWeight()>currweight&&t.isAvailable()) return t;
        }}catch (Exception e){System.out.println("the truck is empty");}
        return null;
    }
    public String printAllTruck(){
        String str="";
        for(Truck t:trucks){
            str+="\nTruck Number: " + t.getNumber() +
                    ", Model: " + t.getModel() +
                    ", Truck Weight: " + t.getTruckWeight() +
                    ", Max Weight: " + t.getMaxWeight() +
                    ", Is Available: " + t.isAvailable();
        }
        return str;
    }
}
