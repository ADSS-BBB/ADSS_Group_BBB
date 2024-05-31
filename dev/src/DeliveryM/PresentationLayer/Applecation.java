package DeliveryM.PresentationLayer;
import DeliveryM.ServiceLayer.*;

import java.util.Enumeration;
import java.util.Scanner;

public class Applecation {

    private MainApplecation mainApplecation;
    Scanner input = new Scanner(System.in);
    public Applecation(){
        this.mainApplecation=new MainApplecation();
    }

    public void menu (){

        System.out.println("Delivery Page.");
        System.out.println("Please choose an option: ");
        System.out.println("1.Mange Trucks.");
        System.out.println("2.Mange Drivers.");
        System.out.println("3.Add Delivery.");
        int choice= input.nextInt();

        while (choice <1 || choice >3)
        {
            System.out.println("your choice is out of the range! ");
            System.out.println("Please choose an option: ");
            choice= input.nextInt();
        }
        switch (choice){
            case 1:
                System.out.println("Please choose an option: ");
                System.out.println("1.Add Truck.");
                System.out.println("2.Delete Truck.");
                int secondCH=input.nextInt();
                switch (secondCH){
                    case 1:
                        addTruck();
                        break;
                    case 2:
                        deleteTruck();
                        break;
                }
                break;
            case 2:
                System.out.println("Please choose an option: ");
                System.out.println("1.Add Driver.");
                System.out.println("2.Delete Driver.");
                int CH=input.nextInt();
                switch (CH){
                    case 1:
                        addDriver();
                        break;
                    case 2:
                        deleteDriver();
                        break;
                }
                break;
            case 3:
                addDelievry();
                break;
        }

    }
    private void addTruck(){
        //String model,int truckWeight,int maxWeight
        System.out.println("Please the truck's number: ");
        int number =input.nextInt();
        System.out.println("Please the truck's model: ");
        String model=input.nextLine();
        System.out.println("please enter the truck's weight:");
        int weight=input.nextInt();
        System.out.println("please enter the truck's max weight:");
        int maxW=input.nextInt();
        //mainApplecation.getTruckService().addTruck(number,model,weight,maxW);

        System.out.println("Truck has been added successfully!");


    }
    private void deleteTruck(){
        System.out.println("Please the truck's number: ");
        int number =input.nextInt();
        System.out.println("Truck has been deleted successfully!");
    }
    private void addDriver(){
        //String name,String licenseType
        System.out.println("Please the driver's name: ");
        String name=input.next();
        System.out.println("Please the driver's id: ");
        int id=input.nextInt();
        System.out.println("Please the driver's licence type from this options: C C1 E: ");
        String type=input.nextLine();
        System.out.println("driver has been added successfully!");
    }
    private void deleteDriver(){
        System.out.println("Please the driver's id: ");
        int id=input.nextInt();
        System.out.println("Driver has been deleted successfully!");
    }
    private void addDelievry(){
        System.out.println("Please the item's name: ");
        String name =input.next();
        System.out.println("Please the item's quantity: ");
        int qu=input.nextInt();
        System.out.println("please enter the destination:");
        String des=input.next();

        System.out.println("Delievry has been added successfully!");

    }
}
