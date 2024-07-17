package DeliveryM.DataAccessLayer.DTOs;

public class DeliveryDTO extends DataDTO {
    private int id;
    private final String idColumn = "id";
    private String exitTime;
    private final String exitTimeColumn = "exitTime";
    private String arrivalTime;
    private final String arrivalTimeColumn = "arrivalTime";
    private int truckId;
    private final String truckIdColumn = "truckId";
    private int driverId;
    private final String driverIdColumn = "driverId";
    private int sourceId;
    private final String sourceIdColumn = "sourceId";

    public DeliveryDTO(int id, String exitTime, String arrivalTime, int truckId, int driverId, int sourceId) {
        this.id = id;
        this.exitTime = exitTime;
        this.arrivalTime = arrivalTime;
        this.truckId = truckId;
        this.driverId = driverId;
        this.sourceId = sourceId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExitTime() {
        return exitTime;
    }

    public void setExitTime(String exitTime) {
        this.exitTime = exitTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getTruckId() {
        return truckId;
    }

    public void setTruckId(int truckId) {
        this.truckId = truckId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }
}
