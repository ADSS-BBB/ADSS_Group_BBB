package DeliveryM.BusinessLayer.Controllers;

import DeliveryM.BusinessLayer.Objects.Item;
import DeliveryM.BusinessLayer.Objects.LocItemDoc;
import DeliveryM.BusinessLayer.Objects.Location;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ItemsController {
    private Map<Item, Location> items;//item, destination
    //private List<String> areas;
    //must do add location somewhere
    private List<Location> locations;
    private int itemId;
    private int locationId;
    private int docCnt;
    public ItemsController(){
        this.items=new HashMap<>();
        itemId=0;
        locationId =0;
        docCnt=0;

    }
    //int id ,String name,int weight,int quantity
    //String address,String contactNumber,String contactName,String area
    public void addItem(String itemName, int weight, int quantity,String address,String contantNumber,String contantName,String area){
        Location loc=new Location(locationId,address,contantNumber,contantName,area);
        items.put(new Item(itemId,itemName,weight,quantity),new Location(locationId,address,contantNumber,contantName,area));
        locations.add(loc);
        itemId++;
        locationId++;
    }
    public Location addSourceLoc(String address,String contantNumber,String contantName,String area){
        Location loc=new Location(locationId,address,contantNumber,contantName,area);
        locationId++;
        return loc;

    }
    public Map<String,List<Item> >getItemsToDeliver(){
        //area 1, the items to deliver to that area
        Map<String,List<Item> >toReturn=new HashMap<>();
        List<String> areas=new LinkedList<>();
        //int cnt=0;
        for (Map.Entry<Item, Location> entry : items.entrySet()) {
            Item item = entry.getKey();
            Location loc = entry.getValue();
            String area=loc.getarea();
            if(!containsArea(areas,area)) {
                List<Item> itemsByArea = getItemsByArea(area);
                areas.add(area);
                if (itemsByArea != null) {
                    toReturn.put(area,itemsByArea);
                    //cnt++;
                }
            }
        }
        return toReturn;
    }
    private boolean containsArea(List<String>areas,String area){
        return areas.contains(area);
    }

    private List<Item> getItemsByArea(String area){
        List<Item> toReturn=new LinkedList<>();
        boolean flag=false;
        for (Map.Entry<Item, Location> entry : items.entrySet()) {
            Item item = entry.getKey();
            Location loc = entry.getValue();
            if(loc.getarea().equals(area)){
                toReturn.add(item);
                flag=true;
            }
        }
        if(flag)
             return toReturn;
        else return null;
    }
    public List<Location> getDestinationsByArea(String area){
        List<Location> toReturn= new LinkedList<>();
        for (Map.Entry<Item, Location> entry : items.entrySet()) {
            Item item = entry.getKey();
            Location loc = entry.getValue();
            if (loc.getarea() == area)
                toReturn.add(loc);
        }
        return toReturn;

    }
    public boolean deleteItemsAndItsLoc(int itemId){
        for (Map.Entry<Item, Location> entry : items.entrySet()) {
            Item item = entry.getKey();
            Location loc = entry.getValue();
            if(item.getIdItem()==itemId){
                items.remove(item);
                for (int i=0;i<locations.size();i++)
                    if(locations.get(i).getlocationid()==loc.getlocationid())
                        locations.remove(i);
                return true;
            }
        }
        return false;
    }
    public int getWeightOfItems(List<Item> toSum){
        int sum=0;
        for (int i=0;i<toSum.size();i++){
            sum+= toSum.get(i).getItemWeight()* toSum.get(i).getQuantity();
        }
        return sum;

    }
    public List<LocItemDoc> createDoc(int delId, List<Location> dests, int truckWeight,List<Item> items){
            return null;
    }
}
