package lab5;

import java.util.*;

/**
 * Main class for the Lab5 program.
 * Demonstrates the use of various sweet types and operations on a gift box.
 */
public class Lab5 {
    /**
     * Main method of the program.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        System.out.println("Лаба5 Любашенко Владислав IС-22 Варiант 14");
        int studentId = 14;
        int C13 = studentId % 13;
        System.out.println("C13 = " + C13);

        // Create a GiftBox object
        GiftBox giftBox = new GiftBox();

        // Add sweets to the gift box
        giftBox.addSweet(new CaramelSweet("Карамельний бум", 0.1, 2.5, 0, true));
        giftBox.addSweet(new JellySweet("Фруктовий бум", 0.2, 3.0, 0, "Полуниця"));
        giftBox.addSweet(new JellySweet("Елітний Шокожуй", 0.25, 13.0, 99.9, "Шоколад"));
        giftBox.addSweet(new JellySweet("Шиколяд", 0.26, 3.0, 30.5, "Шоколад"));
        giftBox.addSweet(new HardSweet("М'ятний бум", 0.15, 1.8, 0, true));
        giftBox.addSweet(new JellySweet("П'яна вишня", 0.69, 7.0, 0.5, "Вишня"));

        // Calculate and print the total weight of the gift box
        System.out.println("Вага подарункового боксу: " + giftBox.calculateTotalWeight());

        // Sort sweets by price and print the sorted list
        giftBox.sortSweetsByPrice();
        System.out.println("\nСортованi за цiною цукерки: " + giftBox);

        // Find and print sweets within a specific cocoa content range
        double minCocoa = 0.1;
        double maxCocoa = 90.0;
        List<Sweet> sweetsInRange = giftBox.findSweetsByCocoaContentRange(minCocoa, maxCocoa);
        System.out.println("\nЦукерки з вмiстом шоколаду вiд " + minCocoa + " до " + maxCocoa + ": " + sweetsInRange);
    }
}

/**
 * Abstract class representing a general sweet.
 */
abstract class Sweet {
    private String name;
    private double weight;
    private double price;
    private double cocoaContent;

    /**
     * Constructor for creating a Sweet object.
     *
     * @param name         Name of the sweet.
     * @param weight       Weight of the sweet in kilograms.
     * @param price        Price of the sweet.
     * @param cocoaContent Cocoa content percentage.
     */
    public Sweet(String name, double weight, double price, double cocoaContent) {
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.cocoaContent = cocoaContent;
    }

    /**
     * @return Name of the sweet.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Weight of the sweet in kilograms.
     */
    public double getWeight() {
        return weight;
    }

    /**
     * @return Price of the sweet.
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return Cocoa content percentage.
     */
    public double getCocoaContent() {
        return cocoaContent;
    }

    @Override
    public String toString() {
        return "назва='" + name + '\'' +
                ", вага=" + weight +
                ", цiна=" + price +
                ", вмiст шоколаду=" + cocoaContent;
    }
}

/**
 * Represents a caramel sweet.
 */
class CaramelSweet extends Sweet {
    private boolean hasFilling;

    /**
     * Constructor for creating a CaramelSweet object.
     *
     * @param name         Name of the caramel sweet.
     * @param weight       Weight of the sweet in kilograms.
     * @param price        Price of the sweet.
     * @param cocoaContent Cocoa content percentage.
     * @param hasFilling   Indicates if the sweet has a filling.
     */
    public CaramelSweet(String name, double weight, double price, double cocoaContent, boolean hasFilling) {
        super(name, weight, price, cocoaContent);
        this.hasFilling = hasFilling;
    }

    /**
     * @return True if the sweet has a filling, false otherwise.
     */
    public boolean hasFilling() {
        return hasFilling;
    }

    @Override
    public String toString() {
        return "Карамелька{" +
                "ЄЗаповнення=" + hasFilling +
                ", " + super.toString() +
                '}';
    }
}

/**
 * Represents a jelly sweet.
 */
class JellySweet extends Sweet {
    private String flavor;

    /**
     * Constructor for creating a JellySweet object.
     *
     * @param name         Name of the jelly sweet.
     * @param weight       Weight of the sweet in kilograms.
     * @param price        Price of the sweet.
     * @param cocoaContent Cocoa content percentage.
     * @param flavor       Flavor of the sweet.
     */
    public JellySweet(String name, double weight, double price, double cocoaContent, String flavor) {
        super(name, weight, price, cocoaContent);
        this.flavor = flavor;
    }

    /**
     * @return Flavor of the sweet.
     */
    public String getFlavor() {
        return flavor;
    }

    @Override
    public String toString() {
        return "Желейка{" +
                "смак='" + flavor + '\'' +
                ", " + super.toString() +
                '}';
    }
}

/**
 * Represents a hard candy sweet.
 */
class HardSweet extends Sweet {
    private boolean isMinty;

    /**
     * Constructor for creating a HardSweet object.
     *
     * @param name         Name of the hard sweet.
     * @param weight       Weight of the sweet in kilograms.
     * @param price        Price of the sweet.
     * @param cocoaContent Cocoa content percentage.
     * @param isMinty      Indicates if the sweet is minty.
     */
    public HardSweet(String name, double weight, double price, double cocoaContent, boolean isMinty) {
        super(name, weight, price, cocoaContent);
        this.isMinty = isMinty;
    }

    /**
     * @return True if the sweet is minty, false otherwise.
     */
    public boolean isMinty() {
        return isMinty;
    }

    @Override
    public String toString() {
        return "Льодяник{" +
                "чиМ'ятний=" + isMinty +
                ", " + super.toString() +
                '}';
    }
}

/**
 * Represents a gift box containing sweets.
 */
class GiftBox {
    private List<Sweet> sweets;

    /**
     * Constructor for creating an empty GiftBox.
     */
    public GiftBox() {
        sweets = new ArrayList<>();
    }

    /**
     * Adds a sweet to the gift box.
     *
     * @param sweet The sweet to be added.
     */
    public void addSweet(Sweet sweet) {
        sweets.add(sweet);
    }

    /**
     * Calculates the total weight of all sweets in the gift box.
     *
     * @return Total weight of the sweets in kilograms.
     */
    public double calculateTotalWeight() {
        return sweets.stream().mapToDouble(Sweet::getWeight).sum();
    }

    /**
     * Sorts the sweets in the gift box by price in ascending order.
     */
    public void sortSweetsByPrice() {
        sweets.sort(Comparator.comparingDouble(Sweet::getPrice));
    }

    /**
     * Finds sweets within a specified cocoa content range.
     *
     * @param minCocoa Minimum cocoa content percentage.
     * @param maxCocoa Maximum cocoa content percentage.
     * @return A list of sweets within the specified range.
     */
    public List<Sweet> findSweetsByCocoaContentRange(double minCocoa, double maxCocoa) {
        List<Sweet> result = new ArrayList<>();
        for (Sweet sweet : sweets) {
            if (sweet.getCocoaContent() >= minCocoa && sweet.getCocoaContent() <= maxCocoa) {
                result.add(sweet);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "ГiфтБокс{" +
                "Цукерки=" + sweets +
                '}';
    }
}
