package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T> {

    /* nested class for TypeNode, the node can be for any type. */
    private class TypeNode {
        public TypeNode prev;
        public T item;
        public TypeNode next;

        /* constructor */
        public TypeNode(TypeNode p, T x, TypeNode n) {
            prev = p;
            item = x;
            next = n;
        }
    }

    private TypeNode sentF;
    private TypeNode sentB;
    private int size;

    /* empty list */
    public LinkedListDeque() {
        sentF = new TypeNode(null, null, null);
        sentB = new TypeNode(null, null, null);
        sentF.next = sentB;
        sentB.prev = sentF;
        size = 0;
    }

    public void addFirst(T x) {
//        /*method 1: use a new node currNode for switching */
//        TypeNode currFirst = sentF.next;
//        TypeNode newNode = new TypeNode(sentF, x, currFirst);
//        sentF.next = newNode;
//        currFirst.prev = newNode;
//        size += 1;
        /* method 2: no need to generate a new TypeNode just for switch */
        TypeNode newNode = new TypeNode(sentF, x, sentF.next);
        sentF.next.prev = newNode;
        sentF.next = newNode;
        size += 1;
    }

    public T getFirst() {
        return sentF.next.item;
    }

    public void addLast(T x) {
//        /*method 1: use a currNode for switching */
//        TypeNode currNode = sentB.prev;
//        TypeNode newNode = new TypeNode(currNode, x, sentB);
//        currNode.next = newNode;
//        sentB.prev = newNode;
//        size += 1;
        /*Method 2: no need for a currNode */
        TypeNode newNode = new TypeNode(sentB.prev, x, sentB);
        sentB.prev.next = newNode;
        sentB.prev = newNode;
        size += 1;
    }

    public T getLast() {
        return sentB.prev.item;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        TypeNode currNode = sentF.next;
        for (int i = 0; i < size; i++) {
            System.out.print(currNode.item + " ");
            currNode = currNode.next;
        }
        System.out.println("");
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            TypeNode currFirst = sentF.next;
            sentF.next = currFirst.next;
            currFirst.next.prev = sentF;
            currFirst.next = null;
            currFirst.prev = null;
            size = size - 1;
            return currFirst.item;
        }
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            TypeNode currLast = sentB.prev;
            sentB.prev = currLast.prev;
            currLast.prev.next = currLast.next;
            currLast.prev = null;
            currLast.next = null;
            size = size - 1;
            return currLast.item;
        }
    }

    public T get(int index) {
        if (size == 0) {
            return null;
        } else if (size <= index) {
            return null;
        } else {
            TypeNode currNode = sentF.next; /* Node at position 0 */
            for (int i = 0; i < index; i++) {
                currNode = currNode.next;
            }
            return currNode.item;
        }
    }

    public Iterator<T> iterator() {
        return new LLDequeIterator();
    }

    private class LLDequeIterator implements Iterator<T> {
        private int wizPos;
        private LLDequeIterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }

        public T next() {
            T returnItem = get(wizPos);
            wizPos += 1;
            return returnItem;
        }
    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof LinkedListDeque)) {
            return false;
        }

        LinkedListDeque<T> copyO = (LinkedListDeque<T>) o; /*change o type to LinkedListDeque */
        if (copyO.size() != size) {
            return false;
        }

        for (int i = 0; i < size; i += 1) {
            if (get(i) != copyO.get(i)) {
                return false;
            }
        }
        return true;
    }

    public T getRecursive(int index) {
        if (size == 0) {
            return null;
        } else if (index >= size) {
            return null;
        } else {
            return getRecursiveHelper(index, sentF.next);
        }
    }

    private T getRecursiveHelper(int index, TypeNode t) {
        if (index == 0) {
            return t.item;
        }
        return getRecursiveHelper(index - 1, t.next);
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> L = new LinkedListDeque<>();
//        L.printDeque();
        L.addFirst(10);
        L.addLast(15);
        L.addLast(20);
        L.addFirst(5);
        System.out.println(L.getRecursive(2));
//        for (int s : L) {
//            System.out.println(s);
//        }
        LinkedListDeque<Integer> L1 = new LinkedListDeque<>();
//        L.printDeque();
        L1.addFirst(10);
        L1.addLast(15);
        L1.addLast(20);
//        L1.addFirst(5);
//        System.out.println(L.equals(L1));
//        System.out.println(L.get(4));
//        System.out.println(L.getFirst());
//        System.out.println(L.getLast());
//        System.out.println(L.size());
//        System.out.println(L.isEmpty());
//        L.printDeque();
//        System.out.println(L.removeFirst());
//        System.out.println(L.size());
//        System.out.println(L.getFirst());
//        System.out.println(L.getLast());
//        L.printDeque();
//        System.out.println(L.removeLast());
//        System.out.println(L.size());
//        System.out.println(L.getFirst());
//        System.out.println(L.getLast());
//        L.printDeque();
    }

}
