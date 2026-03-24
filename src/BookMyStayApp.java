// Version 2.1 - Refactored Implementation

// Abstract Class
abstract class Room {
    protected int beds;
    protected int size;
    protected double price;

    // Constructor
    public Room(int beds, int size, double price) {
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    // Abstract method
    public abstract String getRoomType();

    // Common method
    public void displayRoomDetails() {
        System.out.println("Room Type: " + getRoomType());
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sq.ft");
        System.out.println("Price: ₹" + price);
    }
}

// Single Room Class
class SingleRoom extends Room {

    public SingleRoom() {
        super(1, 200,  1500);
    }

    @Override
    public String getRoomType() {
        return "Single Room";
    }
}

// Double Room Class
class DoubleRoom extends Room {

    public DoubleRoom() {
        super(2, 350, 2500);
    }

    @Override
    public String getRoomType() {
        return "Double Room";
    }
}

// Suite Room Class
class SuiteRoom extends Room {

    public SuiteRoom() {
        super(3, 500, 5000);
    }

    @Override
    public String getRoomType() {
        return "Suite Room";
    }
}

// Main Class
public class BookMyStayApp {

    public static void main(String[] args) {

        // Creating Room Objects (Polymorphism)
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Static Availability Variables
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        // Display Details
        System.out.println("===== HOTEL ROOM AVAILABILITY =====\n");

        single.displayRoomDetails();
        System.out.println("Available: " + singleAvailable);
        System.out.println("-----------------------------------");

        doubleRoom.displayRoomDetails();
        System.out.println("Available: " + doubleAvailable);
        System.out.println("-----------------------------------");

        suite.displayRoomDetails();
        System.out.println("Available: " + suiteAvailable);
        System.out.println("-----------------------------------");

        System.out.println("\nApplication Terminated.");
    }
}