//-----------------------------------------------------
// Title: CargoPackages Class
// Author: Ege/Yavuz
// ID: 14872032366
// Section: 2
// Assignment: 1
// Description: This class tests the …
//-----------------------------------------------------

public class Stack<T> {
    private DoublyLinkedList<T> list; // Uses DoublyLinkedList as the underlying structure

    // Constructor to initialize the stack
    public Stack() {
        this.list = new DoublyLinkedList<>();
    }

    // Pushes an element onto the stack
    public void push(T data) {
        list.addLast(data); // Adds the element to the end of the list
    }

    // Removes the top element from the stack and returns its value
    public T pop() {
        return list.removeLast(); // Removes and returns the last element in the list
    }

    // Returns the size of the stack
    public int size() {
        return list.size();
    }

    // Checks if the stack is empty
    public boolean isEmpty() {
        return list.size() == 0;
    }
}
