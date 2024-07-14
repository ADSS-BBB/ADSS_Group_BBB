package HR;

import HR.DataAccessLayer.HRData.PersonnelManagerDAO;
import HR.DataAccessLayer.HRData.SuperLeeDataController;
import HR.DomainLayer.BranchPackage.Branch;
import HR.DomainLayer.PersonnelManager;
import HR.PresentationLayer.Application;
import HR.ServiceLayer.FactroyService;

import java.time.LocalDate;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
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
        while (true) {
            Scanner sc = new Scanner(System.in);
            String input;
            System.out.println("Welcome to SuperLee");
            System.out.println("1. With Data");
            System.out.println("2. WithOut Data");
            System.out.println("3. Load Data");
            System.out.println("4. Delete Data");
            input = sc.nextLine();
            switch (Integer.parseInt(input)) {
                case 1:
                    SuperLeeDataController.getInstance().insertpersonnelmanager(manager.getName());
                    factroyService.addbranch(1, "Beer sheva");
                    factroyService.addEmployee(1, "essa", 1, 12000, 1, "full", "essa", "essa12", 15000, LocalDate.of(2023, 8, 2));
                    factroyService.addEmployee(2, "athel", 2, 12000, 1, "full", "athel", "athel12", 12000, LocalDate.of(2023, 8, 2));
                    factroyService.addEmployeeRole(1, "Shift Manager");
                    factroyService.addEmployeeRole(2, "cashier");
                    factroyService.addShift(1, LocalDate.of(2024, 10, 10), 2, "Morning", 1);
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
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }


    }


}