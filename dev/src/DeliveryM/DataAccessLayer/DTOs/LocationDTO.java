package DeliveryM.DataAccessLayer.DTOs;

public class LocationDTO extends DataDTO {
    private int Id;
    private String address;
    private String contactNumber;

    private String contactName;
    private int deliveryId;
    private String area;


    public LocationDTO(int Id, String address, String contactNumber, String contactName, int deliveryId, String area) {
        this.Id = Id;
        this.address = address;
        this.contactNumber = contactNumber;
        this.contactName = contactName;
        this.deliveryId = deliveryId;
        this.area=area;
    }

    // Getters and Setters
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
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

    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }
    public String getArea() {
        return area;
    }
}
