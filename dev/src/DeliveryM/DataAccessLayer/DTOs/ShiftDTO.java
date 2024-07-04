package DeliveryM.DataAccessLayer.DTOs;

public class ShiftDTO extends DataDTO {
    private int shiftId;
    private String startingTime;
    private String endingTime;
    private int driverId;
    private int storeKeeperId;

    public ShiftDTO(int shiftId, String startingTime, String endingTime, int driverId, int storeKeeperId) {
        this.shiftId = shiftId;
        this.startingTime = startingTime;
        this.endingTime = endingTime;
        this.driverId = driverId;
        this.storeKeeperId = storeKeeperId;
    }

    // Getters and Setters
    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(String startingTime) {
        this.startingTime = startingTime;
    }

    public String getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(String endingTime) {
        this.endingTime = endingTime;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public int getStoreKeeperId() {
        return storeKeeperId;
    }

    public void setStoreKeeperId(int storeKeeperId) {
        this.storeKeeperId = storeKeeperId;
    }


}
