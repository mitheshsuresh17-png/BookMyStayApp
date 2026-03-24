// Version 5.1 - Refactored Implementation

import java.util.*;

// Reservation Class (Represents booking request)
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void display() {
        System.out.println("Guest: " + guestName + " | Requested Room: " + roomType);
    }
}

// Booking Queue (FIFO Handling)
class BookingRequestQueue {

    private Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    // Add request to queue
    public void addRequest(Reservation reservation) {
        queue.offer(reservation);
        System.out.println("Request Added → " + reservation.getGuestName());
    }

    // View all requests (without removing)
    public void displayQueue() {
        System.out.println("\n===== BOOKING REQUEST QUEUE (FIFO) =====\n");

        if (queue.isEmpty()) {
            System.out.println("No pending requests.");
            return;
        }

        for (Reservation r : queue) {
            r.display();
        }
    }

    // Peek next request (no removal)
    public Reservation peekNext() {
        return queue.peek();
    }

    // Remove request (for future use case)
    public Reservation pollRequest() {
        return queue.poll();
    }
}

// Main Class
public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        // Initialize Queue
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Simulating Guest Requests (Arrival Order)
        bookingQueue.addRequest(new Reservation("Mithesh", "Single Room"));
        bookingQueue.addRequest(new Reservation("John", "Double Room"));
        bookingQueue.addRequest(new Reservation("Antonio", "Suite Room"));
        bookingQueue.addRequest(new Reservation("Rahul", "Single Room"));

        // Display Queue (FIFO Order)
        bookingQueue.displayQueue();

        // Show next request (without removing)
        System.out.println("\nNext Request to Process:");
        Reservation next = bookingQueue.peekNext();
        if (next != null) {
            next.display();
        }

        System.out.println("\nNote: No inventory changes performed.");
        System.out.println("Application Terminated.");
    }
}