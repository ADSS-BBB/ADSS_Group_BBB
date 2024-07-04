package DeliveryM.PresentationLayer;

import DeliveryM.BusinessLayer.Objects.Item;
import DeliveryM.BusinessLayer.Objects.Location;
import DeliveryM.ServiceLayer.MainApplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;

public class Application {

    private MainApplication mainApplication =new MainApplication();
    Scanner input = new Scanner(System.in);

    public Application() throws Exception {

    }

    public void menu() throws Exception {

        int choice;
        Scanner input = new Scanner(System.in);
        boolean menuLoop= true;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        //try {


            do {
                System.out.println("Menu:");
                System.out.println("1: Add Driver");
                System.out.println("2: Add Location");
                System.out.println("3: Add Truck");
                System.out.println("4: Add Delivery");
                System.out.println("5: Add documents");
                System.out.println("6: Remove Driver");
                System.out.println("7: Remove truck");
                System.out.println("8: Remove Delivery");
                System.out.println("9: Remove Location");
                //System.out.println("10: Change Location");
                System.out.println("10: Print All Drivers");
                System.out.println("11: Print All Trucks");
                System.out.println("12: Print All Locations");
                System.out.println("13: Print All deliveries");
                System.out.println("14: Print All documents of specific delivery");
                System.out.println("15: Update shift for driver");
                System.out.println("16: Add Storekeeper");
                System.out.println("17: Print Shifts");
                System.out.println("18:Exit");
                System.out.println("enter your menu choice:");
                choice=input.nextInt();

                switch (choice){
                    case 1:
                        System.out.println("Enter driver's id: ");
                        int id=input.nextInt();
                        System.out.println("Enter driver's name: ");
                        String name=input.next();
                        System.out.println("Enter driver's license Type C/C1/E:");
                        String licensetype=input.next();
                        System.out.println(mainApplication.addDriver(id,name,licensetype));

                        break;
                    case 2:
                        System.out.println("Enter details of the location");
                        System.out.println("Enter Site's Address: ");
                        String siteAddress=input.next();
                        System.out.println("Enter phone Number: ");
                        String PhoneNum=input.next();
                        System.out.println("Enter contact Name: ");
                        String contactN=input.next();
                        System.out.println("Enter the area of this location: ");
                        String area=input.next();
                        System.out.println(mainApplication.addLocation(siteAddress,PhoneNum,contactN,area,-1));
                        break;
                    case 3:
                        System.out.println("Enter truck's Number : ");
                        int num =input.nextInt();
                        System.out.println("Enter truck Model: ");
                        String model=input.next();
                        System.out.println("Enter truck's Nato Weight : ");
                        int natoWeight =input.nextInt();
                        System.out.println("Enter truck's Max Weight : ");
                        int maxWeight =input.nextInt();
                        System.out.println(mainApplication.addTruck(num,model,natoWeight,maxWeight));

                        break;
                    case 4:

//                        input.nextLine(); // Consume the newline left-over
//
//
//
//                        System.out.println("Enter Shift Starting Date (dd/MM/yyyy):");
//                        String startingDateStr = input.nextLine();
//
//                        System.out.println("Enter Shift Starting Time (HH:mm):");
//                        String startingTimeStr = input.nextLine();
//
//                        System.out.println("Enter Shift Ending Date (dd/MM/yyyy):");
//                        String endingDateStr = input.nextLine();
//
//                        System.out.println("Enter Shift Ending Time (HH:mm):");
//                        String endingTimeStr = input.nextLine();
                        input.nextLine(); // Consume the newline left-over

                        System.out.println("Enter The date of the delivery with format dd/MM/yyyy:");
                        String date = input.nextLine();

                        System.out.println("Enter leaving Time (HH:mm):");
                        String leavingtime=input.nextLine();

                        System.out.println("Enter arrive Date (dd/MM/yyyy):");
                        String arriveDatedriver = input.nextLine();

                        System.out.println("Enter arrive Time (HH:mm):");
                        String arriveTimedriver = input.nextLine();

                        LocalDateTime leaving = LocalDateTime.parse(date + " " + leavingtime, dateTimeFormatter);
                        LocalDateTime arrive = LocalDateTime.parse(arriveDatedriver + " " + arriveTimedriver, dateTimeFormatter);

                        System.out.println("Enter The truck number: ");
                        int trucknumber=input.nextInt();
                        System.out.println("Enter The driver id number: ");
                        int driver=input.nextInt();
                        input.nextLine(); // Consume the newline left-over
                        System.out.println(mainApplication.addDelivery(leaving,trucknumber,driver,"here",arrive));
                        break;
                    case 5:
                        System.out.println("Enter exist delivery ID:");
                        int delID;
                        while (true){
                            delID = input.nextInt();
                            if(mainApplication.getDelievryService().ALLdeliveries() !=null && mainApplication.getDelievryService().ALLdeliveries().containsKey(delID))break;
                            else {
                                System.out.println("Invalid id please Enter exist one!");
                            }
                        }
                        System.out.println("""
                        choose one:\s
                        1.add new location
                        2.choose exist location""");
                        int choose = input.nextInt();
                        if(choose==1){
                            System.out.println("you must add your new location please enter the following details");
                            System.out.println("Enter details of the location");
                            System.out.println("Enter Site's Address: ");
                            String siteAddress1=input.next();
                            System.out.println("Enter phone Number: ");
                            String PhoneNum1=input.next();
                            System.out.println("Enter contact Name: ");
                            String contactN1=input.next();
                            System.out.println("Enter the area of this location: ");
                            String area1=input.next();
                            System.out.println(mainApplication.addLocation(siteAddress1,PhoneNum1,contactN1,area1,delID));
                            System.out.println("now you must add the items, please enter the following details");
                            System.out.println("Enter your amount of products: ");
                            int numberofitems=input.nextInt();
                            HashMap<Item,Integer> products=new HashMap<>();
                            Location l=null;
                            for (int i = 1;i<=numberofitems;i++){
                                System.out.println("Enter name of the item:");
                                String itemName = input.next();
                                System.out.println("Enter how much you want from this item:");
                                int quantity = input.nextInt();
                                System.out.println("Enter weight of this item:");
                                int ItemWeight=input.nextInt();
                                l=mainApplication.getLocationbyADD(siteAddress1);
                                products.put(new Item(itemName,ItemWeight,l.getLocationId()),quantity);
                            }
                            System.out.println(mainApplication.addDoc(products,delID,l.getLocationId()));
                            break;
                        }else {
                            System.out.println("Enter id of exist location:");
                            int idlocation =input.nextInt();
                            mainApplication.getLocation(idlocation);
                            System.out.println("Enter your amount of products: ");
                            int numberofitems=input.nextInt();
                            HashMap<Item,Integer> products=new HashMap<>();
                            for (int i = 1;i<=numberofitems;i++){
                                System.out.println("Enter name of the item:");
                                String itemName = input.next();
                                System.out.println("Enter how much you want from this item:");
                                int quantity = input.nextInt();
                                System.out.println("Enter weight of this item:");
                                int ItemWeight=input.nextInt();
                                products.put(new Item(itemName,ItemWeight,idlocation),quantity);
                            }
                            System.out.println(mainApplication.addDoc(products,delID,idlocation));
                        }

                        break;
                    case 6:
                        System.out.println("Enter driver's id: ");
                        int Id=input.nextInt();
                        System.out.println(mainApplication.deleteDriver(Id));

                        break;

                    case 7:
                        System.out.println("Enter truck's Number : ");
                        int tNum =input.nextInt();
                        System.out.println(mainApplication.deleteTruck(tNum));

                        break;
                    case 8: System.out.println("Enter delivery's id: ");
                        int deliveryId=input.nextInt();
                        System.out.println(mainApplication.deletedelivery(deliveryId));

                        break;
                    case 9:
                        System.out.println("Enter Site's Address: ");
                        String siteAddres=input.next();
                        System.out.println( mainApplication.removeLocation(siteAddres));

                        break;
                    case 10:  System.out.println(mainApplication.printalldrivers());

                        break;
                    case 11:
                        System.out.println(mainApplication.printalltrucks());

                        break;
                    case 12:
                        System.out.println(mainApplication.printAllLocations());
                        break;
                    case 13:
                        System.out.println(mainApplication.printalldeliveries());

                        break;
                    case 14:
                        System.out.println("Enter delivery id to print his docs");
                        int iddel= input.nextInt();
                        System.out.println(mainApplication.printalldoc(iddel));

                        break;
                    case 15:
                        System.out.println("you can update the driver's shifts, please enter the following details!");
                        System.out.println("Enter the driver id:");
                        int drId=input.nextInt();
                        input.nextLine(); // Consume the newline left-over
                        System.out.println("Enter Shift Starting Date (dd/MM/yyyy):");
                        String startingDatedriver = input.nextLine();
                        System.out.println("Enter Shift Starting Time (HH:mm):");
                        String startingTimedriver = input.nextLine();
                        System.out.println("Enter Shift Ending Date (dd/MM/yyyy):");
                        String endingDatedriver = input.nextLine();
                        System.out.println("Enter Shift Ending Time (HH:mm):");
                        String endingTimedriver = input.nextLine();

                        LocalDateTime startingTimeD = LocalDateTime.parse(startingDatedriver + " " + startingTimedriver, dateTimeFormatter);
                        LocalDateTime endingTimeD = LocalDateTime.parse(endingDatedriver + " " + endingTimedriver, dateTimeFormatter);

                        System.out.println(mainApplication.addShiftDriver(drId,startingTimeD,endingTimeD));
                        break;
                    case 16:
                        input.nextLine(); // Consume the newline left-over
                        System.out.println("Enter Work Address:");
                        String workAddress = input.nextLine();

                        System.out.println("Enter Shift Starting Date (dd/MM/yyyy):");
                        String startingDateStr = input.nextLine();

                        System.out.println("Enter Shift Starting Time (HH:mm):");
                        String startingTimeStr = input.nextLine();

                        System.out.println("Enter Shift Ending Date (dd/MM/yyyy):");
                        String endingDateStr = input.nextLine();

                        System.out.println("Enter Shift Ending Time (HH:mm):");
                        String endingTimeStr = input.nextLine();

                        try {
                            LocalDateTime startingTime = LocalDateTime.parse(startingDateStr + " " + startingTimeStr, dateTimeFormatter);
                            LocalDateTime endingTime = LocalDateTime.parse(endingDateStr + " " + endingTimeStr, dateTimeFormatter);
                            System.out.println(mainApplication.addShiftsStoreKeeper(workAddress,startingTime,endingTime));
                        } catch (Exception e) {
                            System.out.println("Invalid date/time format. Please use the correct formats.");
                        }
                        break;


                    case 17:
                        System.out.println(mainApplication.printShifts());
                        break;
                    case 18:
                        System.out.println("Exit successfully :) ");
                        System.exit(0);

                }

            }while (choice<=18);




    }
}