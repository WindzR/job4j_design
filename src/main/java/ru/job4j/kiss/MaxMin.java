package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class MaxMin {

    public <T> T max(List<T> value, Comparator<T> comparator) {
        Predicate<Integer> predicate = integer -> integer < 0;
        return find(value, comparator, predicate);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        Predicate<Integer> predicate = integer -> integer > 0;
        return find(value, comparator, predicate);
    }

    private <T> T find(List<T> value, Comparator<T> comparator, Predicate<Integer> predicate) {
        if (value.size() == 0) {
            return null;
        }
        T rsl = value.get(0);
        for (T element : value) {
            if (predicate.test(comparator.compare(rsl, element))) {
                rsl = element;
            }
        }
        return rsl;
    }
}
