
package DeliveryM.BusinessLayer.Objects;
import java.time.LocalTime;
import java.util.*;
import  java.time.Clock;
import java.util.Date;
import java.util.List;

public class Delivery {

	private int id;
	private Date date;
	private String time;
	private Driver driver;
	private Truck truck;
	private Location source;
	private List<Location> destinations;
	private HashMap<Item,Integer> quantity;
	private HashMap<Integer,LocItemDoc> docs;
	//private HashMap<Location,List<Item>> itemsforlocation;

	// Constructor
	public Delivery(int id, Date date, String time, Driver driver,Truck truck , Location source) {
		this.id = id;
		this.date = date;
		this.time = time;
		this.driver = driver;
		this.truck=truck;
		this.source = source;
		this.destinations = new LinkedList<>();
		this.quantity=new HashMap<>();
		this.docs=new HashMap<>();
		//this.itemsforlocation=new HashMap<>();
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getDriverid() {
		return driver.getHumanId();
	}
	public Driver getDriver(){
		return this.driver;
	}
	public void setDriver(Driver driver) {
		this.driver=driver;
	}

	public int getTruckWeight() {
		return truck.getTruckWeight();
	}

	public Truck getTruck() {
		return truck;
	}

	public boolean changeTruck(Truck truck) {
		this.truck.setAvailable();
		this.truck = truck;
		return true;
	}

	public int getTruckNumber() {
		return truck.getNumber();
	}
	//	public int getTruckMaxWeight(){
//		return this.truck.getMaxWeight();
//	}
	public Location getSource() {
		return source;
	}

	public void setSource(Location source) {
		this.source = source;
	}
	public LocItemDoc getdocusingIdofDoc(int docid){
		return docs.get(docid);
	}
	public List<Location> getDestinations() {
		return destinations;
	}


	//	private List<Location> destinations;//
//	private HashMap<Item,Integer> quantity;
//	private HashMap<Integer,LocItemDoc> docs;
//	private HashMap<Location,List<Item>> itemsforlocation;//

	public void addDestinationAndItems(int docId,Location newDest,HashMap<Item,Integer> order){//location hashmap<item,quantity>asf;
		//itemsforlocation.put(newDest,new LinkedList<>(order.keySet()));
		int sum=fixweight(order);
		this.truck.setTruckWeight(sum);
		LocItemDoc newdoc=new LocItemDoc(this.id,docId,newDest.getAddress(),order,this.truck.getTruckWeight(), newDest.getContactName(), newDest.getContactNumber());
		docs.put(docId,newdoc);
		destinations.add(newDest);
		int i=0,new1;
		for(Item item : order.keySet()) {

			if (quantity.get(item) == null)
				quantity.put(item, order.get(item));

			else {
				new1 = quantity.get(item);
				quantity.put(item, new1 + order.get(item));
			}

		}
	}
	public int  fixweight(HashMap<Item,Integer> order){
		int sum=0;
		for(Item i:order.keySet()){
			sum+=i.getItemWeight()*order.get(i);
		}
		return sum;
	}
	public boolean deleteDestinationById(int destId, int docId) {
		// Assuming 'docs' is a Map, we remove the entry with the key 'docId'
		docs.remove(docId);

		// To avoid ConcurrentModificationException, we use an iterator
		Iterator<Item> iterator = quantity.keySet().iterator();
		while (iterator.hasNext()) {
			Item j = iterator.next();
			if (j.getDestinationId() == destId) {
				iterator.remove(); // Safe removal during iteration
				quantity.remove(j); // Also remove from the 'quantity' map
				break;
			}
		}

		// Remove destination from 'destinations' if not empty
		if (!destinations.isEmpty()) {
			destinations.removeIf(i -> i.getLocationId() == destId);
		}

		return true;
	}

	public boolean removeItemsFromDelivery() {
		int currWeight = getWeightDelivery();
		int maxWeight = truck.getMaxWeight()-truck.getTruckWeight();

		if (currWeight <= maxWeight) {
			return true;
		}

		while (currWeight > maxWeight) {
			boolean weightReduced = false;
			for (Map.Entry<Item, Integer> entry : quantity.entrySet()) {
				Item item = entry.getKey();
				int itemQuantity = entry.getValue();

				if (itemQuantity > 0) {

					quantity.put(item, itemQuantity - 1);
					for(LocItemDoc i : docs.values()){
						if(i.getLocItems().keySet().contains(item))	{
							i.getLocItems().put(item,i.getLocItems().get(item)-1);
							if(i.getLocItems().get(item)==0){
								i.getLocItems().remove(item);

							}
							break;
						}
					}
					currWeight -= item.getItemWeight();
					weightReduced = true;

					if (currWeight <= maxWeight) {
						break;
					}
				}
			}
			for(LocItemDoc i:docs.values()){
				if(i.getLocItems().isEmpty()){
					docs.remove(i.getDocId());
					break;
				}
			}

			// If no weight was reduced in this iteration, break the loop
			if (!weightReduced) {
				break;
			}
		}

		return currWeight <= maxWeight;
	}





	public List<Item> getItems() {
		return new LinkedList<>(quantity.keySet());
	}
	public HashMap<Item,Integer> getQuantity(){
		return quantity;
	}


	public void addOneItem(Item i,int quantity1){
		if(quantity.containsKey(i)) {
			quantity.put(i,quantity.get(i)+quantity1);
		}
		else{quantity.put(i,quantity1);}
	}

	public void ChangeOneItem(Item i,int quantity1){
		if(quantity.containsKey(i)) {
			quantity.put(i,quantity1);
		}
	}


	public int getWeightDelivery(){
		int quan,weight,totalWeight=0;
		for(Item i:quantity.keySet()){
			quan=quantity.get(i);
			weight=i.getItemWeight();
			totalWeight+=quan*weight;
		}
		return totalWeight;
	}


	public HashMap<Item,Integer> getDocUsingDestId(int destid){
		HashMap<Item,Integer> a=new HashMap<>();
		for(Item i:quantity.keySet()){
			if(i.getDestinationId()==destid){
				a.put(i,quantity.get(i));
			}
		}
		return a;
	}
	public HashMap<Item,Integer> getDocUsingDestLocation(Location dest){
		HashMap<Item,Integer> a=new HashMap<>();
		for(Item i:quantity.keySet()){
			if(i.getDestinationId()==dest.getLocationId()){
				a.put(i,quantity.get(i));
			}
		}
		return a;
	}
	public HashMap<Integer,LocItemDoc> getdoc(){
		return docs;
	}
	public HashMap<Item,Integer> getquantity(){
		return quantity;
	}
}
