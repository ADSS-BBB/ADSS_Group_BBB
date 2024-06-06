package HR.BackendTests;

import HR.DomainLayer.BankAccount;
import HR.DomainLayer.BranchPackage.Branch;
import HR.DomainLayer.Contract;
import HR.DomainLayer.EmployeePackage.Employee;
import HR.DomainLayer.EmployeePackage.EmployeeController;
import HR.DomainLayer.ShiftPackage.Shift;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ShiftTests {
    Shift shift;
    Employee employee1;
    Employee employee2;
    EmployeeController employeeController;

    @BeforeEach
    public void init() throws Exception{
        Contract contract = new Contract(1 , 10000,1,"full",LocalDate.of(2024,2,23));
        Contract contract2 = new Contract(3 , 10000,1,"full",LocalDate.of(2024,3,23));
        BankAccount bankAccount = new BankAccount("Atheel", "Atheel12",4000);
        BankAccount bankAccount2 = new BankAccount("essa", "Essa1234",5000);
        Branch branch1 = new Branch("tel aviv", 1);
        employee1 = new Employee(1, "Atheel", contract, bankAccount);
        employee2 = new Employee(3, "Essa", contract, bankAccount);
        shift = new Shift(1, LocalDate.of(2022,1,12),2, 2, "full", 1);
        employeeController = EmployeeController.getInstance();
    }

    @Test
    public void addEmployeeTest() throws Exception{
        init();
        boolean ans = true;
        try {
            employee1.addShiftToWeek(1);
            shift.AddEmployee(employee1.getEmployeeID());
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
            shift.AddEmployee(employee1.getEmployeeID());
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
            employee1.addShiftToWeek(1);
            employee2.addShiftToWeek(1);
            employeeController.schedulingShifts(1);
            shift.RemoveEmployee(employee1.getEmployeeID());
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
            employee1.addShiftToWeek(1);
            employeeController.schedulingShifts(1);
            shift.RemoveEmployee(employee1.getEmployeeID());
        }
        catch (Exception e){
            ans = false;
        }
        assertFalse(ans);
    }

    @Test
    public void addCancellation() throws Exception{
        init();
        boolean ans = true;
        try {
            shift.AddCancelation(1);
        }
        catch (Exception e){
            ans = false;
        }
        assertTrue(ans);
    }

    @Test
    public void addCancellation2() throws Exception{
        init();
        boolean ans = true;
        try {
            shift.AddCancelation(1);
            shift.AddCancelation(1);
        }
        catch (Exception e){
            ans = false;
        }
        assertFalse(ans);
    }
}
