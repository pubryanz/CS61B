package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private final Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        comparator = c;
    }

    public T max() {
        return max(comparator);
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }

        int maxInd = 0;
        T returnItem = get(0);
        for (int i = 0; i < size(); i += 1) {
            if (c.compare(get(i), get(maxInd)) > 0) {
                returnItem = get(i);
                maxInd = i;
            }
        }
        return returnItem;
    }
}
