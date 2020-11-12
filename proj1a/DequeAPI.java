public interface DequeAPI<T> {
    /** Add item in the front. */
    void addFirst(T item);

    /** Add item in the end. */
    void addLast(T item);

    /** Check is the deque empty. */
    boolean isEmpty();

    /** Return the size of the deque. */
    int size();

    /** Print the entire deque. */
    void printDeque();

    /** Remove the first item of the deque. */
    T removeFirst();

    /** Remove the last item of the deque. */
    T removeLast();

    /** Get the index-th item of the deque. */
    T get(int index);

}
