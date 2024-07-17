package DeliveryM.PresentationLayer;

import DeliveryM.BusinessLayer.Objects.Item;
import DeliveryM.BusinessLayer.Objects.Location;
import DeliveryM.ServiceLayer.MainApplication;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Application {

    private MainApplication mainApplication = new MainApplication();
    Scanner input = new Scanner(System.in);

    public Application() throws Exception {


    }

    public void menu() throws Exception {

        int choice;
        Scanner input = new Scanner(System.in);
        boolean menuLoop = true;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        do {
            System.out.println("Menu:");
            System.out.println("1: With Data");
            System.out.println("2: Add Location");
            System.out.println("3: Add Truck");
            System.out.println("4: Add Delivery");
            System.out.println("5: Add documents");
            //System.out.println("6: Remove Driver");
            System.out.println("7: Remove truck");
            System.out.println("8: Remove Delivery");
            System.out.println("9: Remove Location");
            System.out.println("10: Print All Drivers");
            System.out.println("11: Print All Trucks");
            System.out.println("12: Print All Locations");
            System.out.println("13: Print All deliveries");
            System.out.println("14: Print All documents of specific delivery");
            //1
            // System.out.println("15: Update shift for driver");
            System.out.println("16: Load Data");
            System.out.println("17: Delete Data");
            System.out.println("18: Exit");
            System.out.println("enter your menu choice:");

            try {
                choice = input.nextInt();
                input.nextLine(); // Consume the newline left-over

                switch (choice) {
                    case 1:
                        addDataForUs();
//                        try {
//                            System.out.println("Enter driver's id: ");
//                            int id = input.nextInt();
//                            input.nextLine(); // Consume the newline left-over
//                            System.out.println("Enter driver's name: ");
//                            String name = input.nextLine();
//                            System.out.println("Enter driver's license Type C/C1/E:");
//                            String licensetype = input.nextLine();
//                            System.out.println(mainApplication.addDriver(id, name, licensetype));
//                        } catch (Exception e) {
//                            System.out.println("Error adding driver: " + e.getMessage());
//                        }
                        break;
                    case 2:
                        try {
                            System.out.println("Enter details of the location");
                            System.out.println("Enter Site's Address: ");
                            String siteAddress = input.nextLine();
                            System.out.println("Enter phone Number: ");
                            String PhoneNum = input.nextLine();
                            System.out.println("Enter contact Name: ");
                            String contactN = input.nextLine();
                            System.out.println("Enter the area of this location: ");
                            String area = input.nextLine();
                            System.out.println(mainApplication.addLocation(siteAddress, PhoneNum, contactN, area, -1));
                        } catch (Exception e) {
                            System.out.println("Error adding location: " + e.getMessage());
                        }
                        break;
                    case 3:
                        try {
                            System.out.println("Enter truck's Number : ");
                            int num = input.nextInt();
                            input.nextLine(); // Consume the newline left-over
                            System.out.println("Enter truck Model: ");
                            String model = input.nextLine();
                            System.out.println("Enter truck's Nato Weight : ");
                            int natoWeight = input.nextInt();
                            input.nextLine(); // Consume the newline left-over
                            System.out.println("Enter truck's Max Weight : ");
                            int maxWeight = input.nextInt();
                            input.nextLine(); // Consume the newline left-over
                            System.out.println(mainApplication.addTruck(num, model, natoWeight, maxWeight));
                        } catch (Exception e) {
                            System.out.println("Error adding truck: " + e.getMessage());
                        }
                        break;
                    case 4:
                        try {
                            input.nextLine(); // Consume the newline left-over
                            System.out.println("Enter The date of the delivery with format dd/MM/yyyy:");
                            String date = input.nextLine();
                            System.out.println("Enter leaving Time (HH:mm):");
                            String leavingtime = input.nextLine();
                            System.out.println("Enter arrive Date (dd/MM/yyyy):");
                            String arriveDatedriver = input.nextLine();
                            System.out.println("Enter arrive Time (HH:mm):");
                            String arriveTimedriver = input.nextLine();
                            LocalDateTime leaving = LocalDateTime.parse(date + " " + leavingtime, dateTimeFormatter);
                            LocalDateTime arrive = LocalDateTime.parse(arriveDatedriver + " " + arriveTimedriver, dateTimeFormatter);
                            System.out.println("Enter The truck number: ");
                            int trucknumber = input.nextInt();
                            System.out.println("Enter The driver id number: ");
                            int driver = input.nextInt();
                            input.nextLine(); // Consume the newline left-over
                            System.out.println(mainApplication.addDelivery(leaving, trucknumber, driver, "here", arrive));
                        } catch (Exception e) {
                            System.out.println("Error adding delivery: " + e.getMessage());
                        }
                        break;
                    case 5:
                        try {
                            System.out.println("Enter exist delivery ID:");
                            int delID;
                            while (true) {
                                delID = input.nextInt();
                                if (mainApplication.getDelievryService().ALLdeliveries() != null && mainApplication.getDelievryService().ALLdeliveries().containsKey(delID)) break;
                                else {
                                    System.out.println("Invalid id please Enter exist one!");
                                }
                            }
                            System.out.println("""
                                    choose one:\s
                                    1.add new location
                                    2.choose exist location""");
                            int choose = input.nextInt();
                            if (choose == 1) {
                                System.out.println("you must add your new location please enter the following details");
                                System.out.println("Enter details of the location");
                                System.out.println("Enter Site's Address: ");
                                String siteAddress1 = input.next();
                                System.out.println("Enter phone Number: ");
                                String PhoneNum1 = input.next();
                                System.out.println("Enter contact Name: ");
                                String contactN1 = input.next();
                                System.out.println("Enter the area of this location: ");
                                String area1 = input.next();
                                System.out.println(mainApplication.addLocation(siteAddress1, PhoneNum1, contactN1, area1, delID));
                                System.out.println("now you must add the items, please enter the following details");
                                System.out.println("Enter your amount of products: ");
                                int numberofitems = input.nextInt();
                                HashMap<Item, Integer> products = new HashMap<>();
                                Location l = null;
                                for (int i = 1; i <= numberofitems; i++) {
                                    System.out.println("Enter name of the item:");
                                    String itemName = input.next();
                                    System.out.println("Enter how much you want from this item:");
                                    int quantity = input.nextInt();
                                    System.out.println("Enter weight of this item:");
                                    int ItemWeight = input.nextInt();
                                    l = mainApplication.getLocationbyADD(siteAddress1);
                                    products.put(new Item(itemName, ItemWeight, l.getLocationId()), quantity);
                                }
                                System.out.println(mainApplication.addDoc(products, delID, l.getLocationId()));
                            } else {
                                System.out.println("Enter id of exist location:");
                                int idlocation = input.nextInt();
                                mainApplication.getLocation(idlocation);
                                System.out.println("Enter your amount of products: ");
                                int numberofitems = input.nextInt();
                                HashMap<Item, Integer> products = new HashMap<>();
                                for (int i = 1; i <= numberofitems; i++) {
                                    System.out.println("Enter name of the item:");
                                    String itemName = input.next();
                                    System.out.println("Enter how much you want from this item:");
                                    int quantity = input.nextInt();
                                    System.out.println("Enter weight of this item:");
                                    int ItemWeight = input.nextInt();
                                    products.put(new Item(itemName, ItemWeight, idlocation), quantity);
                                }
                                System.out.println(mainApplication.addDoc(products, delID, idlocation));
                            }
                        } catch (Exception e) {
                            System.out.println("Error adding documents: " + e.getMessage());
                        }
                        break;
                    case 6:
//                        try {
//                            System.out.println("Enter driver's id: ");
//                            int Id = input.nextInt();
//                            System.out.println(mainApplication.deleteDriver(Id));
//                        } catch (Exception e) {
//                            System.out.println("Error removing driver: " + e.getMessage());
//                        }
                        break;
                    case 7:
                        try {
                            System.out.println("Enter truck's Number : ");
                            int tNum = input.nextInt();
                            System.out.println(mainApplication.deleteTruck(tNum));
                        } catch (Exception e) {
                            System.out.println("Error removing truck: " + e.getMessage());
                        }
                        break;
                    case 8:
                        try {
                            System.out.println("Enter delivery's id: ");
                            int deliveryId = input.nextInt();
                            System.out.println(mainApplication.deletedelivery(deliveryId));
                        } catch (Exception e) {
                            System.out.println("Error removing delivery: " + e.getMessage());
                        }
                        break;
                    case 9:
                        try {
                            System.out.println("Enter location's address: ");
                            String locId = input.next();
                            System.out.println(mainApplication.removeLocation(locId));
                        } catch (Exception e) {
                            System.out.println("Error removing location: " + e.getMessage());
                        }
                        break;
                    case 10:
                        try {
                            System.out.println(mainApplication.printalldrivers());
                        } catch (Exception e) {
                            System.out.println("Error printing drivers: " + e.getMessage());
                        }
                        break;
                    case 11:
                        try {
                            System.out.println(mainApplication.printalltrucks());
                        } catch (Exception e) {
                            System.out.println("Error printing trucks: " + e.getMessage());
                        }
                        break;
                    case 12:
                        try {
                            System.out.println(mainApplication.printAllLocations());
                        } catch (Exception e) {
                            System.out.println("Error printing locations: " + e.getMessage());
                        }
                        break;
                    case 13:
                        try {
                            System.out.println(mainApplication.printalldeliveries());
                        } catch (Exception e) {
                            System.out.println("Error printing deliveries: " + e.getMessage());
                        }
                        break;
                    case 14:
                        try {
                            System.out.println("Enter delivery id to print his docs");
                            int iddel = input.nextInt();
                            System.out.println(mainApplication.printalldoc(iddel));
                        } catch (Exception e) {
                            System.out.println("Error printing documents: " + e.getMessage());
                        }
                        break;
                    case 15:
//                        try {
//                            System.out.println("you can update the driver's shifts, please enter the following details!");
//                            System.out.println("Enter the driver id:");
//                            int drId = input.nextInt();
//                            input.nextLine(); // Consume the newline left-over
//                            System.out.println("Enter Shift Starting Date (dd/MM/yyyy):");
//                            String startingDatedriver = input.nextLine();
//                            System.out.println("Enter Shift Starting Time (HH:mm):");
//                            String startingTimedriver = input.nextLine();
//                            System.out.println("Enter Shift Ending Date (dd/MM/yyyy):");
//                            String endingDatedriver = input.nextLine();
//                            System.out.println("Enter Shift Ending Time (HH:mm):");
//                            String endingTimedriver = input.nextLine();
//                            LocalDateTime startingTimeD = LocalDateTime.parse(startingDatedriver + " " + startingTimedriver, dateTimeFormatter);
//                            LocalDateTime endingTimeD = LocalDateTime.parse(endingDatedriver + " " + endingTimedriver, dateTimeFormatter);
//                            System.out.println(mainApplication.addShiftDriver(drId, startingTimeD, endingTimeD));
//                        } catch (Exception e) {
//                            System.out.println("Error updating driver's shift: " + e.getMessage());
//                        }
                        break;
                    case 16:
                        mainApplication.loadData();
                        break;
                    case 17:
                        mainApplication.deletedata();
                        break;
                    case 18:
                        System.out.println("Exiting to Delivery Menu...");
                        menuLoop = false;
                        break;

                    default:
                        System.out.println("Invalid input. Please enter a number between 1 and 18.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                input.nextLine(); // Consume the invalid input
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                input.nextLine(); // Consume any remaining input
            }
        } while (menuLoop);

    }

    private void addDataForUs() throws Exception {
        mainApplication.addLocation("Beer sheva","0527679","me","Beer sheva",1);
        mainApplication.addLocation("Tel Aviv","0527679","me","Tel Aviv",2);
        mainApplication.addTruck(12,"tr",7000,10000);

        mainApplication.getDriverService().addDriver(4,"rahaf","C1");
        mainApplication.addDelivery(LocalDateTime.of(LocalDate.of(2024,10,10), LocalTime.of(13,0)),12,4,"TelAviv",LocalDateTime.of(LocalDate.of(2024,10,10), LocalTime.of(15,0)));
        int numberofitems = 1;
        HashMap<Item, Integer> products = new HashMap<>();
        Location l = null;
        for (int i = 1; i <= numberofitems; i++) {
            String itemName = "milk";
            int quantity = 1;
            int ItemWeight = 20;
            l = mainApplication.getLocationbyADD("Beer sheva");

            products.put(new Item(itemName, ItemWeight, 0), quantity);
        }
        mainApplication.addDoc(products, 0, l.getLocationId());

    }

}
