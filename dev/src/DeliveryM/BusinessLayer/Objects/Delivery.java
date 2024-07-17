
package DeliveryM.BusinessLayer.Objects;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import  java.time.Clock;
import java.util.Date;
import java.util.List;

public class Delivery {

	private int id;
	//private Date date;
	private LocalDateTime leaving;
	private LocalDateTime arrivetime;
	private Driver driver;
	private Truck truck;
	private Location source;
	private List<Location> destinations;
	private HashMap<Item,Integer> quantity;
	private HashMap<Integer,LocItemDoc> docs;


	// Constructor
	public Delivery(int id, LocalDateTime leaving, Driver driver,Truck truck , Location source,LocalDateTime arrivetime) {
		this.id = id;
		this.leaving = leaving;
		this.driver = driver;
		this.truck=truck;
		this.source = source;
		this.destinations = new LinkedList<>();
		this.quantity=new HashMap<>();
		this.docs=new HashMap<>();
		this.arrivetime=arrivetime;

	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public LocalDateTime getTime() {
		return leaving;
	}

	public void setTime(LocalDateTime time) {
		this.leaving = time;
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
		this.truck.setAvailable(false);
		this.truck = truck;
		this.truck.setAvailable(true);
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
	public LocalDateTime getArrivetime(){
		return this.arrivetime;
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



	public void addDestinationAndItems(int docId,Location newDest,HashMap<Item,Integer> order){//location hashmap<item,quantity>asf;
		int sum=fixweight(order);
		this.truck.setTruckWeight(sum);
		if(destinations.contains(newDest)){
			for(LocItemDoc locItemDoc:docs.values()){
				if(locItemDoc.getAddressLoc().equals(newDest.getAddress())){
					locItemDoc.setItems(order);
				}
			}
		}


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
	//important
	public int getwieght(){
		int DelWeight=0;
		for(LocItemDoc i:docs.values()){
			DelWeight+=i.itemsweight();
		}
		//		return DelWeight+this.getTruckWeight();
		return DelWeight;
	}
	public int  fixweight(HashMap<Item,Integer> order){
		int sum=getwieght();
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




	public int getWeightDelivery(){
		int quan,weight,totalWeight=0;
		for(Item i:quantity.keySet()){
			quan=quantity.get(i);
			weight=i.getItemWeight();
			totalWeight+=quan*weight;
		}
		return totalWeight;
	}


	public HashMap<Integer,LocItemDoc> getdoc(){
		return docs;
	}

	public void addItem(Item item,int q){
		this.quantity.put(item,q);
	}
}
