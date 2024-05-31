package DeliveryM.BusinessLayer.Objects;

import java.util.Map;

public class LocItemDoc{

	private int docId;
	private Delivery delivery;
	private Location dest;
	private Map<String,Integer> ItemsQuantity;//item, quantity

	public LocItemDoc(int docsId, Delivery del, Map<String,Integer> ItemsQuantity){
		this.delivery=del;
		this.dest=dest;
		this.ItemsQuantity=ItemsQuantity;

	}

	public String printDoc(){
		String doc;
		doc= "Delivery details:\n date: "+ delivery.getDate()+ " truck's Num: "+ delivery.getTruckNumber() +" driver: "+ delivery.getDriverId();
		doc+= "Source details:\n address: "+ delivery.getSource().getlocationaddress()+" contant name: "+ delivery.getSource().getcontactname()+
				" contant number: "+ delivery.getSource().getcontactnumber();
		doc+= "Destination details:\n address: "+ dest.getlocationaddress()+" contant name: "+ dest.getcontactname()+
				" contant number: "+ dest.getcontactnumber();
		doc+="order content:\n";
		for (Map.Entry<String,Integer> entry : ItemsQuantity.entrySet()) {
			String name = entry.getKey();
			Integer quan = entry.getValue();
			doc+="Item's name: "+ name + " Item's quantity: "+ quan+ "\n";
		}

		return doc;
	}










}