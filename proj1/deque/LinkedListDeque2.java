package deque;

public class LinkedListDeque2<T> {
    public class TypeNode {
        public TypeNode prev;
        public T item;
        public TypeNode next;

        public TypeNode(TypeNode p, T x, TypeNode n) {
            prev = p;
            item = x;
            next = n;
        }
    }

    private TypeNode sentinel;
    private int size;

    public LinkedListDeque2() {
        sentinel = new TypeNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T x) {
        TypeNode currFirst = sentinel.next;
        TypeNode newNode = new TypeNode(sentinel, x, currFirst);
        sentinel.next = newNode;
        currFirst.prev = newNode;
        size += 1;
    }

    public T getFirst() {
        return sentinel.next.item;
    }

    public void addLast(T x) {
        TypeNode currLast = sentinel.prev;
        TypeNode newNode = new TypeNode(currLast, x, sentinel);
        sentinel.prev = newNode;
        currLast.next = newNode;
        size += 1;
    }

    public T getLast() {
        return sentinel.prev.item;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        TypeNode pointerNode = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(pointerNode.item + " ");
            pointerNode = pointerNode.next;
        }
        System.out.println(" ");
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T removeFirst() {
        TypeNode firstNode = sentinel.next;
        if (size == 0) {
            return null;
        } else {
            sentinel.next = firstNode.next;
            firstNode.next.prev = sentinel;
            firstNode.prev = null;
            firstNode.next = null;
            size -= 1;
        }
        return firstNode.item;
    }

    public T removeLast() {
        TypeNode lastNode = sentinel.prev;
        if (size == 0) {
            return null;
        } else {
            sentinel.prev = lastNode.prev;
            lastNode.prev.next = sentinel;
            lastNode.prev = null;
            lastNode.next = null;
            size -= 1;
        }
        return lastNode.item;
    }

    public T getIndex(int index) {
        if (index >= size) {
            return null;
        } else {
            TypeNode pointerNode = sentinel;
            for (int i = 0; i <= index; i++) {
                pointerNode = pointerNode.next;
            }
            return pointerNode.item;
        }

    }

    public static void main(String[] args) {
        LinkedListDeque2<Integer> L = new LinkedListDeque2<>();
        L.addFirst(10);
        L.addFirst(5);
        L.addLast(15);
        L.addLast(20);
        L.printDeque();
        System.out.println(L.getIndex(4));
    }
}
