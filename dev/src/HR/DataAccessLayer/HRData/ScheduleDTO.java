package HR.DataAccessLayer.HRData;

public class ScheduleDTO {
    private Integer ShiftID;
    private Integer EmployeeID;
    private Integer BranchID;
    private String role;

    public ScheduleDTO(Integer ShiftID, Integer EmployeeID, Integer BranchID, String role) {
        this.ShiftID = ShiftID;
        this.EmployeeID = EmployeeID;
        this.BranchID = BranchID;
        this.role = role;
    }

    public Integer getEmployeeID() {
        return EmployeeID;
    }

    public Integer getBranchID() {
        return BranchID;
    }

    public Integer getShiftID() {
        return ShiftID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setEmployeeID(Integer employeeID) {
        EmployeeID = employeeID;
    }

    public void setBranchID(Integer branchID) {
        BranchID = branchID;
    }

    public void setShiftID(Integer shiftID) {
        ShiftID = shiftID;
    }

}
