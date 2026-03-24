// Version 6.1 - Refactored Implementation

import java.util.*;

// Reservation Class
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
}

// Booking Queue (FIFO)
class BookingRequestQueue {
    private Queue<Reservation> queue = new LinkedList<>();

    public void addRequest(Reservation r) {
        queue.offer(r);
    }

    public Reservation getNextRequest() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}

// Inventory Service
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    public void decreaseAvailability(String type) {
        inventory.put(type, inventory.get(type) - 1);
    }

    public void displayInventory() {
        System.out.println("\n=== CURRENT INVENTORY ===");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " → " + entry.getValue());
        }
    }
}

// Booking Service (CORE LOGIC)
class BookingService {

    private RoomInventory inventory;

    // Prevent duplicate IDs
    private Set<String> allocatedRoomIds = new HashSet<>();

    // Track rooms per type
    private Map<String, Set<String>> allocationMap = new HashMap<>();

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void processBookings(BookingRequestQueue queue) {

        System.out.println("\n===== PROCESSING BOOKINGS =====\n");

        while (!queue.isEmpty()) {

            Reservation r = queue.getNextRequest();
            String type = r.getRoomType();

            System.out.println("Processing → " + r.getGuestName());

            // Check availability
            if (inventory.getAvailability(type) > 0) {

                // Generate Unique Room ID
                String roomId = generateRoomId(type);

                // Store allocation
                allocatedRoomIds.add(roomId);

                allocationMap.putIfAbsent(type, new HashSet<>());
                allocationMap.get(type).add(roomId);

                // Update inventory
                inventory.decreaseAvailability(type);

                // Confirm booking
                System.out.println("✅ Booking Confirmed!");
                System.out.println("Guest: " + r.getGuestName());
                System.out.println("Room Type: " + type);
                System.out.println("Room ID: " + roomId);
                System.out.println("-----------------------------------");

            } else {
                System.out.println("❌ Booking Failed (No Availability)");
                System.out.println("-----------------------------------");
            }
        }
    }

    // Unique Room ID Generator
    private String generateRoomId(String type) {
        String id;
        do {
            id = type.substring(0, 2).toUpperCase() + "-" + UUID.randomUUID().toString().substring(0, 4);
        } while (allocatedRoomIds.contains(id));

        return id;
    }

    // Display Allocations
    public void displayAllocations() {
        System.out.println("\n===== FINAL ROOM ALLOCATIONS =====\n");

        for (Map.Entry<String, Set<String>> entry : allocationMap.entrySet()) {
            System.out.println(entry.getKey() + " → " + entry.getValue());
        }
    }
}

// Main Class
public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        // Initialize
        RoomInventory inventory = new RoomInventory();
        BookingRequestQueue queue = new BookingRequestQueue();

        // Add Requests (FIFO Order)
        queue.addRequest(new Reservation("Mithesh", "Single Room"));
        queue.addRequest(new Reservation("John", "Single Room"));
        queue.addRequest(new Reservation("Antonio", "Single Room")); // will fail
        queue.addRequest(new Reservation("Rahul", "Double Room"));

        // Process Bookings
        BookingService service = new BookingService(inventory);
        service.processBookings(queue);

        // Show Results
        service.displayAllocations();
        inventory.displayInventory();

        System.out.println("\nApplication Terminated.");
    }
}

