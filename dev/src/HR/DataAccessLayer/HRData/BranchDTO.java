package HR.DataAccessLayer.HRData;

import HR.DomainLayer.BranchPackage.Branch;

public class BranchDTO {
    private Integer branchID;
    private String location;


    public BranchDTO(Integer branchID, String location) {
        this.branchID = branchID;
        this.location = location;
    }

    public Integer getBranchID() {
        return branchID;
    }

    public String getLocation() {
        return location;
    }


    public void setBranchID(Integer branchID) {
        this.branchID = branchID;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Branch DTO2Object(){
        return new Branch(location, branchID);
    }
}
