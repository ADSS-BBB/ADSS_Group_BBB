package HR;

import HR.DomainLayer.BranchPackage.Branch;
import HR.DomainLayer.PersonnelManager;
import HR.PresentationLayer.Application;
import HR.ServiceLayer.FactroyService;

import java.time.LocalDate;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static FactroyService factroyService = new FactroyService();
    public static PersonnelManager manager = new PersonnelManager("Firas");
    public static Application application= new Application(factroyService,manager);

    public static void main(String[] args) throws Exception {
        factroyService.addbranch(1,"Beer sheva");
        factroyService.addEmployee(1,"essa",1,12000,1,"full","essa","essa12",15000,LocalDate.of(2023,8,2));
        factroyService.addEmployee(2,"athel",2,12000,1,"full","athel","athel12",15000,LocalDate.of(2023,8,2));
        factroyService.addEmployeeRole(1,"Shift Manager");
        factroyService.addEmployeeRole(2,"cashier");
        factroyService.addShift(1,LocalDate.of(2024,6,10),1,2,"Morning",1);
        application.MenuPage();
    }


   }