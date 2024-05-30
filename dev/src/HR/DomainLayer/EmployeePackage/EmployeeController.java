package HR.DomainLayer.EmployeePackage;

import java.util.HashMap;

public class EmployeeController {
    private HashMap<Integer, Employee> employees;
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
        if (employees.containsKey(id)){
            return employees.get(id);
        }
        throw new Exception("Employee is not existed");
    }


}
