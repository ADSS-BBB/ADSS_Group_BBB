import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please choose an application to run:");
            System.out.println("1. DeliveryM Application");
            System.out.println("2. HR Application");
            System.out.println("3. Exit");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    try {
                        System.out.println("Starting DeliveryM Application...");
                        DeliveryM.delivery.main(args);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case "2":
                    try {
                        System.out.println("Starting WorkerM Application...");
                        HR.worker.main(args);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case "3":
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid input. Please enter 1, 2, or 3.");
                    break;
            }
        }
    }
}
