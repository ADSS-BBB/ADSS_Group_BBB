public class LocItemDoc{

	private int deliveryid;
	private int docid;
	private String addressloc;
	private Map<int,int> locItems;
	private int currtruckweight;
	public String contactNumber;
	public String contactName;

	public LocItemDoc(int deliveryid,int docid,Strin addressloc,Map<int,int> locItems,int truckweight,String contactName,String contactNumber){
	
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
	public Map<int,int> getlocItems(){return locItems;}
	public int getcurrtruckweight(){return currtruckweight;}
	public String getcontactName(){return contactName;}
	public String getcontactNumber(){return contactNumber;}
	public void setitems(Map<int,int> n){locItems=n;}
	public void setWeight(int n){truckweight=n;}


	








}