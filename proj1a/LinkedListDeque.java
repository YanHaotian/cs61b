public class LinkedListDeque<T> implements DequeAPI<T> {
    /** One single node of the linked list deque.*/
    private static class Node<T> {
        public T data;
        public Node<T> next;

        public Node(T d, Node<T> n) {
            data = d;
            next = n;
        }
    }


    public Node<T> first;
    public int size;

    public LinkedListDeque() {
        first = null;
        size = 0;
    }


    public void addFirst(T item) {
        first = new Node<>(item, first);
        size += 1;
    }

    public void addLast(T item) {
        Node<T> p = first;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new Node<>(item, null);
        // first = p; why???
        size += 1;

    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (first == null) {
            return;
        }

        Node<T> temp = first;
        for (int i=0; i<size; i+=1) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public T removeFirst() {
        T removedData = first.data;
        first = first.next;
        size -= 1;
        return removedData;
    }


    private Node<T> getLastNode() {
        Node<T> p = first;
        while (p.next != null) {
            p = p.next;
        }
        return p;
    }

    public T removeLast() {
        Node<T> back = getLastNode();

        // Do not use "first = first.next" directly,
        // use it on a duplicate node p instead.
        Node<T> p = first;
        while (p.next != back) {
            p = p.next;
        }
        p.next = null;
        size -= 1;
        return back.data;
    }

    public T get(int index) {
        Node<T> p = first;
        while (index > 0) {
            p = p.next;
            index -= 1;
        }
        return p.data;
    }
}
