package DeliveryM.BusinessLayer.Objects;

public class Location {

	private String address;
	private String contactNumber;
	private String contactName;
	private String area; //add

	public Location (String address,String contactNumber,String contactName,String area){
		
		
		this.address=address;
		this.contactNumber=contactNumber;
		this.contactName=contactName;
		this.area=area;
		
	}

	
	public String getlocationaddress(){return address;}
	public String getarea(){return area;}
    public String getcontactnumber() {return contactNumber;}
    public String getcontactname() {return contactName;}

}