package DeliveryM.BusinessLayer.Objects;

import java.util.HashMap;

public class StoreKeeper {

    private int storeKeeperId;
    private String workAddress;
    private Shift shift;

    public StoreKeeper(int id, String workAddress, Shift shift) {
        this.storeKeeperId = id;
        this.workAddress = workAddress;
        this.shift = shift;
    }

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

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }
}
