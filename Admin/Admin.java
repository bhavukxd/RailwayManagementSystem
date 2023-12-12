package Admin;
import java.util.Scanner;

import Utils.Utils;

class Login {
    static final int MAX_ATTEMPTS = 3;
    static final String ADMIN_USERNAME = "admin";
    static final String ADMIN_PASSWORD = "adminpass";

    // Add this method to authenticate the login
    static boolean authenticate(String username, String password) {
        return ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password);
    }
}

public class Admin{
    public static void getadmin() {
        System.out.println("----------------------- Welcome Admin! -----------------------");

        Scanner scanner = new Scanner(System.in);
        int loginAttempts = 0;

        while (loginAttempts < Login.MAX_ATTEMPTS) {
            System.out.println("Welcome to the Admin Panel!");

            System.out.print("Enter your username: ");
            String username = scanner.nextLine();

            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            if (Login.authenticate(username, password)) {
                System.out.println("Login successful. Welcome, " + username + "!");
                adminMenu();  // Call the admin menu
                break;
            } else {
                System.out.println("Login failed. Please check your username and password.");
                loginAttempts++;
            }
        }

        if (loginAttempts == Login.MAX_ATTEMPTS) {
            System.out.println("Maximum login attempts reached. Exiting program.");
        }

        // Close the scanner
        scanner.close();
    }

    private static void adminMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Admin Menu:");
            System.out.println("1 - View Tickets");
            System.out.println("4 - Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    manageTickets(scanner);
                    break;
                case 4:
                    System.out.println("Logging out...");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void manageTickets(Scanner sc) {
        // Implement logic for managing tickets
        String[] Files =AdminTickets.printFiles();
        System.out.println("Enter the number");
        int oop = sc.nextInt();
        System.out.println(Utils.getFileContents(Files[oop-1]+".txt"));
            }

}
