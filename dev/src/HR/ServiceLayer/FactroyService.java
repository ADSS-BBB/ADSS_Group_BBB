package HR.ServiceLayer;

import HR.DomainLayer.BankAccount;
import HR.DomainLayer.Contract;

import java.time.LocalDate;

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
        System.out.println(branchService.addBranch(id, location));
        return "";
    }

    public String getRoles(Integer id) throws Exception{
        System.out.println(branchService.getRoles(id));
        return "";
    }

    public String removebranch(Integer id) throws Exception{
        System.out.println(branchService.removeBranch(id));
        return "";
    }

    public String addemployee(Integer id, Integer branchid) throws Exception{
        System.out.println(branchService.addEmployee(id, branchid));
        return "";
    }

    public String removeemployee(Integer id, Integer branchid) throws Exception{
        System.out.println(branchService.removeEmployee(id, branchid));
        return "";
    }

    public String addRole(Integer id, String role) throws Exception{
        System.out.println(branchService.addRole(id, role));
        return "";
    }

    public String removeRole(Integer id, String role) throws Exception{
        System.out.println(branchService.removeRole(id, role));
        return "";
    }


    public String updateBranchShifts(Integer id) throws Exception{
        System.out.println(branchService.updateBranchShift(id));
        return "";
    }


//employee service
    public String getEmployee(Integer id) throws Exception{
        System.out.println(employeeService.getEmployee(id));
        return "";
    }

    public String addEmployee(Integer id, String name, Integer ContractId, Integer Salary , Integer Branchid ,String EmploymentType, String username ,String password,Integer balance, LocalDate startdate ) throws Exception{
        Contract contract = new Contract(ContractId,Salary,Branchid,EmploymentType,startdate);
        BankAccount bankAccount = new BankAccount(username,password,balance);
        System.out.println(employeeService.addEmployee(id, name, contract, bankAccount));
        return "";
    }

    public String removeEmployee(Integer id) throws Exception{
        System.out.println(employeeService.removeEmployee(id));
        return "";
    }

    public String addEmployeeRole(Integer id, String role) throws Exception{
        System.out.println(employeeService.addRole(id, role));
        return "";
    }

    public String removeEmployeeRole(Integer id, String role) throws Exception{
        System.out.println(employeeService.removeRole(id, role));
        return "";
    }

    public String increaseSalary(Integer id, Integer salary) throws Exception{
        System.out.println(employeeService.increaseSalary(id, salary));
        return "";
    }

    public String decreaseSalary(Integer id, Integer salary) throws Exception{
        System.out.println(employeeService.decreaseSalary(id, salary));
        return "";
    }

    public String setBankAccount(String username, String password, Integer balance,  Integer id) throws Exception{
        BankAccount bankAccount = new BankAccount(username,password,balance);
        System.out.println(employeeService.setBankAccount(bankAccount, id));
        return "";
    }

    public String changeBranch(Integer id, Integer branchid) throws Exception{
        System.out.println(employeeService.changeBranch(id, branchid));
        return "";
    }

    public String addShiftToWeek(Integer shiftid, Integer id) throws Exception{
        System.out.println(employeeService.addShiftToWeek(shiftid, id));
        return "";
    }

    public String removeShiftFromWeek(Integer shiftid, Integer id) throws Exception{
        System.out.println(employeeService.removeShifFromWeek(shiftid, id));
        return "";
    }

    public String setEmploymentType(Integer id, String type) throws Exception{
        System.out.println(employeeService.setEmploymentType(id, type));
        return "";
    }

    public String updateHistory(Integer id) throws Exception{
        System.out.println(employeeService.updateHistory(id));
        return "";
    }

    public String schedulingShifts(Integer shiftid) throws Exception{
        System.out.println(employeeService.schedulingShifts(shiftid));
        return "";
    }

    public String showAvailableShifts(Integer id) throws Exception{
        System.out.println(employeeService.showAvailableShifts(id));
        return "";
    }


//shift service
    public String addShift(Integer id, LocalDate time, Integer minworkers, String type, Integer branchid) throws Exception{
        System.out.println(shiftService.addShift(id, time, minworkers, type, branchid));
        return "";
    }

    public String addEmployee(Integer shiftid, Integer workerid) throws Exception{
        System.out.println(shiftService.addEmployee(shiftid, workerid));
        return "";
    }

    public String removeEmployee(Integer shiftid, Integer workerid) throws Exception{
        System.out.println(shiftService.removeEmployee(shiftid, workerid));
        return "";
    }

    public String addCancellation(Integer transictionid, Integer shiftid) throws Exception{
        System.out.println(shiftService.addCancellation(transictionid,shiftid));
        return "";
    }

    public String setShiftMinWorker(Integer shiftid, Integer minworkers) throws Exception{
        System.out.println(shiftService.setMinWorkers(shiftid, minworkers));
        return "";
    }

    public String getShift(Integer shiftid) throws Exception{
        System.out.println(shiftService.getShift(shiftid));
        return "";
    }


}
