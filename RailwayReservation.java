package railwayreservation.system;

import railwayreservation.entities.Train;
import railwayreservation.exceptions.SeatNotAvailableException;
import railwayreservation.interfaces.ReservationSystem;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.util.Collections.addAll;


public class RailwayReservation implements ReservationSystem {
    private Map<String, Train> trains;
    private Map<String, Map<String, Integer>> reservations;

    public RailwayReservation() {
        trains = new HashMap<>();
        reservations = new HashMap<>();
        initializeTrains();
    }

    private void initializeTrains() {
        Train train1 = new Train("Rajdhani", 50);
        Train train2 = new Train("Fast456", 40);
        Train train3 = new Train("Super789", 30);

        trains.put("Rajdhani", train1);
        trains.put("Fast456", train2);
        trains.put("Super789", train3);
    }
    ArrayList<String> destination=new ArrayList<String>();
    int i=0;
    @Override
    public void displayAvailableTrains() {
    addAll(destination,"Puri","Delhi","Ayodhya");
        System.out.println("Available Trains:");
        for (Train train : trains.values()) {
            System.out.println(train.getName() + " - Available Seats: " + train.getAvailableSeats()+"\t Bhubaneswar to "+destination.get(i));
            i++;
            }
    }

    @Override
    public void bookTicket(String trainName, String passengerName, int age, String gender) throws SeatNotAvailableException {
        Payment payment =new Payment();
        Train train = trains.get(trainName);
        if (train == null) {
            System.out.println("Invalid train name");
            return;

        }
        switch (trainName){
            case"Rajdhani":
                System.out.println("\nYour fair amount per seat is ₹"+ 499);break;
            case"Fast456":
                System.out.println("\nYour fair amount per seat is ₹"+399);break;
            case"Super789":
                System.out.println("\nYour fair amount per seat is ₹"+199);break;
            default:
                System.out.println("no amount");

        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of seats to book in "+trainName+" : ");
        int seats = scanner.nextInt();
        train.bookSeats(seats);

        int cost = 0;
        switch (trainName){
            case"Rajdhani1":
                cost=seats*499;
                System.out.println("\nYour total fair amount for "+seats+" seats is ₹"+cost);break;
            case"Fast456":
                cost=seats*399;
                System.out.println("\nYour total fair amount for "+seats+" seats is ₹"+cost);break;
            case "Super789":
                cost=seats*199;
                System.out.println("\nYour total fair amount for "+seats+" seats is ₹"+cost);break;
            default:
                System.out.println("No amo");

        }
        if(payment.payment(cost)) {
            // Store reservation information
            reservations.computeIfAbsent(trainName, k -> new HashMap<>());
            Map<String, Integer> trainReservations = reservations.get(trainName);
            trainReservations.put(passengerName, age);

            System.out.println("\nTicket booked successfully!");
            System.out.println("Train: " + trainName + ", Seats booked: " + seats);
            System.out.println("Passenger: " + passengerName + ", Age: " + age + "Gender: " + gender);

            // Save reservation information to a text file
            saveToTextFile(trainName, passengerName, age, seats,gender);
        }
    }

    public void cancelTicket(String trainName, String passengerName) {
        Map<String, Integer> trainReservations = reservations.get(trainName);
        if (trainReservations != null && trainReservations.containsKey(passengerName)) {
            int canceledSeats = trainReservations.remove(passengerName);
            Train train = trains.get(trainName);
            train.returnSeats(canceledSeats);

            System.out.println("Ticket canceled successfully!");
            System.out.println("Train: " + trainName + ", Canceled Seats: " + canceledSeats);
            System.out.println("Passenger: " + passengerName);
        } else {
            System.out.println("No reservation found for the specified passenger on the given train.");
        }
    }

    public void showPassengerList(String trainName) {
        Map<String, Integer> trainReservations = reservations.get(trainName);
        if (trainReservations != null && !trainReservations.isEmpty()) {
            System.out.println("Passenger list for Train " + trainName + ":");
            for (Map.Entry<String, Integer> entry : trainReservations.entrySet()) {
                System.out.println("Passenger: " + entry.getKey() + ", Age: " + entry.getValue());
            }
        } else {
            System.out.println("No reservations found for Train " + trainName);
        }
    }

    private void saveToTextFile(String trainName, String passengerName, int age, int seats,String gender) {
        try (FileWriter writer = new FileWriter("reservations.txt", true)) {
            writer.write("Train: " + trainName + ", Passenger: " + passengerName +
                    ", Age: " + age + ", Seats: " + seats + ", Gender: "+ gender+ "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean isBooked(String trainName, String passengerName) {
        Map<String, Integer> trainReservations = reservations.get(trainName);
        if (trainReservations != null && trainReservations.containsKey(passengerName)) {
            return true;
        } else {
            return false;        }
    }

}
