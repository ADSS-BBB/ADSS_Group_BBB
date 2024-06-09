package DeliveryM.BusinessLayer.Controllers;

import DeliveryM.BusinessLayer.Objects.Truck;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TruckController {
    private List<Truck> trucks;

    public TruckController() {
        this.trucks = new LinkedList<>();
    }

    public void addTruck(Truck truck) {
        boolean exists = false;
        for (Truck t : trucks) {
            if (t.getNumber()==(truck.getNumber())) {
                exists = true;
                break;
            }
        }

        if (!exists) {
            trucks.add(truck);
        } else {
            System.out.println("Truck with the same ID already exists.");
        }
    }

    public boolean  removeTruckByNumber(int number) {
        try{
            Iterator<Truck> iterator = trucks.iterator();
            while (iterator.hasNext()) {
                Truck truck = iterator.next();
                if (truck.getNumber() == number) {
                    iterator.remove();
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
            if(t.getMaxWeight()>currweight) return t;
        }}catch (Exception e){System.out.println("the truck is empty");}
        return null;
    }
    public boolean updateTruckAvailability(int number) {
        try{
            Truck truck = getTruckByNumber(number);
            if (truck != null) {
                truck.setAvailable();
                return true;
            }}catch (Exception e){
            System.out.println("truck not exist");
        }
        return false;
    }

    public void printAllTruck(){
        for(Truck t:trucks){
            System.out.println("Truck Number: " + t.getNumber() +
                    ", Model: " + t.getModel() +
                    ", Truck Weight: " + t.getTruckWeight() +
                    ", Max Weight: " + t.getMaxWeight() +
                    ", Is Available: " + t.isAvailable());
        }
    }
}
