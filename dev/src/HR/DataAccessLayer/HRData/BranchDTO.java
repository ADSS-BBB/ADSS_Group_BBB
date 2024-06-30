package HR.DataAccessLayer.HRData;

import HR.DomainLayer.BranchPackage.Branch;

import java.util.LinkedList;

public class BranchDTO {
    private Integer branchID;
    private String location;
    private String role;

    public BranchDTO(Integer branchID, String location, String role) {
        this.branchID = branchID;
        this.location = location;
        this.role = role;
    }

    public Integer getBranchID() {
        return branchID;
    }

    public String getLocation() {
        return location;
    }

    public String getRole() {
        return role;
    }

    public void setBranchID(Integer branchID) {
        this.branchID = branchID;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Branch DTO2Object(){
        return new Branch(location, branchID);
    }
}
