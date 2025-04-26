// Package: railwayreservation.entities
package railwayreservation.entities;

import railwayreservation.exceptions.SeatNotAvailableException;

public class Train {
    private String name;
    private int totalSeats;
    private int availableSeats;

    public Train(String name, int totalSeats) {
        this.name = name;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }

    public String getName() {
        return name;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void bookSeats(int seats) throws SeatNotAvailableException {
        if (seats > availableSeats) {
            throw new SeatNotAvailableException("Not enough seats available");
        }
        availableSeats -= seats;
    }

    // New method to return seats when a ticket is canceled
    public void returnSeats(int seats) {
        availableSeats += seats;
    }
}
