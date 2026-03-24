// Version 4.1 - Refactored Implementation

import java.util.*;

// Abstract Room Class
abstract class Room {
    protected int beds;
    protected int size;
    protected double price;

    public Room(int beds, int size, double price) {
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public abstract String getRoomType();

    public void displayDetails() {
        System.out.println("Room Type: " + getRoomType());
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sq.ft");
        System.out.println("Price: ₹" + price);
    }
}

// Concrete Room Classes
class SingleRoom extends Room {
    public SingleRoom() {
        super(1, 200,  1500);
    }

    public String getRoomType() {
        return "Single Room";
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super(2, 350, 2500);
    }

    public String getRoomType() {
        return "Double Room";
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super(3, 500, 5000);
    }

    public String getRoomType() {
        return "Suite Room";
    }
}

// Centralized Inventory (Read-Only Usage Here)
class RoomInventory {
    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 0); // Example: unavailable
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public Set<String> getAllRoomTypes() {
        return inventory.keySet();
    }
}

// Search Service (Read-Only)
class RoomSearchService {

    private RoomInventory inventory;

    public RoomSearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void searchAvailableRooms() {

        System.out.println("===== AVAILABLE ROOMS =====\n");

        for (String type : inventory.getAllRoomTypes()) {

            int available = inventory.getAvailability(type);

            // Validation → show only available rooms
            if (available > 0) {

                Room room = createRoom(type);

                if (room != null) {
                    room.displayDetails();
                    System.out.println("Available: " + available);
                    System.out.println("-----------------------------------");
                }
            }
        }
    }

    // Factory Method (maps type → object)
    private Room createRoom(String type) {
        switch (type) {
            case "Single Room":
                return new SingleRoom();
            case "Double Room":
                return new DoubleRoom();
            case "Suite Room":
                return new SuiteRoom();
            default:
                return null;
        }
    }
}

// Main Class
public class UseCase4RoomSearch {

    public static void main(String[] args) {

        // Initialize Inventory
        RoomInventory inventory = new RoomInventory();

        // Initialize Search Service
        RoomSearchService searchService = new RoomSearchService(inventory);

        // Guest performs search
        searchService.searchAvailableRooms();

        System.out.println("\nSearch Completed. No changes made to inventory.");
    }
}