package HR.DomainLayer.BranchPackage;
import HR.DomainLayer.EmployeePackage.Employee;
import HR.DomainLayer.EmployeePackage.EmployeeController;
import HR.DomainLayer.ShiftPackage.Shift;
import HR.DomainLayer.ShiftPackage.ShiftController;

import java.util.HashMap;
import java.util.LinkedList;

public class Branch {
    private String Location;
    private Integer BranchId;
    private Integer[] Shift1Hours;
    private Integer[] Shift2Hours;
    private LinkedList<String> roles;
    private LinkedList<Integer> BranchEmployees;
    private LinkedList<Integer> BranchShiftList;

    public Branch(String Location, Integer BranchId) {
        this.Location = Location;
        this.BranchId = BranchId;
        this.Shift1Hours = new Integer[]{7 , 15};
        this.Shift2Hours = new Integer[]{15 , 23};
        roles = new LinkedList<>();
        BranchEmployees = new LinkedList<>();
        BranchShiftList = new LinkedList<>();
        BranchController.getInstance().getBranches().put(BranchId,this);
    }

    public LinkedList getBranchEmployees() {
        return BranchEmployees;
    }
    public Branch getBranch(){
        return this;
    }
    public String getLocation() {
        return Location;
    }
    public Integer getBranchId() {
        return BranchId;
    }
    public Integer[] getShift1Hours() {
        return Shift1Hours;
    }
    public Integer[] getShift2Hours() {
        return Shift2Hours;
    }
    public LinkedList<String> getRoles() {
        return roles;
    }
    public void setLocation(String Location) {
        this.Location = Location;
    }
    public void setBranchId(Integer BranchId) {
        this.BranchId = BranchId;
    }
    public LinkedList<Integer> getBranchShiftList() {
        return BranchShiftList;
    }


    public String setShift1Hours(Integer[] Shift1Hours) throws Exception {
        if (Shift1Hours == null || Shift1Hours.length != 2) {
            throw new Exception("Number of Shift1Hours must be equal to 2");
        }
        if (Shift1Hours[0] > Shift1Hours[1]) {
            throw new Exception("Shift1Hours[0] must be less than Shift1Hours[1]");
        }
        if (Shift1Hours[1] - Shift1Hours[0] != 8) {
            throw new Exception("Shift must be exactly 8 hours");
        }
        this.Shift1Hours=Shift1Hours;
        return "Shift1Hours set Successfully";

    }
    public String setShift2Hours(Integer[] Shift2Hours) throws Exception {
        if (Shift2Hours.length != 2) {
            throw new Exception("Number of Shift2Hours must be equal to 2");
        }
        if (Shift2Hours[0] > Shift2Hours[1]) {
            throw new Exception("Shift2Hours[0] must be less than Shift2Hours[1]");
        }
        if (Shift2Hours[1] - Shift2Hours[0] != 8) {
            throw new Exception("Shift must be exactly 8 hours");
        }
        this.Shift2Hours=Shift2Hours;
        return "Shift2Hours set Successfully";
    }


    public String AddRole(String Role) throws Exception{
        if (Role == null || Role.isEmpty()){
            throw new Exception("Role is null");
        }
        if(roles.contains(Role)){
            throw new Exception("Role already exists");
        }
        roles.add(Role);
        return "Role added successfully";
    }
    public String RemoveRole(String Role) throws Exception{
        if (Role == null || Role.isEmpty()){
            throw new Exception("Role is null");
        }
        if(!roles.contains(Role)){
            throw new Exception("Role does not exist");
        }
        roles.remove(Role);
        return "Role removed successfully";
    }

    public String AddBranchEmployee(Integer EmployeeId) throws Exception{
        if (EmployeeId == null || EmployeeId < 0) {
            throw new Exception("EmployeeId is null");
        }
        if (EmployeeController.getInstance().getEmployees().containsKey(EmployeeId)) {
            throw new Exception("Employee already taken");
        }
        if(BranchEmployees.contains(EmployeeId)){
            throw new Exception("Employee already exists");
        }
        BranchEmployees.add(EmployeeId);
        return "Employee added successfully";
    }

    public String RemoveBranchEmployee(Integer EmployeeId) throws Exception{
        if (EmployeeId == null || EmployeeId < 0) {
            throw new Exception("EmployeeId is null");
        }
        if (!EmployeeController.getInstance().getEmployees().containsKey(EmployeeId)) {
            throw new Exception("Invalid EmployeeId");
        }
        if(!BranchEmployees.contains(EmployeeId)){
            throw new Exception("Employee does not exist");
        }
        BranchEmployees.remove(EmployeeId);
        return "Employee removed successfully";
    }

    public String updateBranchShift() throws Exception{
        HashMap<Integer, Shift> shifts = ShiftController.getInstance().getShifts();
        for (Shift shift : shifts.values()){
            if (!BranchShiftList.contains(shift.getShiftId())){
                BranchShiftList.add(shift.getShiftId());
            }
        }
        return "Branch Shifts history updated successfully";
    }


}
