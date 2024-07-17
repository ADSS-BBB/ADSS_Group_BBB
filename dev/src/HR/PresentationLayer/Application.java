package HR.PresentationLayer;

import HR.DataAccessLayer.HRData.SuperLeeDataController;
import HR.DomainLayer.PersonnelManager;
import HR.ServiceLayer.FactroyService;
import HR.worker;

import java.time.LocalDate;
import java.util.Scanner;

public class Application {

    private FactroyService factroyService;
    private PersonnelManager manager;
    public Application(FactroyService factory , PersonnelManager manager){
        this.factroyService=factory;
        this.manager = manager;
    }

    public void MenuPage() throws Exception {
        Scanner sc = new Scanner(System.in);
        String input;
        while (true) {
            try {
                System.out.println("Welcome to SuperLee");
                System.out.println("1. HRManager Page");
                System.out.println("2. Employee Page");
                System.out.println("3. Exit");
                input = sc.nextLine();
                switch (Integer.parseInt(input)) {
                    case 1:
                        HRManagerPage();
                    case 2:
                        EmployeePage();
                    case 3:
                        return;
                    default:
                        System.out.println("Invalid input");
                }
            } catch (Exception e) {
                System.out.println("Invalid input");
            }
        }
    }

    public void HRManagerPage() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            System.out.println("1. Add Branch");
            System.out.println("2. Remove Branch");
            System.out.println("3. get Branch roles");
            System.out.println("4. Add Role To Branch");
            System.out.println("5. Remove Role From Branch");
            System.out.println("6. Update Branch Shift");
            System.out.println("7. Get Employee");
            System.out.println("8. Add Employee To System");
            System.out.println("9. Remove Employee From System");
            System.out.println("10. Add Employee Role");
            System.out.println("11. Remove Employee Role");
            System.out.println("12. Increase Salary");
            System.out.println("13. Decrease Salary");
            System.out.println("14. Change Branch");
            System.out.println("15. Set Employment Type");
            System.out.println("16. Show Available Shifts");
            System.out.println("17. build shift with role");
            System.out.println("18. remove employee from shift");
            System.out.println("19. show schedule");
            System.out.println("20. get Shifts history");
            System.out.println("21. Update Employee History");
            System.out.println("22. Add Shift To System");
            System.out.println("23. Set Shift Minimum Workers");
            System.out.println("24. Show Available Employees");
            System.out.println("25. Show all the employees in the market");
            System.out.println("26. Get Shift From System");
            System.out.println("27. go back to menu page");
            //System.out.println("28. Exit");
            try {
                input = scanner.nextLine();
                switch (Integer.parseInt(input)) {
                    case 1:
                        addBranch();
                        break;
                    case 2:
                        removeBranch();
                        break;
                    case 3:
                        getRoles();
                        break;
                    case 4:
                        addroleToBranch();
                        break;
                    case 5:
                        removeRoleFromBranch();
                        break;
                    case 6:
                        updateBranchShifts();
                        break;
                    case 7:
                        getEmployee();
                        break;
                    case 8:
                        addEmployee();
                        break;
                    case 9:
                        removeEmployee();
                        break;
                    case 10:
                        addEmployeeRole();
                        break;
                    case 11:
                        removeEmployeeRole();
                        break;
                    case 12:
                        IncreaseSalary();
                        break;
                    case 13:
                        decreaseSalary();
                        break;
                    case 14:
                        changeBranch();
                        break;
                    case 15:
                        setEmploymentType();
                        break;
                    case 16:
                        showAvailableShifts();
                        break;
                    case 17:
                        buildShift();
                        break;
                    case 18:
                        unbuildShift();
                        break;
                    case 19:
                        getSchedule();
                        break;
                    case 20:
                        HRgetShiftsHistory();
                        break;
                    case 21:
                        updateEmployeeHistory();
                        break;
                    case 22:
                        addShift();
                        break;
                    case 23:
                        setMinWorkers();
                        break;
                    case 24:
                        ShowAvailableEmployees();
                        break;
                    case 25:
                        HRgetEmployess();
                        break;
                    case 26:
                        getShift();
                        break;
                    case 27:
                        MenuPage();
                        break;
//                    case 28:
//                        System.exit(0);
//                        break;
                    default:
                        System.out.println("Invalid input");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Invalid input");
            }
        }
    }




    private void EmployeePage() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            System.out.println("1. Set Bank Account");
            System.out.println("2. Add Available Shift");
            System.out.println("3. Remove Available Shift");
            System.out.println("4. Update History");
            System.out.println("5. Add Cancellation");
            System.out.println("6. schedule");
            System.out.println("7. Get Shift From System");
            System.out.println("8. go back to menu page");
            System.out.println("9. Exit");

            try {
                input = scanner.nextLine();
                switch (Integer.parseInt(input)) {
                    case 1:
                        SetBankAccount();
                        break;
                    case 2:
                        AddAvailableShift();
                        break;
                    case 3:
                        RemoveAvailableShift();
                        break;
                    case 4:
                        UpdateEmployeeHistory();
                        break;
                    case 5:
                        AddCancellation();
                        break;
                    case 6:
                        getSchedule();
                        break;
                    case 7:
                        getShift();
                        break;
                    case 8:
                        MenuPage();
                        break;
                    case 9:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid input");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Invalid input");
            }
        }
    }

    private void HRgetShiftsHistory(){
        try {
            manager.getShiftsHistory();
        } catch (Exception e){
            System.out.println("failed while trying to get shifts history");
        }
    }

    private void getSchedule(){
        try {
            manager.getschedule();
        } catch (Exception e){
            System.out.println("failed while trying to get shifts history");
        }
    }

    private void SetBankAccount() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input;
        String input2;
        String input3;
        String input4;
        System.out.println("Enter UserName");
        input = scanner.nextLine();
        System.out.println("Enter Password");
        input2 = scanner.nextLine();
        System.out.println("Enter Bank Account Balance");
        input3 = scanner.nextLine();
        System.out.println("Enter Employee Id");
        input4 = scanner.nextLine();
        try {
            factroyService.setBankAccount(input, input2, Integer.parseInt(input3), Integer.parseInt(input4));
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }

//    private void HRscheduleShifts() throws Exception{
//        try {
//            manager.scheduleShifts();
//        } catch (Exception e){
//            System.out.println("failed while trying to schedule shifts");
//        }
//    }

    private void HRgetEmployess(){
        try {
            manager.checktheEmployees();
        } catch (Exception e){
            System.out.println("failed while trying to get employess");
        }
    }

    public void LoadData() throws Exception{
        try {
            SuperLeeDataController.getInstance().LoadData();
        } catch (Exception e){
            System.out.println("failed to load data");
        }
    }

    public void DeleteData() throws Exception{
        try {
            SuperLeeDataController.getInstance().deleteData();
        } catch (Exception e){
            System.out.println("failed to delete data");
        }
    }

    private void buildShift() throws Exception{
        Scanner scanner = new Scanner(System.in);
        String input;
        String input2;
        System.out.println("Enter Shift id");
        input = scanner.nextLine();
        System.out.println("Enter role");
        input2 = scanner.nextLine();
        try {
            manager.buildShift(Integer.parseInt(input), input2);
        } catch (Exception e) {
            System.out.println("invalid input");
        }
    }

    private void unbuildShift() throws Exception{
        Scanner scanner = new Scanner(System.in);
        String input;
        String input2;
        System.out.println("Enter Shift id");
        input = scanner.nextLine();
        System.out.println("Enter employee id");
        input2 = scanner.nextLine();
        try {
            manager.unbuildShift(Integer.parseInt(input), Integer.parseInt(input2));
        } catch (Exception e) {
            System.out.println("invalid input");
        }
    }


    public void getRoles() throws Exception{
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println("Enter branch id");
        input = scanner.nextLine();
        try {
            factroyService.getRoles(Integer.parseInt(input));
        } catch (Exception e) {
            System.out.println("invalid input");
        }
    }

    private void AddAvailableShift() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input;
        String input2;
        System.out.println("Enter Employee Id");
        input = scanner.nextLine();
        System.out.println("Enter Shift Id");
        input2 = scanner.nextLine();
        try {
            factroyService.addShiftToWeek(Integer.parseInt(input2), Integer.parseInt(input));
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }

    private void RemoveAvailableShift() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input;
        String input2;
        System.out.println("Enter Employee Id");
        input = scanner.nextLine();
        System.out.println("Enter Shift Id");
        input2 = scanner.nextLine();
        try {
            factroyService.removeShiftFromWeek(Integer.parseInt(input2), Integer.parseInt(input));
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }

    private void ShowAvailableEmployees() throws Exception {
        try {
            manager.showAvailableEmployees();
        }
        catch (Exception e){
            System.out.println("failed while trying to show the available empolyees");
        }
    }

    private void UpdateEmployeeHistory() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println("Enter Employee Id");
        input = scanner.nextLine();
        try {
            factroyService.updateHistory(Integer.parseInt(input));
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }

    private void AddCancellation() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input;
        String input2;
        System.out.println("Enter Transaction Id");
        input = scanner.nextLine();
        System.out.println("Enter Shift Id");
        input2 = scanner.nextLine();
        try {
            factroyService.addCancellation(Integer.parseInt(input), Integer.parseInt(input2));
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }




    private void addBranch() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input;
        String input2;
        System.out.println("Enter Branch Id");
        input = scanner.nextLine();
        System.out.println("Enter Location");
        input2 = scanner.nextLine();

        try {
            factroyService.addbranch(Integer.parseInt(input), input2);
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }

    private void removeBranch() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println("Enter Branch Id");
        input = scanner.nextLine();
        try {
            factroyService.removebranch(Integer.parseInt(input));
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }

//    private void setShift1Hours() throws Exception {
//        Scanner scanner = new Scanner(System.in);
//        String input;
//        String input2;
//        System.out.println("Enter Branch Id");
//        input = scanner.nextLine();
//        System.out.println("Enter Shift 1 Hours in <StartHour EndHour> in this form");
//        input2 = scanner.nextLine();
//        String[] hours = input2.split(" ");
//        if (hours.length != 2) throw new Exception("Invalid input");
//        try {
//            Integer[] hours1 = {Integer.parseInt(hours[0]), Integer.parseInt(hours[1])};
//            factroyService.setShift1Hours(Integer.parseInt(input), hours1);
//        } catch (Exception e) {
//            System.out.println("Invalid input");
//        }
//    }

//    private void setShift2Hours() throws Exception {
//        Scanner scanner = new Scanner(System.in);
//        String input;
//        String input2;
//        System.out.println("Enter Branch Id");
//        input = scanner.nextLine();
//        System.out.println("Enter Shift 2 Hours in <StartHour EndHour> in this form");
//        input2 = scanner.nextLine();
//        String[] hours = input2.split(" ");
//        if (hours.length != 2) throw new Exception("Invalid input");
//        try {
//            Integer[] hours1 = {Integer.parseInt(hours[0]), Integer.parseInt(hours[1])};
//            factroyService.setShift2Hours(Integer.parseInt(input), hours1);
//        } catch (Exception e) {
//            System.out.println("Invalid input");
//        }
//    }

//    private void addEmployeetoBranch() throws Exception {
//        Scanner scanner = new Scanner(System.in);
//        String input;
//        String input2;
//        System.out.println("Enter employee Id");
//        input = scanner.nextLine();
//        System.out.println("Enter branch Id");
//        input2 = scanner.nextLine();
//        try {
//            factroyService.addemployee(Integer.parseInt(input), Integer.parseInt(input2));
//        } catch (Exception e) {
//            System.out.println("Invalid input");
//        }
//    }

//    private void removeEmployeefromBranch() throws Exception {
//        Scanner scanner = new Scanner(System.in);
//        String input;
//        String input2;
//        System.out.println("Enter Employee Id");
//        input = scanner.nextLine();
//        System.out.println("Enter branch Id");
//        input2 = scanner.nextLine();
//        try {
//            factroyService.removeemployee(Integer.parseInt(input), Integer.parseInt(input2));
//        } catch (Exception e) {
//            System.out.println("Invalid input");
//        }
//    }

    private void addroleToBranch() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input;
        String input2;
        System.out.println("Enter Branch Id");
        input = scanner.nextLine();
        System.out.println("Enter Role");
        input2 = scanner.nextLine();
        try {
            factroyService.addRole(Integer.parseInt(input), input2);
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }

    private void removeRoleFromBranch() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input;
        String input2;
        System.out.println("Enter Branch Id");
        input = scanner.nextLine();
        System.out.println("Enter Role Id");
        input2 = scanner.nextLine();
        try {
            factroyService.removeRole(Integer.parseInt(input), input2);
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }

    private void updateBranchShifts() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println("Enter Branch Id");
        input = scanner.nextLine();
        try {
            factroyService.updateBranchShifts(Integer.parseInt(input));
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }

    private void getEmployee() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println("Enter Employee Id");
        input = scanner.nextLine();
        try {
            factroyService.getEmployee(Integer.parseInt(input));
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }

    private void getShift() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println("Enter Shift Id");
        input = scanner.nextLine();
        try {
            factroyService.getShift(Integer.parseInt(input));
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }

    private void addEmployee() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input;
        String input2;
        String input3;
        String input4;
        String input5;
        String input6;
        String input7;
        String input8;
        String input9;
        String input10;
        System.out.println("Enter Employee Id");
        input = scanner.nextLine();
        System.out.println("Enter Employee name");
        input2 = scanner.nextLine();
        System.out.println("Enter Contract Id");
        input3 = scanner.nextLine();
        System.out.println("Enter salary");
        input4 = scanner.nextLine();
        System.out.println("Enter Branch Id");
        input5 = scanner.nextLine();
        System.out.println("Enter Employment type : full or partial");
        input6 = scanner.nextLine();
        System.out.println("Enter Bank username");
        input7 = scanner.nextLine();
        System.out.println("Enter bank password");
        input8 = scanner.nextLine();
        System.out.println("bank balance");
        input9 = scanner.nextLine();
        System.out.println("Enter Startdate in YYYY-MM-DD format");
        input10 = scanner.nextLine();
        String[] date = input10.split("-");
        if ( date.length != 3){
            throw new Exception("invalid input");
        }
        LocalDate Date = LocalDate.of(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]));

        try {
            factroyService.addEmployee(Integer.parseInt(input), input2, Integer.parseInt(input3), Integer.parseInt(input4), Integer.parseInt(input5), input6, input7, input8, Integer.parseInt(input9),Date);
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }

    private void removeEmployee() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println("Enter Employee Id");
        input = scanner.nextLine();
        try {
            factroyService.removeEmployee(Integer.parseInt(input));
        } catch (Exception e) {
            System.out.println("invalid input");
        }
    }

    private void addEmployeeRole() throws Exception{
        Scanner scanner = new Scanner(System.in);
        String input;
        String input2;
        String input3=null;
        System.out.println("Enter Employee Id");
        input = scanner.nextLine();
        System.out.println("Enter Role");
        input2 = scanner.nextLine();
        if(input2.equals("driver")){
            System.out.println("Enter licenseType");
            input3=scanner.nextLine();

        }
        try {
            factroyService.addEmployeeRole(Integer.parseInt(input), input2,input3);
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }

    private void removeEmployeeRole() throws Exception{
        Scanner scanner = new Scanner(System.in);
        String input;
        String input2;
        System.out.println("Enter Employee Id");
        input = scanner.nextLine();
        System.out.println("Enter Role");
        input2 = scanner.nextLine();
        try {
            factroyService.removeEmployeeRole(Integer.parseInt(input), input2);
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }

    private void IncreaseSalary() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input;
        String input2;
        System.out.println("Enter Employee Id");
        input = scanner.nextLine();
        System.out.println("Enter Salary");
        input2 = scanner.nextLine();
        try {
            factroyService.increaseSalary(Integer.parseInt(input), Integer.parseInt(input2));
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }

    private void decreaseSalary() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input;
        String input2;
        System.out.println("Enter Employee Id");
        input = scanner.nextLine();
        System.out.println("Enter Salary");
        input2 = scanner.nextLine();
        try {
            factroyService.decreaseSalary(Integer.parseInt(input), Integer.parseInt(input2));
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }

    private void changeBranch() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input;
        String input2;
        System.out.println("Enter Employee Id");
        input = scanner.nextLine();
        System.out.println("Enter branch Id");
        input2 = scanner.nextLine();
        try {
            factroyService.changeBranch(Integer.parseInt(input), Integer.parseInt(input2));
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }

    private void setEmploymentType() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input;
        String input2;
        System.out.println("Enter Employmee Id");
        input = scanner.nextLine();
        System.out.println("Enter new Employment Type full or partial");
        input2 = scanner.nextLine();
        try {
            factroyService.setEmploymentType(Integer.parseInt(input), input2);
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }

    private void showAvailableShifts() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println("Enter Employee Id");
        input = scanner.nextLine();
        try {
            factroyService.showAvailableShifts(Integer.parseInt(input));
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }

    private void scheduleShifts() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println("Enter Employee Id");
        input = scanner.nextLine();
        try {
            factroyService.schedulingShifts(Integer.parseInt(input));
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }

    private void updateEmployeeHistory() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println("Enter Employee Id");
        input = scanner.nextLine();
        try {
            factroyService.updateHistory(Integer.parseInt(input));
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }


    private void addShift() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input;
        String input2;
        String input3;
        String input4;
        String input5;
        String input6;
        System.out.println("Enter Shift Id");
        input = scanner.nextLine();
        System.out.println("Enter date in YYYY-MM-DD format");
        input6 = scanner.nextLine();
        String[] date = input6.split("-");
        if ( date.length != 3){
            throw new Exception("invalid input");
        }
        LocalDate Date = LocalDate.of(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]));
        System.out.println("Enter Min workers");
        input3 = scanner.nextLine();
        System.out.println("Enter Shift type Morning or Evening");
        input4 = scanner.nextLine();
        System.out.println("Enter Branch Id");
        input5 = scanner.nextLine();
        try {
            factroyService.addShift(Integer.parseInt(input), Date,  Integer.parseInt(input3), input4, Integer.parseInt(input5));
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }

    private  void setMinWorkers() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input;
        String input2;
        System.out.println("Enter Shift Id");
        input = scanner.nextLine();
        System.out.println("Enter Min workers");
        input2 = scanner.nextLine();
        try {
            factroyService.setShiftMinWorker(Integer.parseInt(input), Integer.parseInt(input2));
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }


}
