package HR.DomainLayer.EmployeePackage;

import HR.DomainLayer.BankAccount;
import HR.DomainLayer.BranchPackage.Branch;
import HR.DomainLayer.BranchPackage.BranchController;
import HR.DomainLayer.Contract;
import HR.DomainLayer.ShiftPackage.Shift;
import HR.DomainLayer.ShiftPackage.ShiftController;

import java.util.HashMap;
import java.util.LinkedList;

public class Employee {
    private Integer EmployeeID;
    private String Username;
    private Contract Dealdetails;
    private BankAccount BankAccount;
    private LinkedList<Integer> WeeklyAvailableShifts;
    private LinkedList<Integer> ShiftsHistory;
    private LinkedList<String> roles;
    private Integer BranchId;


    public Employee(Integer EmployeeID , String Username, Contract Dealdetails, BankAccount BankAccount, Integer BranchId) {
        this.EmployeeID = EmployeeID;
        this.Username = Username;
        this.Dealdetails = Dealdetails;
        this.BankAccount = BankAccount;
        WeeklyAvailableShifts = new LinkedList<>();
        ShiftsHistory = new LinkedList<>();
        roles = new LinkedList<>();
        this.BranchId = BranchId;
        EmployeeController.getInstance().getEmployees().put(EmployeeID,this);

    }

    public String getUsername() {
        return Username;
    }

    public BankAccount getBankAccount() {
        return BankAccount;
    }

    public LinkedList<String> getRoles() {
        return roles;
    }

    public Integer getEmployeeID() {
        return EmployeeID;
    }

    public Contract getDealdetails() {
        return Dealdetails;
    }

    public Integer getBranchId() {
        return BranchId;
    }

    public LinkedList<Integer> getWeeklyAvailableShifts() {
        return WeeklyAvailableShifts;
    }

    public LinkedList<Integer> getShiftsHistory() {
        return ShiftsHistory;
    }


    public String addRole(String role) throws Exception{
        if (role == null || role.isEmpty()){
            throw new Exception("role can not be null");
        }
        if (roles.contains(role)){
            throw new Exception("the employee already has this role");
        }
        roles.add(role);
        return "the role added successfully";
    }

    public String removeRole(String role) throws Exception{
        if (role == null || role.isEmpty()){
            throw new Exception("role can not be null or empty");
        }
        if (!roles.contains(role)){
            throw new Exception("role is not existed");
        }
        roles.remove(role);
        return "the role deleted successfully";
    }


    public String IncreaseSalary(Integer salary) throws Exception{
        if (salary < Dealdetails.getSalary()){
            throw new Exception("current salary is higher than the new salary");
        }
        this.Dealdetails.setSalary(salary);
        return "salary increased successfully";
    }

    public String DecreaseSalary(Integer salary) throws Exception{
        if (salary > Dealdetails.getSalary()){
            throw new Exception("current salary is lower than the new salary");
        }
        this.Dealdetails.setSalary(salary);
        return "salary decreased successfully";
    }

    public String setBankAccount(BankAccount bankAccount) throws Exception{
        if (bankAccount == null){
            throw new Exception("null bank account");
        }
        if (bankAccount.getBalance() == null){
            throw new Exception("null balance");
        }
        if (bankAccount.getPassword() == null){
            throw new Exception("null password");
        }
        if (bankAccount.getUsername() == null){
            throw new Exception("null username");
        }
        if (bankAccount.getUsername().equals(this.BankAccount.getUsername())){
            throw new Exception("same bank account");
        }
        this.BankAccount = bankAccount;
        return "bankaccount changed successfully";
    }

    public String changeBranch(Integer branch2) throws Exception{
        Branch currbranch = BranchController.getInstance().getBranch(branch2);
        if (branch2 == null || branch2 < 0){
            throw new Exception("Invalid id");
        }
        try {
            if (!BranchController.getInstance().getBranches().containsKey(branch2)){
                throw new Exception("no such branch");
            }
            Branch newBranch = BranchController.getInstance().getBranch(branch2);
            if (currbranch.equals(newBranch)){
                throw new Exception("already in this branch");
            }
        } catch (Exception e){
            System.out.println("failed while trying to get the branch");
        }
        this.BranchId = branch2;
        Dealdetails.setBranchId(branch2);
        return "branch changed successfully";
    }

    public String addShiftToWeek(Integer ShiftId) throws Exception{
        if (ShiftId == null){
            throw new Exception("null input");
        }
        if (ShiftId < 0){
            throw new Exception("Invalid shiftId");
        }
        if (!ShiftController.getInstance().getShifts().containsKey(ShiftId)){
            throw new Exception("no such shift");
        }
        WeeklyAvailableShifts.add(ShiftId);
        return "employee added an available day";
    }

    public String removeShiftFromWeek(Integer ShiftId) throws Exception{
        if (ShiftId == null){
            throw new Exception("null shiftId to remove");
        }
        if (ShiftId < 0){
            throw new Exception("Invalid ShiftId to remove");
        }
        if (!WeeklyAvailableShifts.contains(ShiftId)){
            throw new Exception("employee can not remove availabilty from a shift he is not available for");
        }
        WeeklyAvailableShifts.remove(ShiftId);
        return "employee removed availability";
    }

    public String setEmploymentType(String newType) throws Exception{
        Dealdetails.setEmploymentType(newType);
        return "employment type changed successfully";
    }

    public String updatehistory() throws Exception{
        HashMap<Integer, Shift> shifts = ShiftController.getInstance().getShifts();
        for (int i = 0 ; i < shifts.size() ; i++){
            Shift currshift = shifts.get(i);
            if (currshift.getEmployees().contains(this.EmployeeID)){
                ShiftsHistory.add(currshift.getShiftId());
            }
        }
        return "updated the history";
    }

}
