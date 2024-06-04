package HR.DomainLayer.EmployeePackage;

import HR.DomainLayer.BankAccount;
import HR.DomainLayer.BranchPackage.Branch;
import HR.DomainLayer.BranchPackage.BranchController;
import HR.DomainLayer.Contract;
import HR.DomainLayer.ShiftPackage.Shift;
import HR.DomainLayer.ShiftPackage.ShiftController;

import java.util.HashMap;
import java.util.LinkedList;

public class EmployeeController {
    private HashMap<Integer, Employee> employees = new HashMap<>();
    private static EmployeeController instance;
    public static EmployeeController getInstance() {
        if (instance == null) {
            instance = new EmployeeController();
        }
        return instance;
    }

    public HashMap<Integer, Employee> getEmployees() {
        return employees;
    }


    public Employee getEmployee(Integer id) throws Exception{
        if (id == null || id < 0){
            throw new Exception("Employee ID is null or empty");
        }
        if (employees.containsKey(id)){
            return employees.get(id);
        }
        throw new Exception("Employee is not existed");
    }

    public String addRole(String role, Integer id) throws Exception{
        if (id == null || id < 0){
            throw new Exception("Employee id is null");
        }
        if (!employees.containsKey(id)){
            throw new Exception("Employee is not existed");
        }
        return employees.get(id).addRole(role);
    }

    public String removeRole(String role, Integer id) throws Exception{
        if (id == null || id < 0){
            throw new Exception("Employee id is null");
        }
        if (!employees.containsKey(id)){
            throw new Exception("Employee is not existed");
        }
        return employees.get(id).removeRole(role);
    }

    public String increaseSalary(Integer id, Integer salary) throws Exception{
        if (id == null || id < 0){
            throw new Exception("Employee id is null");
        }
        if (!employees.containsKey(id)){
            throw new Exception("Employee is not existed");
        }
        return employees.get(id).IncreaseSalary(salary);
    }

    public String decreaseSalary(Integer id, Integer salary) throws Exception{
        if (id == null || id < 0){
            throw new Exception("Employee id is null");
        }
        if (!employees.containsKey(id)){
            throw new Exception("Employee is not existed");
        }
        return employees.get(id).DecreaseSalary(salary);
    }

    public String setBankAccount(BankAccount bankAccount, Integer id) throws Exception{
        if (id == null || id < 0){
            throw new Exception("Employee id is null");
        }
        if (!employees.containsKey(id)){
            throw new Exception("Employee is not existed");
        }
        return employees.get(id).setBankAccount(bankAccount);
    }

    public String changeBranch(Integer id, Integer branchid) throws Exception{
        if (id == null || id < 0){
            throw new Exception("Employee id is null");
        }
        if (!employees.containsKey(id)){
            throw new Exception("Employee is not existed");
        }
        return employees.get(id).changeBranch(branchid);
    }

    public String addShiftToWeek(Integer shiftid, Integer id) throws Exception{
        if (id == null || id < 0){
            throw new Exception("Employee id is null");
        }
        if (!employees.containsKey(id)){
            throw new Exception("Employee is not existed");
        }
        return employees.get(id).addShiftToWeek(shiftid);
    }

    public String removeShiftFromWeek(Integer shiftid, Integer id) throws Exception{
        if (id == null || id < 0){
            throw new Exception("Employee id is null");
        }
        if (!employees.containsKey(id)){
            throw new Exception("Employee is not existed");
        }
        return employees.get(id).removeShiftFromWeek(shiftid);
    }

    public String setEmploymentType(Integer id, String type) throws Exception{
        if (id == null || id < 0){
            throw new Exception("Employee id is null");
        }
        if (!employees.containsKey(id)){
            throw new Exception("Employee is not existed");
        }
        return employees.get(id).setEmploymentType(type);
    }

    public LinkedList<Integer> updateHistory(Integer id) throws Exception{
        if (id == null || id < 0){
            throw new Exception("Employee id is null");
        }
        if (!employees.containsKey(id)){
            throw new Exception("Employee is not existed");
        }
        employees.get(id).updatehistory();
        return employees.get(id).getShiftsHistory();
    }

    public LinkedList<Employee> schedulingShifts(Integer shiftId) throws Exception{
        LinkedList<Employee> shiftEmployees = new LinkedList<>();
        if (shiftId == null || shiftId < 0){
            throw new Exception("Employee id is null");
        }
        for (Employee employee : employees.values()){
            if (employee.getWeeklyAvailableShifts().contains(shiftId)){
                if (!shiftEmployees.contains(employee)){
                    shiftEmployees.add(employee);
                    ShiftController.getInstance().getShift(shiftId).AddEmployee(employee.getEmployeeID());
                    employee.updatehistory();
                }

            }
        }
        if (shiftEmployees.isEmpty() || shiftEmployees.size() < ShiftController.getInstance().getShift(shiftId).getMinWorkers()){
            throw new Exception("There is no enough worker to schedule shifts" + shiftId);
        }
        return shiftEmployees;
    }


    public String addEmployee(Integer id, String username, Contract contract , BankAccount bankAccount) throws Exception{
        if (id == null || id < 0){
            throw new Exception("Employee id is null");
        }
        if (employees.containsKey(id)){
            throw new Exception("Employee is existed");
        }
        if (username == null || username.equals("")){
            throw new Exception("Username is null");
        }
        if (contract == null || contract.isEmpty()){
            throw new Exception("Contract is null");
        }
        if (bankAccount == null || bankAccount.isEmpty()){
            throw new Exception("BankAccount is null");
        }
        if (contract.getBranchId() == null || contract.getBranchId()<0){
            throw new Exception("Branchid is null");
        }
        if (!BranchController.getInstance().getBranches().containsKey(contract.getBranchId())){
            throw new Exception("no such branch");
        }
        employees.put(id, new Employee(id, username, contract, bankAccount));
        return "Employee added successfully";

    }

    public String removeEmployee(Integer id) throws Exception{
        if (id == null || id < 0){
            throw new Exception("Employee id is null");
        }
        if (!employees.containsKey(id)){
            throw new Exception("Employee is not existed");
        }
        employees.remove(id);
        HashMap<Integer, Branch> branches = BranchController.getInstance().getBranches();
        for (Branch branch : branches.values()){
            branch.RemoveBranchEmployee(id);
        }
        return "Employee removed successfully";
    }

    public LinkedList<Integer> showAvailableShifts(Integer id) throws Exception{
        if (id == null || id < 0){
            throw new Exception("Employee id is null");
        }
        if (!employees.containsKey(id)){
            throw new Exception("Employee is not existed");
        }
        return employees.get(id).getWeeklyAvailableShifts();
    }
}
