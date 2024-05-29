package DomainLayer.EmployeePackage;

import java.util.HashMap;

public class EmployeeController {
    private HashMap<String, Employee> employees;
    private static EmployeeController instance;
    public static EmployeeController getInstance() {
        if (instance == null) {
            instance = new EmployeeController();
        }
        return instance;
    }

}
