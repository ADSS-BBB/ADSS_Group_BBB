package HR.DomainLayer;

import HR.DomainLayer.EmployeePackage.Employee;
import HR.DomainLayer.EmployeePackage.EmployeeController;
import HR.DomainLayer.ShiftPackage.Shift;
import HR.DomainLayer.ShiftPackage.ShiftController;

import java.util.HashMap;
import java.util.LinkedList;

public class PersonnelManager {
    private HashMap<Integer, Contract> Employees;
    private LinkedList<Integer> ShiftsHistory;
    private String name;

    public PersonnelManager(String name) {
        Employees = new HashMap<>();
        ShiftsHistory = new LinkedList<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public HashMap<Integer, Contract> getEmployees() {
        return Employees;
    }

    public LinkedList<Integer> getShiftsHistory() {
        return ShiftsHistory;
    }

    public String updateShiftsHistory() {
        HashMap<Integer, Shift> shifts = ShiftController.getInstance().getShifts();
        for (Integer Id : shifts.keySet()) {
            if(!ShiftsHistory.contains(Id)) {
                ShiftsHistory.add(Id);
            }
        }
        return "shifts history updated";
    }

    public String checktheEmployees() {
        HashMap<Integer, Employee> employees = EmployeeController.getInstance().getEmployees();
        for (Employee employee : employees.values()) {
            if(!Employees.containsKey(employee.getEmployeeID())) {
                Employees.put(employee.getEmployeeID(),employee.getDealdetails());
            }
        }
        return "employees updated";
    }



}
