package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        Predicate<Integer> predicate = t -> t < 0;
        return cycle(value, predicate, comparator);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        Predicate<Integer> predicate = t -> t > 0;
        return cycle(value, predicate, comparator);
    }

    private <T> T cycle(List<T> value, Predicate predicate, Comparator<T> comparator) {
        if (value.isEmpty()) {
            return null;
        }
        T result = value.get(0);
        for (T t : value) {
            int num = comparator.compare(result, t);
            if (predicate.test(num)) {
                result = t;
            }
        }
        return result;
    }
}