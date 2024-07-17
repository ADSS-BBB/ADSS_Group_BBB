//package HR.BackendTests;
//
//import HR.DomainLayer.BankAccount;
//import HR.DomainLayer.BranchPackage.Branch;
//import HR.DomainLayer.Contract;
//import HR.DomainLayer.EmployeePackage.Employee;
//import HR.DomainLayer.ShiftPackage.Shift;
//import org.junit.Test;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//public class BranchTests {
//    Branch branch;
//    Branch branch2;
//    Employee employee;
//    BankAccount bankAccount;
//    Contract contract;
//    Shift shift;
//
//    @BeforeEach
//    public void init() throws Exception{
//        branch = new Branch("Beer Sheva",1 );
//        branch2 = new Branch("Tel Aviv",2 );
//        bankAccount = new BankAccount("essa","Essa1234",7500);
//        contract = new Contract(1 , 15000, 1 , "full" , LocalDate.of(2023,6,6));
//        employee = new Employee(1 , "essa", contract , bankAccount);
//        employee.addRole("Shift Manager");
//        shift = new Shift(1, LocalDate.of(2024,6,19),  2 , "Morning" ,1);
//    }
//
//    @AfterEach
//    public void cleanup(){
//        branch = null;
//        branch2 = null;
//        employee = null;
//        bankAccount = null;
//        contract = null;
//        shift = null;
//
//    }
//
//    @Test
//    public void AddRoleTest() throws Exception {
//        init();
//        boolean ans = true;
//        try {
//            branch.AddRole("cleaner");
//        } catch (Exception e){
//            ans = false;
//        }
//        assertTrue(ans);
//    }
//
//    @Test
//    public void AddRoleTest2() throws Exception {
//        init();
//        boolean ans = true;
//        try {
//            branch.AddRole("");
//        } catch (Exception e){
//            ans = false;
//        }
//        assertFalse(ans);
//    }
//
//    @Test
//    public void RemoveRoleTest() throws Exception {
//        init();
//        boolean ans = true;
//        try {
//            branch.AddRole("cleaner");
//            branch.RemoveRole("cleaner");
//        } catch (Exception e){
//            ans = false;
//        }
//        assertTrue(ans);
//    }
//
//    @Test
//    public void RemoveRoleTest2() throws Exception {
//        init();
//        boolean ans = true;
//        try {
//            branch.RemoveRole("cleaner");
//        } catch (Exception e){
//            ans = false;
//        }
//        assertFalse(ans);
//    }
//
////    @Test
//////    public void setShift1HoursTest() throws Exception {
//////        init();
//////        boolean ans = true;
//////        Integer[] hours1 = {14 , 22};
//////        try {
//////            branch.setShift1Hours(hours1);
//////        } catch (Exception e){
//////            ans = false;
//////        }
//////        assertTrue(ans);
//////    }
//
////    @Test
////    public void setShift1HoursTest2() throws Exception {
////        init();
////        boolean ans = true;
////        Integer[] hours1 = {14 , 21};
////        try {
////            branch.setShift1Hours(hours1);
////        } catch (Exception e){
////            ans = false;
////        }
////        assertFalse(ans);
////    }
//
//    @Test
//    public void AddBranchEmployeeTest() throws Exception{
//        init();
//        boolean ans = true;
//        try {
//            branch.AddBranchEmployee(2);
//        } catch (Exception e){
//            ans = false;
//        }
//        assertTrue(ans);
//    }
//
//    @Test
//    public void AddBranchEmployeeTest2() throws Exception{
//        init();
//        boolean ans = true;
//        try {
//            branch.AddBranchEmployee(1);
//        } catch (Exception e){
//            ans = false;
//        }
//        assertFalse(ans);
//    }
//
//    @Test
//    public void RemoveBranchEmployeeTest() throws Exception{
//        init();
//        boolean ans = true;
//        try {
//            branch.RemoveBranchEmployee(2);
//        } catch (Exception e){
//            ans = false;
//        }
//        assertFalse(ans);
//    }
//
//    @Test
//    public void RemoveBranchEmployeeTest2() throws Exception{
//        init();
//        boolean ans = true;
//        try {
//            branch.RemoveBranchEmployee(1);
//        } catch (Exception e){
//            ans = false;
//        }
//        assertTrue(ans);
//    }
//
//    @Test
//    public void updateBranchShiftTest() throws Exception{
//        init();
//        boolean ans = true;
//        try {
//            branch.updateBranchShift();
//            ans = branch.getBranchShiftList().contains(shift.getShiftId());
//        } catch (Exception e){
//            ans = false;
//        }
//        assertTrue(ans);
//    }
//
//
//}
//
