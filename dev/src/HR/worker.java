package HR;

import HR.DataAccessLayer.HRData.SuperLeeDataController;
import HR.DomainLayer.PersonnelManager;
import HR.PresentationLayer.Application;
import HR.ServiceLayer.FactroyService;
import HR.ServiceLayer.ShiftService;

import java.time.LocalDate;
import java.util.Scanner;

public class worker {
    public static FactroyService factroyService;

    static {
        try {
            factroyService = new FactroyService();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static PersonnelManager manager;

    static {
        try {
            manager = new PersonnelManager("Firas");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Application application = new Application(factroyService, manager);

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input;
            System.out.println("Welcome to SuperLee");
            System.out.println("1. With Data");
            System.out.println("2. Without Data");
            System.out.println("3. Load Data");
            System.out.println("4. Delete Data");
            System.out.println("5. Back to menu");
            input = sc.nextLine();
            switch (Integer.parseInt(input)) {
                case 1:
                    SuperLeeDataController.getInstance().insertpersonnelmanager(manager.getName());
                    factroyService.addbranch(1, "Beer sheva");
                    factroyService.addbranch(2, "Tel Aviv");
                    factroyService.addEmployee(1, "essa", 1, 12000, 1, "full", "essa", "essa12", 15000, LocalDate.of(2023, 8, 2));
                    factroyService.addEmployee(2, "athel", 2, 12000, 1, "full", "athel", "athel12", 12000, LocalDate.of(2023, 8, 2));
                    factroyService.addEmployee(3, "bashar", 3, 12000, 2, "full", "bashar", "bashar12", 12000, LocalDate.of(2023, 8, 2));
                    factroyService.addEmployee(4, "rahaf", 4, 12000, 2, "full", "rahaf", "rahaf12", 12000, LocalDate.of(2023, 8, 2));
                    factroyService.addEmployeeRole(1, "Shift Manager",null);
                    factroyService.addEmployeeRole(2, "storekeeper",null);
                    factroyService.addEmployeeRole(3,"Shift Manager",null);
//                    factroyService.addRole(2, "driver");
//                    factroyService.addRole(1, "driver");
                    factroyService.addEmployeeRole(4, "driver","C1");
                    factroyService.addShift(1, LocalDate.of(2024, 10, 10), 2, "Morning", 1);
                    factroyService.addShift(2, LocalDate.of(2024, 10, 10), 2, "Morning", 2);
                    factroyService.addShiftToWeek(1,1);
                    factroyService.addShiftToWeek(1,2);
                    factroyService.addShiftToWeek(2,3);
                    factroyService.addShiftToWeek(2,4);
                    application.MenuPage();
                    break;
                case 2:
                    application.MenuPage();
                    break;
                case 3:
                    application.LoadData();
                    break;
                case 4:
                    application.DeleteData();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid input. Please enter a number between 1 and 5.");
            }
        }
    }
}
