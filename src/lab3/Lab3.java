package lab3;

import java.util.*;

/**
 * The {@code Ship} class represents a ship with attributes such as
 * name, number of cannons, captain's name, type, displacement, and maximum speed.
 */
class Ship {
    private String name;
    private int cannons;
    private String captain;
    private String type;
    private double displacement; // displacement in tons
    private int maxSpeed; // maximum speed in knots

    /**
     * Constructs a {@code Ship} object with the specified parameters.
     *
     * @param name         the name of the ship
     * @param cannons      the number of cannons on the ship
     * @param captain      the name of the ship's captain
     * @param type         the type of the ship (e.g., Frigate, Galleon)
     * @param displacement the displacement of the ship in tons
     * @param maxSpeed     the maximum speed of the ship in knots
     */
    public Ship(String name, int cannons, String captain, String type, double displacement, int maxSpeed) {
        this.name = name;
        this.cannons = cannons;
        this.captain = captain;
        this.type = type;
        this.displacement = displacement;
        this.maxSpeed = maxSpeed;
    }

    /**
     * Returns the name of the ship.
     *
     * @return the name of the ship
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the number of cannons on the ship.
     *
     * @return the number of cannons
     */
    public int getCannons() {
        return cannons;
    }

    /**
     * Returns the name of the ship's captain.
     *
     * @return the captain's name
     */
    public String getCaptain() {
        return captain;
    }

    /**
     * Returns the type of the ship.
     *
     * @return the ship's type
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the displacement of the ship in tons.
     *
     * @return the ship's displacement
     */
    public double getDisplacement() {
        return displacement;
    }

    /**
     * Returns the maximum speed of the ship in knots.
     *
     * @return the ship's maximum speed
     */
    public int getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * Returns a string representation of the {@code Ship} object.
     *
     * @return a formatted string describing the ship
     */
    @Override
    public String toString() {
        return String.format("Ship{name='%s', cannons=%d, captain='%s', type='%s', displacement=%.1f, maxSpeed=%d}",
                name, cannons, captain, type, displacement, maxSpeed);
    }

    /**
     * Compares this ship to the specified object for equality.
     *
     * @param obj the object to compare with
     * @return {@code true} if the specified object is equal to this ship; {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ship ship = (Ship) obj;
        return cannons == ship.cannons &&
                Double.compare(ship.displacement, displacement) == 0 &&
                maxSpeed == ship.maxSpeed &&
                Objects.equals(name, ship.name) &&
                Objects.equals(captain, ship.captain) &&
                Objects.equals(type, ship.type);
    }

    /**
     * Returns a hash code value for the ship.
     *
     * @return a hash code value for this ship
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, cannons, captain, type, displacement, maxSpeed);
    }
}

/**
 * The {@code Lab3} class contains the main method for creating, sorting,
 * and searching an array of {@code Ship} objects.
 */
public class Lab3 {
    /**
     * The main method executes the program.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            System.out.println("Лаба3 Любашенко Владислав IС-22 Варiант 14");
            int studentId = 14;
            int C11 = studentId % 11;
            System.out.println("C11 = " + C11);

            // Creating an array of ships
            Ship[] ships = {
                    new Ship("Black Pearl", 32, "Jack Sparrow", "Frigate", 500.0, 20),
                    new Ship("Queen Anne's Revenge", 40, "Blackbeard", "Frigate", 300.0, 17),
                    new Ship("Flying Dutchman", 46, "Davy Jones", "Galleon", 600.0, 20),
                    new Ship("Dying Gull", 2, "Jack Sparrow", "Sloop", 150.0, 13),
                    new Ship("HMS Endeavour", 64, "Cutler Beckett", "Ship of the Line", 1000.0, 15),
                    new Ship("HMS Interceptor", 28, "James Norrington", "Sloop-of-War", 200.0, 20),
                    new Ship("HMS Dauntless", 100, "James Norrington", "Ship of the Line", 1500.0, 13),
                    new Ship("Empress", 50, "Xiao Feng", "Junk", 700.0, 17),
                    new Ship("Silent Mary", 80, "Armando Salazar", "Frigate", 1200.0, 13),
                    new Ship("Wicked Wench", 32, "Jack Sparrow", "Frigate", 500.0, 20)
            };

            // Sorting ships by the number of max speed (ascending) and cannons (descending)
            Arrays.sort(ships, Comparator.comparingInt(Ship::getMaxSpeed)
                    .thenComparing(Comparator.comparingInt(Ship::getCannons).reversed()));

            System.out.println("\nПосортованi кораблi за зростанням швидкостi, а при рiвностi за спаданням кiлькостi гармат:");
            for (Ship ship : ships) {
                System.out.println(ship);
            }

            // Searching for a specific ship
            Ship targetShip = new Ship("Flying Dutchman", 46, "Davy Jones", "Galleon", 600.0, 20);
            int index = Arrays.asList(ships).indexOf(targetShip);

            if (index != -1) {
                System.out.println("\nЗнайдений корабель: " + ships[index]);
            } else {
                System.out.println("\nТакий корабель не знайдено");
            }

        } catch (Exception e) {
            System.err.println("Помилка: " + e.getMessage());
        }
    }
}





