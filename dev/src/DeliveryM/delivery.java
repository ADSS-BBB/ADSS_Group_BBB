package DeliveryM;

import DeliveryM.BusinessLayer.Objects.Item;
import DeliveryM.BusinessLayer.Objects.Location;
import DeliveryM.PresentationLayer.Application;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Scanner;

public class delivery {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            //MainApplication service = new MainApplication();
            Application menu = new Application();
            menu.menu();
            System.out.println("Do you want to go back to the Main menu? (yes/no)");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("yes")) {
                break;
            }
        }
    }

}


//case 18:
//        System.out.println("Exiting to Delivery Menu...");
//menuLoop = false;
//        break;
