package HR.DomainLayer;

import HR.DomainLayer.EmployeePackage.Employee;
import HR.DomainLayer.EmployeePackage.EmployeeController;
import HR.DomainLayer.ShiftPackage.Shift;
import HR.DomainLayer.ShiftPackage.ShiftController;
import HR.LocalDateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;

public class PersonnelManager {
    private HashMap<Integer, Contract> Employees;
    private LinkedList<Integer> ShiftsHistory;
    private String name;
    private HashMap<Integer, LinkedList<Integer>> availableEmployees;
    private HashMap<Integer, HashMap<Integer,String>> schedule;

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
        updateShiftsHistory();
        Gson gson = new Gson();
        System.out.println(gson.toJson(ShiftsHistory));
        return ShiftsHistory;
    }

    public String getschedule(){
        Gson gson = new Gson();
        System.out.println(gson.toJson(schedule));
        return "";
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
            if(Employees.isEmpty() || !Employees.containsKey(employee.getEmployeeID())) {
                Employees.put(employee.getEmployeeID(),employee.getDealdetails());
            }
        }
        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateAdapter()).create();
        System.out.println(gson.toJson(Employees));
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

    public String buildShift(Integer shiftid , String role) throws Exception{
        HashMap<Integer,Employee> employees = EmployeeController.getInstance().getEmployees();
        for (Employee employee : employees.values()){
            if (employee.getWeeklyAvailableShifts().contains(shiftid) && employee.getRoles().contains(role)){
                if (!schedule.containsKey(shiftid)){
                    HashMap<Integer, String> employeesinShift = new HashMap<>();
                    employeesinShift.put(employee.getEmployeeID(), role);
                    schedule.put(shiftid , employeesinShift);
                    ShiftController.getInstance().addEmployee(shiftid,employee.getEmployeeID());
                }
                else{
                    if (!schedule.get(shiftid).containsKey(employee.getEmployeeID()))
                        schedule.get(shiftid).putIfAbsent(employee.getEmployeeID() , role);
                        ShiftController.getInstance().addEmployee(shiftid,employee.getEmployeeID());
                }
                break;
            }
        }
        return "";
    }

    public String scheduleShifts() throws Exception{
        HashMap<Integer,Shift> shifts = ShiftController.getInstance().getShifts();
        for (Shift shift : shifts.values()){
            buildShift(shift.getShiftId() , "cashier");
            buildShift(shift.getShiftId() , "storekeeper");
            buildShift(shift.getShiftId() , "Shift Manager");
            if (ShiftController.getInstance().getShift(shift.getShiftId()).getMinWorkers() > schedule.get(shift.getShiftId()).size()){
                throw new Exception("no enough employees");
            }
        }
        Gson gson = new Gson();
        System.out.println(gson.toJson(schedule));
        return "";
    }
}
