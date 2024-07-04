package DeliveryM.DataAccessLayer.DTOs;

public class StorekeeperDTO extends DataDTO {
    private int storeKeeperId;
    private String workAddress;

    public StorekeeperDTO(int storeKeeperId, String workAddress) {
        this.storeKeeperId = storeKeeperId;
        this.workAddress = workAddress;
    }

    // Getters and Setters
    public int getStoreKeeperId() {
        return storeKeeperId;
    }

    public void setStoreKeeperId(int storeKeeperId) {
        this.storeKeeperId = storeKeeperId;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    // Optional: Override toString() for debugging and logging purposes
    @Override
    public String toString() {
        return "StorekeeperDTO{" +
                "storeKeeperId=" + storeKeeperId +
                ", workAddress='" + workAddress + '\'' +
                '}';
    }
}
