// Version 3.1 - Refactored Implementation

import java.util.HashMap;
import java.util.Map;

// Room Inventory Class (Centralized State Management)
class RoomInventory {

    private Map<String, Integer> inventory;

    // Constructor - Initialize inventory
    public RoomInventory() {
        inventory = new HashMap<>();

        // Register room types with availability
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    // Get availability of a specific room
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability (controlled method)
    public void updateAvailability(String roomType, int count) {
        if (inventory.containsKey(roomType)) {
            inventory.put(roomType, count);
        } else {
            System.out.println("Room type not found!");
        }
    }

    // Display full inventory
    public void displayInventory() {
        System.out.println("===== CENTRALIZED ROOM INVENTORY =====\n");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println("Room Type: " + entry.getKey());
            System.out.println("Available: " + entry.getValue());
            System.out.println("-----------------------------------");
        }
    }
}

// Main Class
public class UseCase3InventorySetup {

    public static void main(String[] args) {

        // Initialize Inventory
        RoomInventory inventory = new RoomInventory();

        // Display initial state
        inventory.displayInventory();

        // Example update
        System.out.println("\nUpdating Single Room availability...\n");
        inventory.updateAvailability("Single Room", 4);

        // Display updated state
        inventory.displayInventory();

        System.out.println("\nApplication Terminated.");
    }
}