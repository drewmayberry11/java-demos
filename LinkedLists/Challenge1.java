import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Challenge 1 — Train Car Manager
 *
 * Description:
 * You are modeling a train with cars attached in order.
 * Each car is represented by a string (e.g., "Engine", "Cargo", "Passenger").
 *
 * Requirements:
 * 1. Start with an empty train.
 * 2. Add cars to the front or back (simulate attaching cars).
 * 3. Remove the first and last car safely (no crashes if train is empty).
 * 4. Print the train in order like:
 * Engine -> Cargo -> Passenger -> null
 *
 * Hints:
 * - Stack-like operations: use push/offerFirst for attaching at the front.
 * - Queue-like operations: use offerLast/poll for attaching/removing at ends.
 * - Use safe removal (poll) since it returns null instead of throwing
 * exceptions.
 *
 * Example:
 * Empty Train
 * Engine -> Cargo -> Passenger -> null
 * Engine was removed
 * Passenger was removed
 * Cargo -> null
 * No car was removed
 * No car was removed
 */
public class Challenge1 {

    public static void main(String[] args) {
        LinkedList<String> cars = new LinkedList<>();

        // Start with an empty train
        System.out.println("Empty Train");
        printCars(cars); // should just print: null

        // Attach cars in order
        attachFront(cars, "Engine"); // Engine is the front of the train
        attachBack(cars, "Cargo"); // add cargo behind the engine
        attachBack(cars, "Passenger"); // add passenger car at the end

        // Print current state of the train
        printCars(cars); // Engine -> Cargo -> Passenger -> null

        // Remove cars safely
        printRemovedCar(removeFront(cars)); // remove from front (Engine)
        printRemovedCar(removeBack(cars)); // remove from back (Passenger)

        // Print after removals
        printCars(cars); // should now be Cargo -> null

        // Clear everything (empty train again)
        cars.clear();

        // Try removing from empty train (should handle safely)
        printRemovedCar(removeFront(cars));
        printRemovedCar(removeBack(cars));
    }

    // ===============================
    // Attach Operations
    // ===============================

    // Attach a car to the very front of the train
    public static void attachFront(LinkedList<String> cars, String car) {
        cars.offerFirst(car); // safe: won't throw if train is empty
    }

    // Attach a car to the very back of the train
    public static void attachBack(LinkedList<String> cars, String car) {
        cars.offerLast(car);
    }

    // ===============================
    // Remove Operations
    // ===============================

    // Remove and return the last car in the train
    // If train is empty, returns null
    public static String removeBack(LinkedList<String> cars) {
        return cars.pollLast();
    }

    // Remove and return the first car in the train
    // If train is empty, returns null
    public static String removeFront(LinkedList<String> cars) {
        return cars.pollFirst();
    }

    // ===============================
    // Printing Helpers
    // ===============================

    // Prints what car was removed, or a safe message if nothing was removed
    public static void printRemovedCar(String car) {
        if (car == null) {
            System.out.println("No car was removed");
        } else {
            System.out.println(car + " was removed");
        }
    }

    // Print the full train in the format: Car1 -> Car2 -> ... -> null
    public static void printCars(LinkedList<String> cars) {
        if (cars.isEmpty()) {
            System.out.println("null");
            return;
        }

        ListIterator<String> it = cars.listIterator();
        boolean first = true;

        // Traverse all cars in order
        while (it.hasNext()) {
            if (!first)
                System.out.print(" -> "); // add arrow between cars
            System.out.print(it.next());
            first = false;
        }

        System.out.println(" -> null"); // mark the end of the train
        System.out.println(); // extra line for readability
    }
}
