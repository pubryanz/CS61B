package deque;

public class ArrayDeque<T> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] items;

    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[8];
        nextFirst = items.length / 2 - 1;
        nextLast = items.length / 2;
    }

    private void resize() {
        T[] a = (T[]) new Object[size * 2];
        if (nextFirst < nextLast) {
            System.arraycopy(items, nextFirst + 1, a, nextFirst + 1 + size, size - nextFirst - 1);
            System.arraycopy(items, 0, a, 0, nextFirst + 1);
            items = a;
            nextFirst = nextFirst + size;
        } else {
            System.arraycopy(items, 0, a, size, size);
            items = a;
            nextFirst = size - 1;
        }
    }

    public void addFirst(T x) {
        if (size == items.length) {
            resize();
        }

        items[nextFirst] = x;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst = nextFirst - 1;
        }
        size += 1;
    }

    public void addLast(T x) {
        if (size == items.length) {
            resize();
        }
        items[nextLast] = x;
        if (nextLast == items.length - 1) {
            nextLast = 0;
        } else {
            nextLast = nextLast + 1;
        }
        size += 1;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> L = new ArrayDeque<>();
        L.addFirst(1);
        L.addFirst(2);
        L.addLast(3);
        L.addLast(4);
        L.addFirst(5);
        L.addLast(6);
        L.addLast(7);
        L.addFirst(8);
        L.addFirst(9);
        L.addLast(10);
        L.addLast(11);
        L.addFirst(12);
        L.addFirst(13);
        L.addLast(14);
        L.addLast(15);
        L.addFirst(16);
        L.addLast(17);
    }
}
