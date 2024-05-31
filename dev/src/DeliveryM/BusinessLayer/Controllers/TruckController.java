package DeliveryM.BusinessLayer.Controllers;

import DeliveryM.BusinessLayer.Objects.Driver;
import DeliveryM.BusinessLayer.Objects.Truck;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TruckController {
    private List<Truck> trucks;//id , truck itself
    private int truckId;
    public TruckController(){
        this.trucks=new LinkedList<>();
    }
    public void addTruck(int number,String model,int truckWeight,int maxWeight) throws Exception{
        //if number exists
        for (int i=0;i<trucks.size();i++) {
            if(trucks.get(i).getNumber()== number)
              throw new Exception("truck already exists!");
        }

        Truck toAdd= new Truck(number, model,truckWeight,maxWeight,true);
        trucks.add(toAdd);
    }
    public boolean updateIsAvaliable(int number){
        for (int i=0;i<trucks.size();i++){
            if(trucks.get(i).getNumber()==number) {
                trucks.get(i).setIsAvailableTruck();
                return true;
            }
        }
        return false;
    }
    private boolean checkAvaliable(int number) {
        for (int i = 0; i < trucks.size(); i++) {
            if (trucks.get(i).getNumber() == number) {
                return trucks.get(i).getIsAvailableTruck();
            }
        }
        return false;
    }
    private boolean checkSum(int number, int sum){
            for (int i = 0; i < trucks.size(); i++) {
                if (trucks.get(i).getNumber() == number) {
                    return trucks.get(i).getMaxWeight() <= sum;
                }
            }
            return false;

    }
    public boolean isExist(int number){
        for (int i = 0; i < trucks.size(); i++) {
            if (trucks.get(i).getNumber() == number)
                return true;
        }
            return false;
    }
    public Truck findTruck(int sum){
        for (int i = 0; i < trucks.size(); i++) {
            Truck truck=trucks.get(i);
            if (checkAvaliable(truck.getNumber()) && checkSum(truck.getNumber(), sum)) {
                //must update is avaliable truck in main
                return truck;
            }

        }
        return null;

    }
    public boolean deleteTruck(int number){
        if(!isExist(number)) return false;
        for (int i = 0; i < trucks.size(); i++) {
            if(trucks.get(i).getNumber()==number)
                trucks.remove(i);
            return true;
        }
        return false;//truck does not exist
    }
}
