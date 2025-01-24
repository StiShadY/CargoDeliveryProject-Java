//-----------------------------------------------------
// Title: CargoPackages Class
// Author: Ege/Yavuz
// ID: 14872032366
// Section: 2
// Assignment: 1
// Description: Implements a generic doubly linked list data structure to store elements in a sequence,
// allowing both forward and backward traversal.
// This class is used in the Vehicle class to store its cargo packages.
//-----------------------------------------------------

public class DoublyLinkedList<T> {
    public class Node {
        T data;
        Node prev;
        Node next;

        public Node(T data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    public Node head;
    public Node tail;
    public int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // Adds an element to the end of the list
    public void addLast(T data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    // Removes the last element from the list and returns its data
    public T removeLast() {
        if (tail == null) return null;

        T data = tail.data;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return data;
    }

    // Returns the data at a specific index
    public T get(int index) { // updated method name to get
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    // Removes the node at a specific index and returns its data
    public T removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        // Remove the node and adjust links
        if (current.prev != null) {
            current.prev.next = current.next;
        } else {
            head = current.next; // if removing the head
        }

        if (current.next != null) {
            current.next.prev = current.prev;
        } else {
            tail = current.prev; // if removing the tail
        }

        size--;
        return current.data;
    }

    // Returns the size of the list
    public int size() {
        return size;
    }
}
