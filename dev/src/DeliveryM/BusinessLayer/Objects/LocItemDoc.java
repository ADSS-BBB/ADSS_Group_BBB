package DeliveryM.BusinessLayer.Objects;

import java.util.HashMap;
import java.util.Map;

public class LocItemDoc{

	private int deliveryId;
	private int docId;
	private HashMap<Item,Integer> locItems;//id item,
	private int currTruckWeight;
	private String addressLoc;
	public String contactNumber;
	public String contactName;

	public LocItemDoc(int deliveryid,int docid,String addressloc,HashMap<Item,Integer> locItems,int truckweight,String contactName,String contactNumber){

		this.deliveryId =deliveryid;
		this.docId =docid;
		this.addressLoc =addressloc;
		this.locItems=locItems;
		this.currTruckWeight =truckweight;
		this.contactName=contactName;
		this.contactNumber=contactNumber;

	}
	public int itemsweight(){
		int sum=0;
		for(Item i:locItems.keySet()){
			sum+=i.getItemWeight()*locItems.get(i);
		}
		return sum;
	}
	public int getDeliveryId(){return deliveryId;}
	public int getDocId(){return docId;}
	public String getAddressLoc(){return addressLoc;}
	public HashMap<Item,Integer> getLocItems(){return locItems;}
	public int getCurrTruckWeight(){return currTruckWeight;}
	public String getContactName(){return contactName;}
	public String getContactNumber(){return contactNumber;}
	public void setItems(HashMap<Item,Integer> n){locItems=n;}
	public void setWeight(int n){
		currTruckWeight =n;}


	public void printDetails() {
		int cur=currTruckWeight+itemsweight();
		System.out.println("LocItemDoc{");
		System.out.println("  deliveryId=" + deliveryId + ",");
		System.out.println("  docId=" + docId + ",");
		System.out.println("  addressLoc='" + addressLoc + "',");
		//System.out.println("  currTruckWeight=" + cur + ",");
		System.out.println("  contactName='" + contactName + "',");
		System.out.println("  contactNumber='" + contactNumber + "',");

		System.out.println("  locItems={");
		for (Item i:locItems.keySet()) {
			System.out.println("    " + i.getNameItem() + ": " + locItems.get(i) + ",");
		}
		System.out.println("  }");
		System.out.println("}");
	}








}