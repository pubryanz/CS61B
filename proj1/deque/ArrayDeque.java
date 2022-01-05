package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
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

//    public boolean isEmpty() {
//        return size == 0;
//    }

    public void printDeque() {
        if (size == 0) {
            System.out.println("");
        } else if (nextLast >= nextFirst) {
            for (int i = 0; i < size; i += 1) {
                System.out.print(items[nextFirst + i] + " ");
            }
            System.out.println("");
        } else {
            for (int i = 0; i < size; i += 1) {
                if (i + nextFirst + 1 < items.length) {
                    System.out.print(items[nextFirst + 1 + i] + " ");
                } else {
                    System.out.print(items[i - (items.length - nextFirst - 1)] + " ");
                }
            }
            System.out.println("");
        }
    }

    private void shrinkSize() {
        /* remove half of the array if
         1) usage ratio < 0.25 and
         2) array length > 8 */
        double r = (float)size / items.length;
        if (r < 0.25 && items.length > 16) {
            T[] temp = (T[]) new Object[items.length / 2];
            if (nextFirst < nextLast) {
                System.arraycopy(items, nextFirst, temp, 0, size + 1);
            } else {
                System.arraycopy(items, nextFirst, temp, 0, items.length-nextFirst);
                System.arraycopy(items, 0, temp, items.length-nextFirst, size-(items.length-nextFirst)+1);
            }
            items = temp;
            nextFirst = 0;
            nextLast = size + 1;
        }
    }

    public T removeFirst() {
        T returnItem = null;
        if (size == 0) {
            return null;
        } else if (nextFirst + 1 != items.length) {
            returnItem = items[nextFirst + 1];
            items[nextFirst + 1] = null;
            nextFirst += 1;
        } else {
            returnItem = items[0];
            items[0] = null;
            nextFirst = 0;
        }
        size -= 1;
        shrinkSize();
        return returnItem;
    }

    public T removeLast() {
        T returnItem = null;
        if (size == 0) {
            return null;
        } else if (nextLast != 0) {
            returnItem = items[nextLast - 1];
            items[nextLast - 1] = null;
            nextLast -= 1;
        } else {
            returnItem = items[items.length - 1];
            items[items.length - 1] = null;
            nextLast = items.length - 1;
        }
        size -= 1;
        shrinkSize();
        return returnItem;
    }

    public T get(int index) {
        if (size == 0 || index >= size) {
            return null;
        } else if (index + nextFirst + 1 < items.length) {
            return items[index + nextFirst + 1];
        } else {
            return items[index + nextFirst + 1 - items.length];
        }
    }

    public Iterator<T> iterator() {
        return new ADequeIterator();
    }

    private class ADequeIterator implements Iterator<T> {
        private int wizPos;
        private ADequeIterator() {
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
        if (!(o instanceof ArrayDeque)) {
            return false;
        }

        ArrayDeque<T> temp = (ArrayDeque<T>) o;
        if (temp.size != size) {
            return false;
        }
        for (int i = 0; i < size; i += 1) {
            if (get(i) != temp.get(i)) {
                return false;
            }
        }
        return true;
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
//        for (int s : L) {
//            System.out.print(s + " ");
//        }
        /* test removeFirst */
//        int a1 = L.removeFirst();
//        int a2 = L.removeFirst();
//        int a3 = L.removeFirst();
//        int a4 = L.removeFirst();
//        int a5 = L.removeFirst();
//        int a6 = L.removeFirst();
//        int a7 = L.removeFirst();
//        int a8 = L.removeFirst();
//        int a9 = L.removeFirst();
//        int a10 = L.removeFirst();
//        int a11 = L.removeFirst();
//        int a12 = L.removeFirst();
//        int a13 = L.removeFirst();
        /* test removeLast */
//        int a1 = L.removeLast();
//        int a2 = L.removeLast();
//        int a3 = L.removeLast();
//        int a4 = L.removeLast();
//        int a5 = L.removeLast();
//        int a6 = L.removeLast();
//        int a7 = L.removeLast();
//        int a8 = L.removeLast();
//        int a9 = L.removeLast();
//        int a10 = L.removeLast();
//        int a11 = L.removeLast();
//        int a12 = L.removeLast();
//        int a13 = L.removeLast();
//        L.addFirst(18);
//        L.addFirst(19);
//        L.addFirst(20);
//        L.addFirst(21);
//        L.addFirst(22);
    }
}
