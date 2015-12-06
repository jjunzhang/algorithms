import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    
    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    public Deque() {
        first = null;
        last = null;
    }
    public boolean isEmpty() {
        if (first == null)
            return true;
        return false;
    }
    public int size() {
        // return the number of items on the deque
        if (isEmpty()) return 0;
        int count = 1;
        Node n = first;
        while (n != last) {
            count++;
            n = n.next;
        }
        return count;
    }
    public void addFirst(Item item) {
        if (item == null) throw new java.lang.NullPointerException();
        Node n = new Node();
        n.item = item;
        if (first != null) {
            n.next = first;
            first.prev = n;
            n.prev = null;
            first = n;
        } else {
            first = n;
            last = n;
            n.prev = null;
            n.next = null;
        }
    }    
    public void addLast(Item item) {
        if (item == null) throw new java.lang.NullPointerException();
        Node n = new Node();
        n.item = item;
        if (last != null) {
            n.prev = last;
            last.next = n;
            n.next = null;
            last = n;
        } else {
            first = n;
            last = n;
            n.prev = null;
            n.next = null;
        }
    }
    public Item removeFirst() {
        if (first == null) throw new java.util.NoSuchElementException();
        Item i = first.item;
        first = first.next;
        if (first != null) {
            first.prev = null;
        } else {
            last = null;
        }
        return i;
    }
    public Item removeLast() {
        if (last == null) throw new java.util.NoSuchElementException();
        Item i = last.item;
        last = last.prev;
        if (last != null) {
            last.next = null;
        } else {
            first = null;
        }
        return i;
    }
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }
    private class DequeIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext() {
            return current != null;
        }
        public Item next() {
            if (current == null) throw new java.util.NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }
    public static void main(String[] args) {
        Deque<Double> deque = new Deque<Double>();
        deque.addFirst(0.6);
        StdOut.println(deque.removeFirst());
    }
}
