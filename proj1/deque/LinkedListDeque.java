package deque;

public class LinkedListDeque<T> {

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
        TypeNode currFirst = sentF.next;
        TypeNode newNode = new TypeNode(sentF, x, currFirst);
        sentF.next = newNode;
        currFirst.prev = newNode;
        size += 1;
    }

    public T getFirst() {
        return sentF.next.item;
    }

    public void addLast(T x) {
        TypeNode currNode = sentB.prev;
        TypeNode newNode = new TypeNode(currNode, x, sentB);
        currNode.next = newNode;
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
        } else if (size < index) {
            return null;
        } else {
            TypeNode currNode = sentF.next;
            for (int i = 0; i < index; i++) {
                currNode = currNode.next;
            }
            return currNode.item;
        }
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> L = new LinkedListDeque<>();
        L.addFirst(10);
        L.addLast(15);
        L.addLast(20);
        L.addFirst(5);
        System.out.println(L.get(3));
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
