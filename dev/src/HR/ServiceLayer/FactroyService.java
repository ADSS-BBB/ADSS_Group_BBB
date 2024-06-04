package HR.ServiceLayer;

import HR.DomainLayer.BankAccount;
import HR.DomainLayer.Contract;

public class FactroyService {
    private ShiftService shiftService;
    private EmployeeService employeeService;
    private BranchService branchService;

    public FactroyService() {
        this.shiftService = new ShiftService();
        this.employeeService = new EmployeeService();
        this.branchService = new BranchService();

    }

//branch service
    public String addbranch(Integer id, String location) throws Exception{
        return branchService.addBranch(id, location);
    }

    public String removebranch(Integer id) throws Exception{
        return branchService.removeBranch(id);
    }

    public String addemployee(Integer id, Integer branchid) throws Exception{
        return branchService.addEmployee(id, branchid);
    }

    public String removeemployee(Integer id, Integer branchid) throws Exception{
        return branchService.removeEmployee(id, branchid);
    }

    public String addRole(Integer id, String role) throws Exception{
        return branchService.addRole(id, role);
    }

    public String removeRole(Integer id, String role) throws Exception{
        return branchService.removeRole(id, role);
    }

    public String setShift1Hours(Integer branchid, Integer[] shift1Hours) throws Exception{
        return branchService.setShift1Hours(branchid, shift1Hours);
    }

    public String setShift2Hours(Integer branchid, Integer[] shift2Hours) throws Exception{
        return branchService.setShift2Hours(branchid, shift2Hours);
    }

    public String updateBranchShifts(Integer id) throws Exception{
        return branchService.updateBranchShift(id);
    }


//employee service
    public String getEmployee(Integer id) throws Exception{
        return employeeService.getEmployee(id);
    }

    public String addEmployee(Integer id, String name, Integer ContractId, Integer Salary , Integer Branchid ,String EmploymentType, String username ,String password,Integer balance ) throws Exception{
        Contract contract = new Contract(ContractId,Salary,Branchid,EmploymentType);
        BankAccount bankAccount = new BankAccount(username,password,balance);
        return employeeService.addEmployee(id, name, contract, bankAccount);
    }

    public String removeEmployee(Integer id) throws Exception{
        return employeeService.removeEmployee(id);
    }

    public String addEmployeeRole(Integer id, String role) throws Exception{
        return employeeService.addRole(id, role);
    }

    public String removeEmployeeRole(Integer id, String role) throws Exception{
        return employeeService.removeRole(id, role);
    }

    public String increaseSalary(Integer id, Integer salary) throws Exception{
        return employeeService.increaseSalary(id, salary);
    }

    public String decreaseSalary(Integer id, Integer salary) throws Exception{
        return employeeService.decreaseSalary(id, salary);
    }

    public String setBankAccount(String username, String password, Integer balance,  Integer id) throws Exception{
        BankAccount bankAccount = new BankAccount(username,password,balance);
        return employeeService.setBankAccount(bankAccount, id);
    }

    public String changeBranch(Integer id, Integer branchid) throws Exception{
        return employeeService.changeBranch(id, branchid);
    }

    public String addShiftToWeek(Integer shiftid, Integer id) throws Exception{
        return employeeService.addShiftToWeek(shiftid, id);
    }

    public String removeShiftFromWeek(Integer shiftid, Integer id) throws Exception{
        return employeeService.removeShifFromWeek(shiftid, id);
    }

    public String setEmploymentType(Integer id, String type) throws Exception{
        return employeeService.setEmploymentType(id, type);
    }

    public String updateHistory(Integer id) throws Exception{
        return employeeService.updateHistory(id);
    }

    public String schedulingShifts(Integer shiftid) throws Exception{
        return employeeService.schedulingShifts(shiftid);
    }

    public String showAvailableShifts(Integer id) throws Exception{
        return employeeService.showAvailableShifts(id);
    }


//shift service
    public String addShift(Integer id, Integer shiftmanagerid, Integer minworkers, String type, Integer branchid) throws Exception{
        return shiftService.addShift(id, shiftmanagerid, minworkers, type, branchid);
    }

    public String addEmployee(Integer shiftid, Integer workerid) throws Exception{
        return shiftService.addEmployee(shiftid, workerid);
    }

    public String removeEmployee(Integer shiftid, Integer workerid) throws Exception{
        return shiftService.removeEmployee(shiftid, workerid);
    }

    public String addCancellation(Integer transictionid, Integer shiftid) throws Exception{
        return shiftService.addCancellation(transictionid,shiftid);
    }

    public String setShiftMinWorker(Integer shiftid, Integer minworkers) throws Exception{
        return shiftService.setMinWorkers(shiftid, minworkers);
    }


}
