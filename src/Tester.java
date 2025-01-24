//-----------------------------------------------------
// Title: CargoPackages Class
// Author: Ege/Yavuz
// ID: 14872032366
// Section: 2
// Assignment: 1
// Description: This class tests the …
//-----------------------------------------------------

public class Tester {
    public static void main(String[] args) {
        testDoublyLinkedList();
    }

    public static void testDoublyLinkedList() {
        System.out.println("Testing DoublyLinkedList...");
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);

        System.out.println("Size after adding elements: " + list.size());

        // Use get instead of getNodeData
        System.out.println("Element at index 1: " + list.get(1));

        list.removeLast();
        System.out.println("Size after removing last element: " + list.size());
    }
}
