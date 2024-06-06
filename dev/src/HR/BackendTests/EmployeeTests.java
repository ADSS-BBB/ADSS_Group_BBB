package HR.BackendTests;

import HR.DomainLayer.BankAccount;
import HR.DomainLayer.BranchPackage.Branch;
import HR.DomainLayer.Contract;
import HR.DomainLayer.EmployeePackage.Employee;
import HR.DomainLayer.ShiftPackage.Shift;
import org.junit.Test;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.LinkedList;


public class EmployeeTests {
    Employee employee;

    @BeforeEach
    public void init() throws Exception{
        Contract contract = new Contract(1 , 10000,1,"full",LocalDate.of(2023,12,20));
        BankAccount bankAccount = new BankAccount("essa", "Essa1234",5000);
        Branch branch1 = new Branch("tel aviv", 1);
        employee = new Employee(1 , "essa" , contract , bankAccount);
        Shift shift = new Shift(1 , LocalDate.of(2024,11,6), 1 , 10 , "Morning" , 1);
        Branch branch2 = new Branch("beer sheva", 2);
        LinkedList<String> roles = new LinkedList<>();
        employee.getRoles().add("cashier");
        employee.addShiftToWeek(1);
    }

    @AfterEach
    public void clean(){
        employee = null;
    }

    @Test
    public void addRoleTest() throws Exception {
        init();
        boolean ans = true;
        try {
            employee.addRole("storekeeper");
        } catch (Exception e) {
            ans = false;
        }
        assertTrue(ans);
    }

    @Test
    public void addRoleTest2() throws Exception {
        init();
        boolean ans = true;
        try {
            employee.addRole("cashier");
        } catch (Exception e) {
            ans = false;
        }
        assertFalse(ans);
    }

    @Test
    public void removeRoleTest() throws Exception {
        init();
        boolean ans = true;
        try {
            employee.removeRole("storekeeper");
        } catch (Exception e) {
            ans = false;
        }
        assertFalse(ans);
    }

    @Test
    public void removeRoleTest2() throws Exception {
        init();
        boolean ans = true;
        try {
            employee.removeRole("cashier");
        } catch (Exception e) {
            ans = false;
        }
        assertTrue(ans);
    }

    @Test
    public void increaseSalaryTest() throws Exception {
        init();
        boolean ans = true;
        try {
            employee.IncreaseSalary(20000);
        } catch (Exception e) {
            ans = false;
        }
        assertTrue(ans);
    }

    @Test
    public void increaseSalaryTest2() throws Exception {
        init();
        boolean ans = true;
        try {
            employee.IncreaseSalary(3000);
        } catch (Exception e) {
            ans = false;
        }
        assertFalse(ans);
    }

    @Test
    public void decreaseSalaryTest() throws Exception {
        init();
        boolean ans = true;
        try {
            employee.DecreaseSalary(3000);
        } catch (Exception e) {
            ans = false;
        }
        assertTrue(ans);
    }

    @Test
    public void decreaseSalaryTest2() throws Exception {
        init();
        boolean ans = true;
        try {
            employee.DecreaseSalary(20000);
        } catch (Exception e) {
            ans = false;
        }
        assertFalse(ans);
    }

    @Test
    public void setBankAccountTest() throws Exception {
        init();
        boolean ans = true;
        try {
            employee.setBankAccount(new BankAccount("atheel", "athel123", 6000));
        } catch (Exception e) {
            ans = false;
        }
        assertTrue(ans);
    }

    @Test
    public void setBankAccountTest2() throws Exception {
        init();
        boolean ans = true;
        try {
            employee.setBankAccount(new BankAccount("essa", "athel000", 6000));
        } catch (Exception e) {
            ans = false;
        }
        assertFalse(ans);
    }

    @Test
    public void changeBranchTest() throws Exception {
        init();
        boolean ans = true;
        try {
            employee.changeBranch(2);
        } catch (Exception e) {
            ans = false;
        }
        assertTrue(ans);
    }

    @Test
    public void changeBranchTest2() throws Exception {
        init();
        boolean ans = true;
        try {
            employee.changeBranch(3);
        } catch (Exception e) {
            ans = false;
        }
        assertFalse(ans);
    }

    @Test
    public void addShiftToWeekTest() throws Exception {
        init();
        boolean ans = true;
        try {
            employee.addShiftToWeek(1);
        } catch (Exception e) {
            ans = false;
        }
        assertTrue(ans);
    }

    

    @Test
    public void removeShiftFromWeekTest() throws Exception {
        init();
        boolean ans = true;
        try {
            employee.removeShiftFromWeek(1);
        } catch (Exception e) {
            ans = false;
        }
        assertTrue(ans);
    }

    @Test
    public void removeShiftFromWeekTest2() throws Exception {
        init();
        boolean ans = true;
        try {
            employee.removeShiftFromWeek(2);
        } catch (Exception e) {
            ans = false;
        }
        assertFalse(ans);
    }


    @Test
    public void setEmploymentTypeTest() throws Exception {
        init();
        boolean ans = true;
        try {
            employee.setEmploymentType("partial");
        } catch (Exception e) {
            ans = false;
        }
        assertTrue(ans);
    }

    @Test
    public void setEmploymentTypeTest2() throws Exception {
        init();
        boolean ans = true;
        try {
            employee.setEmploymentType("night");
        } catch (Exception e) {
            ans = false;
        }
        assertFalse(ans);
    }





}
