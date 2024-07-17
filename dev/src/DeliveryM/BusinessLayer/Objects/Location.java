package DeliveryM.BusinessLayer.Objects;

public class Location {

	private int LocationId;
	private String address;
	private String contactNumber;
	private String contactName;
	private  String area;

	// Constructor
	public Location(int id, String address, String contactNumber, String contactName, String area) {
		this.LocationId = id;
		this.address = address;
		this.contactNumber = contactNumber;
		this.contactName = contactName;
		this.area = area;
	}

	public int getLocationId() {
		return LocationId;
	}

	public void setLocationId(int id) {
		this.LocationId = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
}
