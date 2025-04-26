package railwayreservation.interfaces;

import railwayreservation.exceptions.SeatNotAvailableException;

public interface ReservationSystem {
    void displayAvailableTrains();

    void bookTicket(String trainName, String passengerName, int age, String gender) throws SeatNotAvailableException;
}
