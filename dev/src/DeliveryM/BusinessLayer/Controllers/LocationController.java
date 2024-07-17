package DeliveryM.BusinessLayer.Controllers;

import DeliveryM.BusinessLayer.Objects.Location;
import DeliveryM.BusinessLayer.Objects.Truck;
import DeliveryM.DataAccessLayer.DAOs.DriverDAO;
import DeliveryM.DataAccessLayer.DAOs.LocationDAO;
import DeliveryM.DataAccessLayer.DTOs.DriverDTO;
import DeliveryM.DataAccessLayer.DTOs.LocationDTO;
import DeliveryM.DataAccessLayer.DTOs.TruckDTO;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationController {
    private HashMap<Integer, Location> locations;
    private LocationDAO locationDAO;
    private static LocationController instance;
    int locationId;
    public LocationController() throws ClassNotFoundException {
        this.locations = new HashMap<>();
        locationDAO=new LocationDAO();
        locationId=0;

    }
    public void loadData() {
        List<LocationDTO> locationDTOs = locationDAO.getAllLocations();
        for(LocationDTO L:locationDTOs) {
            Location toAdd = new Location(L.getId(), L.getAddress(), L.getContactNumber(), L.getContactName(), L.getArea());
            locations.put(locationId, toAdd);
            locationId++;
        }
    }


    public static LocationController getInstance() throws Exception {
        if (instance == null) {
            instance = new LocationController();

        }
        return instance;
    }


    public String addLocation(String address,String contactNumber,String contactname,String area,int id ) throws SQLException {
        Location toadd=new Location(locationId,address,contactNumber,contactname, area);
        String response= "Loaction has been added successfully!";
        int c=0;
        if(locations.isEmpty()){
            locations.put(locationId,toadd);
            locationId++;
            LocationDTO ll=new LocationDTO(locationId,address,contactNumber,contactname,id,area);
            locationDAO.addLocation(ll);
            return response;
        }
        else{
            for(Location l:locations.values()) {
                if (l.getAddress().equals(address)) {
                c++;
                }
            }
            if(c==0){
                locations.put(locationId,toadd);
                locationId++;
                LocationDTO ll=new LocationDTO(locationId,address,contactNumber,contactname,id,area);
                locationDAO.addLocation(ll);
                return response;
            }
        }



        return "Location already exists";
    }
    public Location getLocation(int id) {
        Location l=locations.get(id-1);
        return l;
    }
    public Location getLocationbyadd(String address){
        for(Location l:locations.values()){
            if(address.equals(l.getAddress())){
                return l;
            }
        }
        return null;
    }
    public Location getLocationbyADD(String address) {

        for (Location i:locations.values()){
            if(i==null){
                return null;
            }
            if(i.getAddress().equals(address)){
                return i;
            }
        }

        return null;
    }


    public String removeLocation(String address) throws SQLException {
        String response="could not remove location: the location does not exist";
        for(Location l:locations.values()){
            if(l.getAddress().equals(address)){
                locationDAO.deleteLocation(l.getLocationId());
                locations.remove(l.getLocationId());
                response= "the location has been deleted";
                return response;
            }
        }
        return response;
    }


    public String printAllLocations(){
        String str="";
        for (Location d : locations.values()) {
            str+="\nLocation ID: " + d.getLocationId() +
                    ", Address: " + d.getAddress() +
                    ", Contact Name: " + d.getContactName() +
                    ", Contact Number: " + d.getContactNumber() +
                    ", Area: " + d.getArea();

        }
        return str;

    }
    public HashMap<Integer, Location> allLocations() {
        return locations;
    }
}
