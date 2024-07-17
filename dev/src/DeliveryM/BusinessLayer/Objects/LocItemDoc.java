package DeliveryM.BusinessLayer.Objects;

import java.util.HashMap;

public class LocItemDoc{

	private int deliveryId;
	private int docId;
	private HashMap<Item,Integer> locItems;//item : quantity
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
	public void addItems(HashMap<Item,Integer> locItem){
		for(Item i:locItem.keySet()){
			if(locItems.containsKey(i)){
				int j=locItems.get(i)+locItem.get(i);
				locItems.put(i,j);
			}
			else {locItems.put(i,locItem.get(i));}
		}

	}
	public int getDeliveryId(){return deliveryId;}
	public int getDocId(){return docId;}
	public String getAddressLoc(){return addressLoc;}
	public HashMap<Item,Integer> getLocItems(){return locItems;}
	public int getCurrTruckWeight(){return currTruckWeight;}
	public String getContactName(){return contactName;}
	public String getContactNumber(){return contactNumber;}
	public void setItems(HashMap<Item,Integer> n){
		for(Item i:locItems.keySet()){
			for(Item t:n.keySet()){
				if(i.getNameItem().equals(t.getNameItem())){
					int s=locItems.get(i)+n.get(t);
					locItems.replace(t,locItems.get(i),s);
				}
			}
		}
	}
	public void setWeight(int n){
		currTruckWeight =n;}


	public String printDetails() {
		String str="";
		int cur=currTruckWeight+itemsweight();
		str+= "LocItemDoc{";
		str+= "  deliveryId=" + deliveryId + ",";
		str+= "  docId=" + docId + ",";
		str+= "  addressLoc='" + addressLoc + "',";
		//System.out.println("  currTruckWeight=" + cur + ",");
		str+= "  contactName='" + contactName + "',";
		str+= "  contactNumber='" + contactNumber + "',";
		str+= "  locItems={";
		for (Item i:locItems.keySet()) {
			str+= "    " + i.getNameItem() + ": " + locItems.get(i) + ",";
		}
		str+= "  }";
		str+= "}";
		return str;
	}








}