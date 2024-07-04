package HR.ServiceLayer;

import HR.DomainLayer.BankAccount;
import HR.DomainLayer.Contract;
import HR.DomainLayer.EmployeePackage.Employee;
import HR.DomainLayer.EmployeePackage.EmployeeController;
import HR.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDate;
import java.util.LinkedList;

public class EmployeeService {
    EmployeeController employeeController;

    public EmployeeService() throws Exception {
        employeeController = EmployeeController.getInstance();
    }

    public String addEmployee(Integer id, String username, Contract contract, BankAccount bankAccount) throws Exception {
        try {
            String result = employeeController.addEmployee(id, username, contract, bankAccount);
            return result;
        }
        catch (Exception e) {
            return "failed while trying to add employee";
        }
    }

    public String getEmployee(Integer employeeId) throws Exception{
        try {
            Employee result = employeeController.getEmployee(employeeId);
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
            String json = gson.toJson(result);
            return json;
        } catch (Exception e) {
            return "failed while trying to retrieve employee";
        }
    }

    public String removeEmployee(Integer id) throws Exception {
        try {
            String result = employeeController.removeEmployee(id);
            return result;
        }
        catch (Exception e) {
            return "failed while trying to remove employee";
        }

    }

    public String addRole(Integer id, String role) throws Exception {
        try{
            String result = employeeController.addRole(role, id);
            return result;
        }
        catch (Exception e) {
            return "failed while trying to add role";
        }
    }

    public String removeRole(Integer id, String role) throws Exception {
        try{
            String result = employeeController.removeRole(role, id);
            return result;
        }
        catch (Exception e) {
            return "failed while trying to remove role";
        }
    }

    public String increaseSalary(Integer id, Integer salary) throws Exception {
        try {
            String result = employeeController.increaseSalary(id, salary);
            return result;
        }
        catch (Exception e) {
            return "failed while trying to increase salary";
        }
    }

    public String decreaseSalary(Integer id, Integer salary) throws Exception {
        try {
            String result = employeeController.decreaseSalary(id, salary);
            return result;
        }
        catch (Exception e) {
            return "failed while trying to decrease salary";
        }
    }

    public String setBankAccount(BankAccount bankAccount, Integer id) throws Exception{
        try {
            String result = employeeController.setBankAccount(bankAccount, id);
            return result;
        }
        catch (Exception e) {
            return "failed while trying set the bank account";
        }
    }

    public String changeBranch(Integer id, Integer branchid) throws Exception{
        try {
            String result = employeeController.changeBranch(id, branchid);
            return result;
        }
        catch (Exception e) {
            return "failed while trying change branch";
        }
    }

    public String addShiftToWeek(Integer shiftid, Integer id) throws Exception{
        try {
            String result = employeeController.addShiftToWeek(shiftid, id);
            return result;
        }
        catch (Exception e) {
            return "failed while trying to add shift to week";
        }
    }

    public String removeShifFromWeek(Integer shiftid, Integer id) throws Exception{
        try {
            String result = employeeController.removeShiftFromWeek(shiftid, id);
            return result;
        }
        catch (Exception e) {
            return "failed while trying to remove shift from week";
        }
    }

    public String setEmploymentType(Integer id, String type) throws Exception{
        try {
            String result = employeeController.setEmploymentType(id, type);
            return result;
        }
        catch (Exception e) {
            return "failed while trying to set employement type";
        }
    }

    public String updateHistory(Integer id) throws Exception{
        try {
            LinkedList<Integer> result = employeeController.updateHistory(id);
            Gson gson = new Gson();
            String json = gson.toJson(result);
            return json;
        }
        catch (Exception e) {
            return "failed while trying to update history";
        }
    }

    public String schedulingShifts(Integer shiftid) throws Exception{
        try{
            LinkedList<Employee> result = employeeController.schedulingShifts(shiftid);
            Gson gson = new Gson();
            String json = gson.toJson(result);
            return json;
        }
        catch (Exception e) {
            return "failed while trying to schedule shifts";
        }
    }

    public String showAvailableShifts(Integer id) throws Exception{
        try {
            LinkedList<Integer> result = employeeController.showAvailableShifts(id);
            Gson gson = new Gson();
            String json = gson.toJson(result);
            return json;
        }
        catch (Exception e) {
            return "failed while trying to show the available shifts";
        }
    }
}
