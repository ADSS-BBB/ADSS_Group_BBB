package HR.BackendTests;

import HR.DomainLayer.BankAccount;
import HR.DomainLayer.BranchPackage.Branch;
import HR.DomainLayer.Contract;
import HR.DomainLayer.EmployeePackage.Employee;
import HR.DomainLayer.ShiftPackage.Shift;
import HR.DomainLayer.ShiftPackage.ShiftController;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;


public class ShiftControllerTests {
    ShiftController shiftController;
    Branch branch;
    Shift shift;
    Employee employee1;
    Employee employee2;
    BankAccount bankAccount;
    Contract contract;
    BankAccount bankAccount2;
    Contract contract2;
    @BeforeEach
    public void init() throws Exception{
        shiftController = ShiftController.getInstance();
        contract = new Contract(1 , 10000,1,"full");
        contract2 = new Contract(3 , 10000,1,"full");
        bankAccount = new BankAccount("Atheel", "Atheel12",4000);
        bankAccount2 = new BankAccount("essa", "Essa1234",5000);
        branch = new Branch("beer sheva", 1);
        shift = new Shift(1, 2, 2, "full", 1);
        employee1 = new Employee(1, "Atheel", contract, bankAccount);
        employee2 = new Employee(3, "Essa", contract2, bankAccount2);

    }

    @Test
    public void addShift() throws Exception{
        init();
        boolean ans = true;
        try {
            employee1.addRole("Shift Manager");
            shiftController.addShift(2, 1, 2, "Morning", 1);
        }
        catch (Exception e){
            ans = false;
        }
        assertTrue(ans);
    }

    @Test
    public void addShift2() throws Exception{
        init();
        boolean ans = true;
        try {
            employee1.addRole("Shift Manager");
            shiftController.addShift(1, 1, 2, "Morning", 1);
        }
        catch (Exception e){
            ans = false;
        }
        assertFalse(ans);
    }
}
