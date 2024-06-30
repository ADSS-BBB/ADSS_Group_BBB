package HR.DataAccessLayer.HRData;

public class ShiftsHistoryDTO {
    private Integer EmployeeID;
    private Integer BranchID;
    private Integer ShiftID;

    public ShiftsHistoryDTO(Integer employeeID, Integer BranchID, Integer ShiftID){
        this.EmployeeID = employeeID;
        this.BranchID = BranchID;
        this.ShiftID = ShiftID;
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
