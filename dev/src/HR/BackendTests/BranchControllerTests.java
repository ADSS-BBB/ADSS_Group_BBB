package HR.BackendTests;

import HR.DomainLayer.BankAccount;
import HR.DomainLayer.BranchPackage.Branch;
import HR.DomainLayer.BranchPackage.BranchController;
import HR.DomainLayer.Contract;
import HR.DomainLayer.EmployeePackage.Employee;
import HR.DomainLayer.ShiftPackage.Shift;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class BranchControllerTests {
   BranchController branchController;
   Branch branch;
   Employee employee;
   BankAccount bankAccount;
   Contract contract;
   Shift shift;

   @BeforeEach
    public void init() throws Exception{
       branchController = BranchController.getInstance();
       branch = new Branch("Beer Sheva" , 1);
       bankAccount = new BankAccount("essa","Essa1234",8500);
       contract = new Contract(1 , 12000 , 1 , "full", LocalDate.of(2024,6,5));
       employee = new Employee(1,"essa" , contract ,bankAccount);
       employee.addRole("Shift Manager");
       shift = new Shift(1, LocalDate.of(2024,12,4),1,2,"Morning",1);
   }

   @AfterEach
   public void cleanup(){
       BranchController.setInstancetonull(branchController);
       branch = null;
       bankAccount = null;
       contract = null;
       shift = null;
       employee = null;
   }

    @Test
    public void addBranchTest() throws Exception{
       init();
       boolean ans = true;
       try {
           branchController.addBranch(2, "tel aviv");
       } catch (Exception e){
           ans = false;
       }
       assertTrue(ans);
   }

    @Test
    public void addBranchTest2() throws Exception{
        init();
        boolean ans = true;
        try {
            branchController.addBranch(1, "Beer Sheva");
        } catch (Exception e){
            ans = false;
        }
        assertFalse(ans);
    }

    @Test
    public void removeBranchTest() throws Exception{
        init();
        boolean ans = true;
        try {
            branchController.removeBranch(1);
        } catch (Exception e){
            ans = false;
        }
        assertTrue(ans);
    }

    @Test
    public void removeBranchTest2() throws Exception{
        init();
        boolean ans = true;
        try {
            branchController.removeBranch(2);
        } catch (Exception e){
            ans = false;
        }
        assertFalse(ans);
    }



}
