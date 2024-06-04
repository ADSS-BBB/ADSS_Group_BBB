package HR;

import HR.ServiceLayer.FactroyService;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static FactroyService factroyService = new FactroyService();

    public static void main(String[] args) {
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
                        //case 2: EmployeePage();
                    case 3:
                        System.exit(0);
                    default:
                        System.out.println("Invalid input");
                }
            } catch (Exception e) {
                System.out.println("Invalid input");
            }
        }
    }

    public static void HRManagerPage() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            System.out.println("1. Add Branch");
            System.out.println("2. Remove Branch");
            System.out.println("3. Edit Shift 1 Hours");
            System.out.println("4. Edit Shift 2 Hours");
            System.out.println("5. Add Employee To Branch");
            System.out.println("6. Remove Employee From Branch");
            System.out.println("7. Add Role To Branch");
            System.out.println("8. Remove Role From Branch");
            System.out.println("9. Update Branch Shift");
            System.out.println("10. Get Employee");
            System.out.println("11. Add Employee To System");
            System.out.println("12. Remove Employee From System");
            System.out.println("13. Add Employee Role");
            System.out.println("14. Remove Employee Role");
            System.out.println("15. Increase Salary");
            System.out.println("16. Decrease Salary");
            System.out.println("17. Change Branch");
            System.out.println("18. Set Employment Type");
            System.out.println("19. Show Available Shifts");
            System.out.println("20. Scheduling Shifts");
            System.out.println("21. Update Employee History");
            System.out.println("22. Add Shift To System");
            System.out.println("23. Add Employee To Shift");
            System.out.println("24. Remove Employee From Shift");
            System.out.println("25. Set Shift Minimum Workers");
            System.out.println("26. Exit");
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
                        setShift1Hours();
                        break;
                    case 4:
                        setShift2Hours();
                        break;
                    case 5:
                        addEmployeetoBranch();
                        break;
                    case 6:
                        removeEmployeefromBranch();
                        break;
                    case 7:
                        addroleToBranch();
                        break;
                    case 8:
                        removeRoleFromBranch();
                        break;
                    case 9:
                        updateBranchShifts();
                        break;
                    case 10:
                        getEmployee();
                        break;
                    case 11:
                        addEmployee();
                        break;
                    case 12:
                        removeEmployee();
                        break;
                    case 13:
                        addEmployeeRole();
                        break;
                    case 14:
                        removeEmployeeRole();
                        break;
                    case 15:
                        IncreaseSalary();
                        break;
                    case 16:
                        decreaseSalary();
                        break;
                    case 17:
                        changeBranch();
                        break;
                    case 18:
                        setEmploymentType();
                        break;
                    case 19:
                        showAvailableShifts();
                        break;
                    case 20:
                        scheduleShifts();
                        break;
                    case 21:
                        updateEmployeeHistory();
                        break;
                    case 22:
                        addShift();
                        break;
                    case 23:
                        AddEmployeeToShift();
                        break;
                    case 24:
                        removeEmployeeFromShift();
                        break;
                    case 25:
                        setMinWorkers();
                        break;
                    case 26:
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

    private static void EmployeePage() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            System.out.println("1. Set Bank Account");
            System.out.println("2. Add Available Shift");
            System.out.println("3. Remove Available Shift");
            System.out.println("4. Update History");
            System.out.println("5. Add Cancellation");
            System.out.println("6. Exit");

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

    private static void SetBankAccount() throws Exception {
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

    private static void AddAvailableShift() throws Exception {
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

    private static void RemoveAvailableShift() throws Exception {
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

    private static void UpdateEmployeeHistory() throws Exception {
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

    private static void AddCancellation() throws Exception {
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




    private static void addBranch() throws Exception {
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

    private static void removeBranch() throws Exception {
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

    private static void setShift1Hours() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input;
        String input2;
        System.out.println("Enter Branch Id");
        input = scanner.nextLine();
        System.out.println("Enter Shift 1 Hours in <StartHour EndHour> in this form");
        input2 = scanner.nextLine();
        String[] hours = input2.split(" ");
        if (hours.length != 2) throw new Exception("Invalid input");
        try {
            Integer[] hours1 = {Integer.parseInt(hours[0]), Integer.parseInt(hours[1])};
            factroyService.setShift1Hours(Integer.parseInt(input), hours1);
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }

    private static void setShift2Hours() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input;
        String input2;
        System.out.println("Enter Branch Id");
        input = scanner.nextLine();
        System.out.println("Enter Shift 2 Hours in <StartHour EndHour> in this form");
        input2 = scanner.nextLine();
        String[] hours = input2.split(" ");
        if (hours.length != 2) throw new Exception("Invalid input");
        try {
            Integer[] hours1 = {Integer.parseInt(hours[0]), Integer.parseInt(hours[1])};
            factroyService.setShift2Hours(Integer.parseInt(input), hours1);
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }

    private static void addEmployeetoBranch() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input;
        String input2;
        System.out.println("Enter employee Id");
        input = scanner.nextLine();
        System.out.println("Enter branch Id");
        input2 = scanner.nextLine();
        try {
            factroyService.addemployee(Integer.parseInt(input), Integer.parseInt(input2));
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }

    private static void removeEmployeefromBranch() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input;
        String input2;
        System.out.println("Enter Employee Id");
        input = scanner.nextLine();
        System.out.println("Enter branch Id");
        input2 = scanner.nextLine();
        try {
            factroyService.removeemployee(Integer.parseInt(input), Integer.parseInt(input2));
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }

    private static void addroleToBranch() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input;
        String input2;
        System.out.println("Enter Branch Id");
        input = scanner.nextLine();
        System.out.println("Enter Role Id");
        input2 = scanner.nextLine();
        try {
            factroyService.addRole(Integer.parseInt(input), input2);
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }

    private static void removeRoleFromBranch() throws Exception {
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

    private static void updateBranchShifts() throws Exception {
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

    private static void getEmployee() throws Exception {
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

    private static void addEmployee() throws Exception {
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
        try {
            factroyService.addEmployee(Integer.parseInt(input), input2, Integer.parseInt(input3), Integer.parseInt(input4), Integer.parseInt(input5), input6, input7, input8, Integer.parseInt(input9));
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }

    private static void removeEmployee() throws Exception {
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

    private static void addEmployeeRole() throws Exception{
        Scanner scanner = new Scanner(System.in);
        String input;
        String input2;
        System.out.println("Enter Employee Id");
        input = scanner.nextLine();
        System.out.println("Enter Role");
        input2 = scanner.nextLine();
        try {
            factroyService.addEmployeeRole(Integer.parseInt(input), input2);
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }

    private static void removeEmployeeRole() throws Exception{
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

    private static void IncreaseSalary() throws Exception {
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

    private static void decreaseSalary() throws Exception {
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

    private static void changeBranch() throws Exception {
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

    private static void setEmploymentType() throws Exception {
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

    private static void showAvailableShifts() throws Exception {
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

    private static void scheduleShifts() throws Exception {
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

    private static void updateEmployeeHistory() throws Exception {
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


    private static void addShift() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input;
        String input2;
        String input3;
        String input4;
        String input5;
        System.out.println("Enter Shift Id");
        input = scanner.nextLine();
        System.out.println("Enter Shift manager Id");
        input2 = scanner.nextLine();
        System.out.println("Enter Min workers");
        input3 = scanner.nextLine();
        System.out.println("Enter Shift type Morning or Evening");
        input4 = scanner.nextLine();
        System.out.println("Enter Branch Id");
        input5 = scanner.nextLine();
        try {
            factroyService.addShift(Integer.parseInt(input), Integer.parseInt(input2), Integer.parseInt(input3), input4, Integer.parseInt(input5));
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }

    private static void AddEmployeeToShift() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input;
        String input2;
        System.out.println("Enter Shift Id");
        input = scanner.nextLine();
        System.out.println("Enter Employee Id");
        input2 = scanner.nextLine();
        try {
            factroyService.addEmployee(Integer.parseInt(input),Integer.parseInt(input2));
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }

    private static void removeEmployeeFromShift() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input;
        String input2;
        System.out.println("Enter Shift Id");
        input = scanner.nextLine();
        System.out.println("Enter Employee Id");
        input2 = scanner.nextLine();
        try {
            factroyService.removeEmployee(Integer.parseInt(input), Integer.parseInt(input2));
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }

    private static void setMinWorkers() throws Exception {
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