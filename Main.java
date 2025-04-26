package railwayreservation.main;

import railwayreservation.system.RailwayReservation;
import railwayreservation.system.Payment;
import railwayreservation.exceptions.SeatNotAvailableException;
import servises.Food;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("\n\033[1mWelcome to Urban Rails\033[0m");
//    void login() {
//            if (Login.login()) {
//                System.out.println("Login Successful");
//            } else {
//                Login.login();
//            }
//        }
//        }
        while (true){
            if(Login.login()){
                System.out.println("Login Successful");
                break;
            }
            else
                System.out.println("Invalid User name or password\n Please try again..");

        }
        RailwayReservation reservationSystem = new RailwayReservation();
        Payment payment = new Payment();
        Scanner scanner = new Scanner(System.in);



        while (true) {
            System.out.println("\n1. Display Available Trains");
            System.out.println("2. Book Ticket");
            System.out.println("3. Cancel Ticket");
            System.out.println("4. Show Passenger List");
            System.out.println("5. Order food");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    reservationSystem.displayAvailableTrains();
                    break;
                case 2:
                    System.out.print("Enter the train name: ");
                    String bookTrainName = scanner.next();
                    System.out.print("Enter passenger name: ");
                    String bookPassengerName = scanner.next();
                    System.out.print("Enter passenger age: ");
                    int bookAge = scanner.nextInt();
                    System.out.print("Enter passenger gender(m/f): ");
                    String gender = scanner.next();

                    try {
                        reservationSystem.bookTicket(bookTrainName, bookPassengerName, bookAge, gender);
                    } catch (SeatNotAvailableException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.print("Enter the train name: ");
                    String cancelTrainName = scanner.next();
                    System.out.print("Enter passenger name: ");
                    String cancelPassengerName = scanner.next();

                    reservationSystem.cancelTicket(cancelTrainName, cancelPassengerName);
                    break;
                case 4:
                    System.out.print("Enter the train name: ");
                    String showListTrainName = scanner.next();
                    reservationSystem.showPassengerList(showListTrainName);
                    break;
                case 5:
                    Food.food();break;
                case 6:
                    System.out.println("Exiting the program. Thank you!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
