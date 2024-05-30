package HR.DomainLayer.BranchPackage;

import java.util.LinkedList;

public class Branch {
    private String Location;
    private Integer BranchId;
    private Integer[] shift1hours;
    private Integer[] shift2hours;
    private LinkedList<String> roles;

    public Branch(String Location, Integer BranchId) {
        this.Location = Location;
        this.BranchId = BranchId;
        roles = new LinkedList<>();
        shift1hours = new Integer[]{7, 15};
        shift2hours = new Integer[]{15, 23};
    }

    public Integer getBranchId() {
        return BranchId;
    }

    public String getLocation() {
        return Location;
    }

    public LinkedList<String> getRoles() {
        return roles;
    }

    public Integer[] getShift1hours() {
        return shift1hours;
    }

    public Integer[] getShift2hours() {
        return shift2hours;
    }
}
