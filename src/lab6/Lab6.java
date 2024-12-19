package lab6;

import java.util.*;

public class Lab6 {
    public static void main(String[] args) {
        System.out.println("Лаба6 Любашенко Владислав IС-22 Варiант 14");
        int studentId = 14;
        int C3 = studentId % 3;
        int C2 = studentId % 2;
        System.out.println("C3 = " + C3 + "; C2 = " + C2);

        Sweet caramelSweet = new CaramelSweet("Карамельний бум", 0.1, 2.5, 0, true);
        Sweet hardSweet = new HardSweet("М'ятний бум", 0.15, 1.8, 0, true);
        Sweet jellySweet1 = new JellySweet("Фруктовий бум", 0.2, 3.0, 0, "Полуниця");
        Sweet jellySweet2 = new JellySweet("Елітний Шокожуй", 0.25, 13.0, 99.9, "Шоколад");
        Sweet jellySweet3 = new JellySweet("Шиколяд", 0.26, 3.0, 30.5, "Шоколад");
        Sweet jellySweet4 = new JellySweet("П'яна вишня", 0.69, 7.0, 0.5, "Вишня");

        CustomLinkedList<Sweet> sweetList = new CustomLinkedList<>();
        sweetList.add(caramelSweet);
        sweetList.add(jellySweet1);
        sweetList.add(jellySweet2);
        sweetList.add(jellySweet3);
        sweetList.add(hardSweet);
        sweetList.add(jellySweet4);

        System.out.println("CustomLinkedList пiсля додавання цукерок:");
        System.out.println(sweetList);

        sweetList.remove(2);
        System.out.println("\nCustomLinkedList пiсля видалення елемента з iндексом 2:");
        System.out.println(sweetList);

        System.out.println("\nЕлемент на iндексi 1: " + sweetList.get(1));

        sweetList.clear();
        System.out.println("\nCustomLinkedList пiсля очищення:");
        System.out.println(sweetList);
    }
}

class CustomLinkedList<T extends Sweet> implements List<T> {
    private class Node {
        T data;
        Node next;
        Node prev;

        Node(T data) {
            this.data = data;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public CustomLinkedList() {
        this.size = 0;
    }

    public CustomLinkedList(T item) {
        this.size = 1;
        Node newNode = new Node(item);
        head = tail = newNode;
    }

    public CustomLinkedList(Collection<? extends T> collection) {
        this.size = 0;
        for (T item : collection) {
            add(item);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int index = 0;
        for (Node current = head; current != null; current = current.next) {
            array[index++] = current.data;
        }
        return array;
    }

    @Override
    public boolean add(T t) {
        Node newNode = new Node(t);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (Node current = head; current != null; current = current.next) {
            if (Objects.equals(current.data, o)) {
                unlink(current);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T element : c) {
            add(element);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isModified = false;
        for (Object element : c) {
            isModified |= remove(element);
        }
        return isModified;
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public T get(int index) {
        return getNode(index).data;
    }

    @Override
    public T set(int index, T element) {
        Node node = getNode(index);
        T oldData = node.data;
        node.data = element;
        return oldData;
    }

    @Override
    public T remove(int index) {
        Node node = getNode(index);
        T data = node.data;
        unlink(node);
        return data;
    }

    @Override
    public int indexOf(Object object) {
        int index = 0;
        for (Node current = head; current != null; current = current.next) {
            if (Objects.equals(current.data, object)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    private Node getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    private void unlink(Node node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }
        size--;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (Node current = head; current != null; current = current.next) {
            sb.append(current.data).append(current.next != null ? ", " : "");
        }
        sb.append("]");
        return sb.toString();
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public void add(int index, T element) {
        throw new UnsupportedOperationException("Method skipped");
    }

    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException("Method skipped");
    }

    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Method skipped");
    }

    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException("Method skipped");
    }

    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException("Method skipped");
    }

    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Method skipped");
    }

    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Method skipped");
    }
}

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
        return "назва='" + name + '\''
//                +
//                ", вага=" + weight +
//                ", цiна=" + price +
//                ", вмiст шоколаду=" + cocoaContent
                ;
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

