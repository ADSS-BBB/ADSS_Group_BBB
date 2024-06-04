package HR.BackendTests;

import HR.DomainLayer.BankAccount;
import HR.DomainLayer.BranchPackage.Branch;
import HR.DomainLayer.Contract;
import HR.DomainLayer.EmployeePackage.Employee;
import HR.DomainLayer.EmployeePackage.EmployeeController;
import HR.DomainLayer.ShiftPackage.Shift;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeControllerTests {
    Employee employee;
    EmployeeController employeeController;
    Employee employee2;



    @BeforeEach
    public void init() throws Exception{
        Branch branch = new Branch("beer sheva", 1);
        Contract contract = new Contract(1, 10000, 1, "full");
        BankAccount bankAccount = new BankAccount("Atheel", "atheel12", 7000);
        Shift shift = new Shift(1, 2, 2, "Morning", 1);
        Contract contract2 = new Contract(2, 5000, 1, "full");
        BankAccount bankAccount2 = new BankAccount("essa", "essa12", 9000);
        employee = new Employee(1, "Atheel", contract, bankAccount);
        employee2 = new Employee(3, "essa", contract2, bankAccount2);
        employeeController = EmployeeController.getInstance();

    }



    @Test
    public void addEmployeeTest() throws Exception{
        init();
        boolean ans = true;
        try {
            Contract contract2 = new Contract(2, 5000, 1, "full");
            BankAccount bankAccount2 = new BankAccount("essa", "essa12", 9000);
            employeeController.addEmployee(2, "essa", contract2, bankAccount2);
        }
        catch (Exception e){
            ans = false;
        }
        assertTrue(ans);
    }

    @Test
    public void addEmployeeTest2() throws Exception{
        init();
        boolean ans = true;
        try {
            Contract contract2 = new Contract(1, 5000, 1, "full");
            BankAccount bankAccount2 = new BankAccount("essa", "essa12", 9000);
            employeeController.addEmployee(1, "essa", contract2, bankAccount2);
        }
        catch (Exception e){
            ans = false;
        }
        assertFalse(ans);
    }

    @Test
    public void removeEmployeeTest() throws Exception{
        init();
        boolean ans = true;
        try {
            employeeController.removeEmployee(1);
        }
        catch (Exception e){
            ans = false;
        }
        assertTrue(ans);
    }


    @Test
    public void removeEmployeeTest2() throws Exception{
        init();
        boolean ans = true;
        try {
            employeeController.removeEmployee(2);
        }
        catch (Exception e){
            ans = false;
        }
        assertFalse(ans);
    }

    @Test
    public void showAvailableShiftsTest() throws Exception{
        init();
        boolean ans = true;
        try {
            employeeController.addShiftToWeek(1, 1);
            employeeController.showAvailableShifts(1);
        }
        catch (Exception e){
            ans = false;
        }
        assertTrue(ans);
    }

    @Test
    public void showAvailableShiftsTest2() throws Exception{
        init();
        boolean ans = true;
        try {
            employeeController.addShiftToWeek(1, 1);
            employeeController.showAvailableShifts(2);
        }
        catch (Exception e){
            ans = false;
        }
        assertFalse(ans);
    }

    @Test
    public void schedulingShiftsTest() throws Exception{
        init();
        boolean ans = true;
        try {
            employeeController.addShiftToWeek(1, 3);
            employeeController.addShiftToWeek(1, 1);
            employeeController.schedulingShifts(1);
        }
        catch (Exception e){
            ans = false;
        }
        assertTrue(ans);
    }

    @Test
    public void schedulingShiftsTest2() throws Exception{
        init();
        boolean ans = true;
        try {
            employeeController.addShiftToWeek(1, 1);
            employeeController.schedulingShifts(1);
        }
        catch (Exception e){
            ans = false;
        }
        assertFalse(ans);
    }

}
