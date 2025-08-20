
import java.util.LinkedList;
import java.util.Scanner;

record Place(String name, int distance) {

    @Override
    public String toString() {
        return String.format("%-12s Distance: %d", name, distance);
    }

}

public class LinkedListChallenge {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        LinkedList<Place> placesToVisit = new LinkedList<>();
        addPlace(placesToVisit, new Place("Denver", 100));
        addPlace(placesToVisit, new Place("Austin", 20));
        addPlace(placesToVisit, new Place("Houston", 300));
        addPlace(placesToVisit, new Place("Houston", 300));
        addPlace(placesToVisit, new Place("houston", 300));
        addPlace(placesToVisit, new Place("Detroit", 1000));
        addPlace(placesToVisit, new Place("Kalamazoo", 1050));
        addPlace(placesToVisit, new Place("San Antonio", 50));
        placesToVisit.addFirst(new Place("Dallas", 0));

        System.out.println("\n=========Places-To-Visit=========");
        printList(placesToVisit);

        var iterator = placesToVisit.listIterator();
        boolean quitLoop = false;
        boolean forward = false;

        while (!quitLoop) {
            if (!iterator.hasPrevious()) {
                System.out.println("Originating: " + iterator.next());
                forward = true;
            }
            if (!iterator.hasNext()) {
                System.out.println("Final: " + placesToVisit.getLast());
                forward = false;
            }
            printOptions();
            String choice = scanner.nextLine().trim().toUpperCase();
            switch (choice) {
                case "F" -> {
                    if (!forward) {
                        // Changing direction from backward to forward
                        forward = true;
                        if (iterator.hasNext()) {
                            iterator.next(); // Advance to avoid repeating same item

                        }
                    }

                    if (iterator.hasNext()) {
                        System.out.println(iterator.next());
                    } else {
                        System.out.println("You're at the end of the list.");
                    }
                }
                case "B" -> {
                    if (forward) {
                        // Changing direction from forward to backward
                        forward = false;
                        if (iterator.hasPrevious()) {
                            iterator.previous(); // Step back to avoid repeating

                        }
                    }

                    if (iterator.hasPrevious()) {
                        System.out.println(iterator.previous());
                    } else {
                        System.out.println("You're at the beginning of the list.");
                    }
                }
                case "L" -> {
                    System.out.println("List Places");
                    printList(placesToVisit);
                }
                case "M" ->
                    printOptions();
                case "Q" -> {
                    System.out.println("Quit\nGoodBye!");
                    quitLoop = true;
                }
                default ->
                    System.out.println("Default");
            }

        }

    }

    private static void printOptions() {
        System.out.println("\n======Available Actions======");
        System.out.println("(F)orward");
        System.out.println("(B)ackward");
        System.out.println("(L)ist Places");
        System.out.println("(M)enu");
        System.out.println("(Q)uit");
    }

    private static void printList(LinkedList<Place> list) {
        for (Place place : list) {
            System.out.println(place.toString());
        }
    }

    private static void addPlace(LinkedList<Place> list, Place place) {

        if (list.contains(place)) {
            System.out.println(place.name() + " already exists");
            return;
        }

        for (Place p : list) {
            if (p.name().equalsIgnoreCase(place.name())) {
                System.out.println("Found Duplicate for: " + place.name());
                return;
            }
        }

        int matchedIndex = 0;
        for (var listPLace : list) {
            if (place.distance() < listPLace.distance()) {
                list.add(matchedIndex, place);
                return;
            }
            matchedIndex++;
        }

        list.add(place);

    }

    private static void removePlace(Place place, LinkedList<Place> list) {
        list.remove(place);
        System.out.println(place.name() + " was removed");

    }

    private static Place createPlace(String name, int distance) {
        return new Place(name, distance);
    }

    public static String firstLetterUppercase(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

}
