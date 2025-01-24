//-----------------------------------------------------
// Title: CargoPackages Class
// Author: Ege/Yavuz
// ID: 14872032366
// Section: 2
// Assignment: 1
// Description: This class tests the …
//-----------------------------------------------------

public class Queue<T> {
    private DoublyLinkedList<T> list; // Uses DoublyLinkedList as the underlying structure

    // Constructor to initialize the queue
    public Queue() {
        this.list = new DoublyLinkedList<>();
    }

    // Adds an element to the end of the queue
    public void enqueue(T data) {
        list.addLast(data); // Adds the element to the end of the list
    }

    // Removes the front element from the queue and returns its value
    public T dequeue() {
        if (list.size() == 0) return null; // if the queue is empty
        T data = list.head.data; // Accesses the data of the head element
        list.head = list.head.next; // Updates the head to the next element
        if (list.head != null) list.head.prev = null; // Disconnects the old head
        list.size--;
        return data;
    }

    // Returns the size of the queue
    public int size() {
        return list.size();
    }

    // Checks if the queue is empty
    public boolean isEmpty() {
        return list.size() == 0;
    }
}
