public class LinkedListDeque<T> implements DequeAPI<T> {
    /** 错了，这个就是链表，不是队列！！.*/
    private static class Node<T> {
        public T data;
        public Node<T> prev;
        public Node<T> next;

        public Node(T d, Node<T> p,Node<T> n) {
            data = d;
            prev = p;
            next = n;
        }
    }


    public Node<T> sentinel;
    public int size;


    public LinkedListDeque() {
        sentinel = new Node<>(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }


    public void addFirst(T item) {
        sentinel.next = new Node<>(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }


    public void addLast(T item) {
        sentinel.prev = new Node<>(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;

    }


    public boolean isEmpty() {
        return size == 0;
    }


    public int size() {
        return size;
    }


    public void printDeque() {
        if (sentinel == null) {
            return;
        }
        Node<T> temp = sentinel;
        for (int i=0; i<size; i+=1) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }


    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T removedData = sentinel.next.data;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return removedData;
    }


    public T removeLast() {
        // Do not use "first = first.next" directly,
        // use it on a duplicate node p instead.
        if (size == 0) {
            return null;
        }
        T removedData = sentinel.prev.data;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return removedData;
    }


    public T get(int index) {
        if (index < 0) {
            return null;
        }
        Node<T> pointer = sentinel;
        while (index >= 0) {
            pointer = pointer.next;
            index -= 1;
        }
        return pointer.data;
    }
}
