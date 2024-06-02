package HR.ServiceLayer;


import HR.DomainLayer.EmployeePackage.EmployeeController;

public class EmployeeService {
    EmployeeController employeeController;

    public EmployeeService(){
        employeeController=EmployeeController.getInstance();
    }



}
