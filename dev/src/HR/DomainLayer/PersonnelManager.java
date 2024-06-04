package HR.DomainLayer;

import HR.DomainLayer.EmployeePackage.Employee;
import HR.DomainLayer.EmployeePackage.EmployeeController;
import HR.DomainLayer.ShiftPackage.Shift;
import HR.DomainLayer.ShiftPackage.ShiftController;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.LinkedList;

public class PersonnelManager {
    private HashMap<Integer, Contract> Employees;
    private LinkedList<Integer> ShiftsHistory;
    private String name;
    private HashMap<Integer, LinkedList<Integer>> availableEmployees;
    private HashMap<Integer, LinkedList<Integer>> schedule;

    public PersonnelManager(String name) {
        Employees = new HashMap<>();
        ShiftsHistory = new LinkedList<>();
        this.name = name;
        availableEmployees = new HashMap<>();
        schedule = new HashMap<>();
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


    public String showAvailableEmployees(){
        HashMap<Integer, Employee> employees = EmployeeController.getInstance().getEmployees();
        for (Employee employee : employees.values()){
            LinkedList<Integer> shifts = employee.getWeeklyAvailableShifts();
            for (Integer shiftid : shifts){
                if (availableEmployees.containsKey(employee.getEmployeeID()) && !availableEmployees.get(employee.getEmployeeID()).contains(shiftid)){
                    availableEmployees.get(employee.getEmployeeID()).add(shiftid);
                }
                else {
                    LinkedList<Integer> temp = new LinkedList<>();
                    temp.add(shiftid);
                    availableEmployees.put(employee.getEmployeeID(), temp);
                }
            }
        }
        Gson gson = new Gson();
        System.out.println(gson.toJson(availableEmployees));
        return "";
    }
}
