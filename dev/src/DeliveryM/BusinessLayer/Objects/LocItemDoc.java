package DeliveryM.BusinessLayer.Objects;

import java.util.Map;

public class LocItemDoc{

	private int deliveryId;
	private int docId;
	private String addressLoc;
	private Map<Integer,Integer> locItems;//id item,
	private int currTruckWeight;
	public String contactNumber;
	public String contactName;

	public LocItemDoc(int deliveryid,int docid,String addressloc,Map<Integer,Integer> locItems,int truckweight,String contactName,String contactNumber){
	
		this.deliveryId =deliveryid;
		this.docId =docid;
		this.addressLoc =addressloc;
		this.locItems=locItems;
		this.currTruckWeight =truckweight;
		this.contactName=contactName;
		this.contactNumber=contactNumber;

	}

	public int getDeliveryId(){return deliveryId;}
	public int getDocId(){return docId;}
	public String getAddressLoc(){return addressLoc;}
	public Map<Integer,Integer> getLocItems(){return locItems;}
	public int getCurrTruckWeight(){return currTruckWeight;}
	public String getContactName(){return contactName;}
	public String getContactNumber(){return contactNumber;}
	public void setItems(Map<Integer,Integer> n){locItems=n;}
	public void setWeight(int n){
		currTruckWeight =n;}


	








}