package DeliveryM.BusinessLayer.Controllers;

import DeliveryM.BusinessLayer.Objects.Location;

import java.util.HashMap;
import java.util.Map;

public class LocationController {
    private HashMap<Integer, Location> locations;
    int locationId;
    public LocationController() {
        this.locations = new HashMap<>();
        locationId=0;
    }

    public void addLocation(String address,String contactNumber,String contactname,String area ) {
        Location toadd=new Location(locationId,address,contactNumber,contactname, area);
        if (!locations.containsKey(locationId)) {
            locations.put(locationId, toadd);
            locationId++;
        } else {
            System.out.println("Location with this ID already exists.");
        }

    }
    public Location getLocation(int id) {
        return locations.get(id);
    }

    public Location getLocationbyADD(String address) {
        for (Location i:locations.values()){
            if(i.getAddress()==address){
                return i;
            }
        }

        return locations.get(address);
    }

    public void removeLocation(int id) {
        if (locations.containsKey(id)) {
            locations.remove(id);
        } else {
            System.out.println("Location with this ID does not exist.");
        }
    }
    public void removeLocation(String address){
        for(Location l:locations.values()){
            if(l.getAddress()==address){
                locations.remove(l.getLocationId());
                System.out.println("the location deleted");
                break;
            }
        }
    }
    public void updateLocation(int id, Location location) {
        if (locations.containsKey(id)) {
            locations.put(id, location);
        } else {
            System.out.println("Location with this ID does not exist.");
        }
    }
    public HashMap<Integer, Location> allLocations(){
        return locations;
    }
    public void listLocations() {
        for (Map.Entry<Integer, Location> entry : locations.entrySet()) {
            System.out.println("ID: " + entry.getKey() + ", Location: " + entry.getValue());
        }
    }

    public void printAllLocations(){

        for (Location d : locations.values()) {
            System.out.println("Location ID: " + d.getLocationId() +
                    ", Address: " + d.getAddress() +
                    ", Contact Name: " + d.getContactName() +
                    ", Contact Number: " + d.getContactNumber() +
                    ", Area: " + d.getArea());

        }

    }
}
