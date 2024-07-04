package DeliveryM.DataAccessLayer.DTOs;

public class LocItemDocDTO extends DataDTO {
    private int deliveryId;
    private int documentationId;
    private String locationId;
    private int currentTruckWeight;
    private int qid; // Corrected variable name to follow conventions
    private int driverId;

    public LocItemDocDTO(int deliveryId, int documentationId, String locationId, int currentTruckWeight, int qid,int driverId) {
        this.deliveryId = deliveryId;
        this.documentationId = documentationId;
        this.locationId = locationId;
        this.currentTruckWeight = currentTruckWeight;
        this.qid = qid;
        this.driverId=driverId;

    }

    // Getters and Setters
    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int deliveryId) {
        this.deliveryId = deliveryId;
    }

    public int getDocumentationId() {
        return documentationId;
    }

    public void setDocumentationId(int documentationId) {
        this.documentationId = documentationId;
    }

    public String getLocationId() {
        return locationId;
    }


    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public int getCurrentTruckWeight() {
        return currentTruckWeight;
    }

    public void setCurrentTruckWeight(int currentTruckWeight) {
        this.currentTruckWeight = currentTruckWeight;
    }

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

}
