package HR.DataAccessLayer.HRData;

import HR.DomainLayer.EmployeePackage.EmployeeController;

import java.util.LinkedList;

public class RolesDTO {
    private Integer EmployeeID;
    private Integer BranchId;
    private String role;

    public RolesDTO(Integer EmployeeID, Integer BranchID, String role) {
        this.EmployeeID = EmployeeID;
        this.BranchId = BranchID;
        this.role = role;
    }

    public Integer getEmployeeID() {
        return EmployeeID;
    }

    public Integer getBranchId() {
        return BranchId;
    }

    public String getRole() {
        return role;
    }

    public void setEmployeeID(Integer employeeID) {
        EmployeeID = employeeID;
    }

    public void setBranchId(Integer branchId) {
        BranchId = branchId;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
