import java.util.Scanner;

import Admin.Admin;
import Utils.Utils;
import java.text.SimpleDateFormat;
import java.util.Date;


class User {
    String username;
    String password;
    boolean loggedIn;

    User(String username, String password) {
        this.username = username;
        this.password = password;
        this.loggedIn = false;
    }

    boolean authenticate(String enteredPassword) {
        return this.password.equals(enteredPassword);
    }
}

class Ticket {
    public String trainName;
    public String startStation;
    public String destination;
    public int ticketNumber;

    Ticket(String trainName, String startStation, String destination, int ticketNumber) {
        this.trainName = trainName;
        this.startStation = startStation;
        this.destination = destination;
        this.ticketNumber = ticketNumber;
    }

    public static double calculateTicketCost() {

        return 50.0;
    }

    public void displayTicketDetails() {
        System.out.println("Ticket Details:");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Train Name: " + trainName);
        System.out.println("Start Station: " + startStation);
        System.out.println("Destination: " + destination);
        System.out.println("Ticket Number: " + ticketNumber);
        // System.out.println("Ticket type" + ticketType);
        System.out.println("-----------------------------------------------------------------");
    }

    public String getTrainName() {
        return trainName;
    }

    public String getStartStation() {
        return startStation;
    }

    public String getDestination() {
        return destination;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }
}

class RailwayManagementSystem {
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/javaproject";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "";
    public static Ticket[] bookedTickets = new Ticket[100];
    public static int ticketIndex = 0;
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Database database = new Database();
        int choice;
        User loggedInUser = null;
        System.out.println("///////////////////////////////////////\n");
        System.out.println("///////Welcome to Railway Ticket///////\n");
        System.out.println("///////////////////////////////////////\n");
        while (true) {
            System.out.println("Menu:");

            if (loggedInUser == null) {
                System.out.println("1 - Sign Up");
                System.out.println("2 - Log In");
                System.out.println("3 - Admin");
            } else {
                System.out.println("3 - Book Railway Ticket");
                System.out.println("4 - Check Your Booking Details");
                System.out.println("5 - Cancel Your Ticket");
                System.out.println("6 - Log Out");
            }

            System.out.println("7 - Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            if (loggedInUser == null) {
                switch (choice) {
                    case 1:
                        System.out.print("Enter a username: ");
                        String username = scanner.nextLine();
                        System.out.print("Enter a password: ");
                        String password = scanner.nextLine();
                        database.addUser(username,password);
                        loggedInUser = new User(username, password);
                        System.out.println("Sign-up successful. You are logged in now..");
                        break;
                    case 2:
                        System.out.print("Enter your username: ");
                        String loginUsername = scanner.nextLine();
                        System.out.print("Enter your password: ");
                        String loginPassword = scanner.nextLine();
                        String user[] = database.getUser(loginUsername);
                        System.out.println(user[0]);
                        loggedInUser = new User(user[0],user[1]);
                        if (loggedInUser != null && loggedInUser.username.equals(loginUsername)
                                && loggedInUser.authenticate(loginPassword)) {
                            loggedInUser.loggedIn = true;
                            System.out.println("Login successful. Welcome, " + loginUsername + "!");
                        } else {
                            System.out.println("Login failed. Please check your username and password.");
                        }
                        break;
                    case 3:
                        Admin.getadmin();
                        System.exit(0);
                        break;
                    case 7:
                        System.out.println("Goodbye!");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } else {
                switch (choice) {
                    case 3:
                        
                        System.out.println("Booking a ticket...");

                        System.out.print("Enter the date (dd/MM/yyyy): ");
                        String date = scanner.nextLine();
                        if (isValidDate(date)) {
                            System.out.print("Enter the start station: ");
                            String startStation = scanner.nextLine();
                            System.out.print("Enter the destination: ");
                            String destination = scanner.nextLine();
                            if (startStation.equalsIgnoreCase(destination)) {
                                System.out.println("Start station cannot be the same as the destination. Please try again.");
                                continue;  
                            }

                            System.out.println("You are checking trains between " + startStation + " and " + destination
                                    + " on " + date + ".");
                            System.out.println("Train Options:");
                            System.out.println("1 - Gareeb Rath");
                            System.out.println("2 - Shatabdi");
                            System.out.println("3 - Double Decker");
                            System.out.println("4 - Super Express");
                            System.out.println("Select a train (1-4): ");
                            int trainChoice = scanner.nextInt();
                            scanner.nextLine();

                            TrainDetails.Train selectedTrain = null;
                            String ticketType = "";
                            switch (trainChoice) {
                                case 1:
                                    selectedTrain = new TrainDetails.GareebRath();
                                    break;
                                case 2:
                                    selectedTrain = new TrainDetails.Shatabdi();
                                    break;
                                case 3:
                                    selectedTrain = new TrainDetails.DoubleDecker();
                                    break;
                                case 4:
                                    selectedTrain = new TrainDetails.SuperExpress();
                                    break;
                                default:
                                    System.out.println("Invalid train choice. Please try again.");
                                    continue;
                            }

                            System.out.println("Train: " + selectedTrain.getName());
                            System.out.println("Max Capacity: " + selectedTrain.getMaxCapacity());
                            System.out.println("Available Seats (SL): " + selectedTrain.getAvailableSeatsSL());
                            System.out.println("Available Seats (AC1): " + selectedTrain.getAvailableSeatsAC1());
                            System.out.println("Available Seats (AC2): " + selectedTrain.getAvailableSeatsAC2());
                            System.out.println("Available Seats (AC3): " + selectedTrain.getAvailableSeatsAC3());

                            System.out.print("Enter the number of tickets needed (max 6): ");
                            int numTickets = scanner.nextInt();
                            scanner.nextLine();

                            if (numTickets < 1 || numTickets > 6) {
                                System.out.println("Invalid number of tickets. Please select between 1 and 6 tickets.");
                            } else {
    System.out.print("Select the ticket type (SL, AC1, AC2, AC3): ");
    ticketType = scanner.nextLine();

    if (ticketType.equals("SL") && numTickets > selectedTrain.getAvailableSeatsSL()) {
        System.out.println("Not enough available SL seats for the requested tickets.");
    } else if (ticketType.equals("AC1") && numTickets > selectedTrain.getAvailableSeatsAC1()) {
        System.out.println("Not enough available AC1 seats for the requested tickets.");
    } else if (ticketType.equals("AC2") && numTickets > selectedTrain.getAvailableSeatsAC2()) {
        System.out.println("Not enough available AC2 seats for the requested tickets.");
    } else if (ticketType.equals("AC3") && numTickets > selectedTrain.getAvailableSeatsAC3()) {
        System.out.println("Not enough available AC3 seats for the requested tickets.");
    } else {
                                    int ticketNumber = (int) (Math.random() * 100000);

                                    Ticket newTicket = new Ticket(selectedTrain.getName(), startStation, destination,
                                            ticketNumber);
                                    bookedTickets[ticketIndex] = newTicket;
                                    ticketIndex++;

                                    selectedTrain.bookTicket(numTickets, numTickets, numTickets, numTickets);

                                    System.out.println("Ticket booked successfully.");

                                    String text= "----------------------------------------------------------------------------"+"\n"+
                                    "Ticket Number: " + ticketNumber+"\n"+
                                    "Train Name: " + selectedTrain.getName()+"\n"+
                                    "Starting Station: " + startStation+"\n"+
                                    "Destination: " + destination+"\n"+
                                    "The number of seats alloted on this ticket number:"+numTickets+"\n"+
                                    "The total price of " + numTickets + " tickets will be "+ (50 * numTickets)+"\n"+
                                    "Ticket Type : "+ticketType+"\n"+
                                    "----------------------------------------------------------------------------";
                                    Utils.createTextFile(Integer.toString(ticketNumber)+".txt", text);
                                    System.out.println(text);
                                }
                            }
                            
                        } else {
                            System.out.println("Invalid date. Please enter a future date (dd/MM/yyyy).");
                        }
                        break;

                    case 4:

                        System.out.println("Checking booking details...");
                        System.out.print("Enter the ticket number: ");
                        int ticketNumberToCheck = scanner.nextInt();
                        scanner.nextLine();

                        Ticket foundTicket = null;
                        for (Ticket ticket : bookedTickets) {
                            if (ticket != null && ticket.getTicketNumber() == ticketNumberToCheck) {
                                foundTicket = ticket;
                                break;
                            }
                        }

                        if (foundTicket != null) {
                            foundTicket.displayTicketDetails();
                        } else {
                            System.out.println("Ticket with ticket number " + ticketNumberToCheck + " not found.");
                        }
                        break;

                    case 5:

                        System.out.println("Canceling a ticket...");
                        System.out.print("Enter the ticket number to cancel: ");
                        int ticketNumberToCancel = scanner.nextInt();
                        scanner.nextLine();

                        int ticketIndexToCancel = -1;
                        for (int i = 0; i < ticketIndex; i++) {
                            if (bookedTickets[i] != null
                                    && bookedTickets[i].getTicketNumber() == ticketNumberToCancel) {
                                ticketIndexToCancel = i;
                                break;
                            }
                        }

                        if (ticketIndexToCancel != -1) {
                            System.out.println("Ticket Details:");
                            bookedTickets[ticketIndexToCancel].displayTicketDetails();
                            System.out.print("Do you want to cancel this ticket? (yes/no): ");
                            String cancelConfirmation = scanner.nextLine().toLowerCase();

                            if (cancelConfirmation.equals("yes")) {

                              Utils.deleteFile(Integer.toString(ticketNumberToCancel));

                                System.out.println("Ticket cancellation successful.");
                                System.out.println("20% will be deducted as the cancellation fees from the total amount");

                                for (int i = ticketIndexToCancel; i < ticketIndex - 1; i++) {
                                    bookedTickets[i] = bookedTickets[i + 1];
                                }

                                bookedTickets[ticketIndex - 1] = null;
                                ticketIndex--;
                            } else {
                                System.out.println("Ticket cancellation not confirmed.");
                            }
                        } else {
                            System.out.println("Ticket with ticket number " + ticketNumberToCancel + " not found.");
                        }
                        break;

                    case 6:
                        loggedInUser.loggedIn = false;
                        loggedInUser = null;
                        System.out.println("Logged out successfully.");
                        break;

                    case 7:
                        System.out.println("Goodbye!");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        }
    }
    public static boolean isValidDate(String date) {
        try {
            Date currentDate = new Date();
            Date inputDate = DATE_FORMAT.parse(date);
            return inputDate.after(currentDate);
        } catch (Exception e) {
            System.out.println("Invalid date format. Please enter a date in the format dd/MM/yyyy.");
            return false;
        }
    }

}
