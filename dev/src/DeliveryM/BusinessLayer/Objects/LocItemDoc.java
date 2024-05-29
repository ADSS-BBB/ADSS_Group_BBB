package DeliveryM.BusinessLayer.Objects;

import java.util.HashMap;

public class LocItemDoc{

	private int deliveryid;
	private int docid;
	private String addressloc;
	private HashMap<String,Integer> locItems;
	private int currtruckweight;
	public String contactNumber;
	public String contactName;

	public LocItemDoc(int deliveryid,int docid,String addressloc,HashMap<String,Integer> locItems,int truckweight,String contactName,String contactNumber){
	
		this.deliveryid=deliveryid;
		this.docid=docid;
		this.addressloc=addressloc;
		this.locItems=locItems;
		this.currtruckweight=truckweight;
		this.contactName=contactName;
		this.contactNumber=contactNumber;

	}

	public int getdeliveryid(){return deliveryid;}
	public int getDocid(){return docid;}
	public String getAddressloc(){return addressloc;}
	public HashMap<String,Integer> getlocItems(){return locItems;}
	public int getcurrtruckweight(){return currtruckweight;}
	public String getcontactName(){return contactName;}
	public String getcontactNumber(){return contactNumber;}
	public void setitems(HashMap<String,Integer> n){locItems=n;}
	public void addoneitem(String nameitem,Integer sum){locItems.put(nameitem,sum);}
	public void setWeight(int n){currtruckweight=n;}


	








}