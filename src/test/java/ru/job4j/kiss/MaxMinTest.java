package ru.job4j.kiss;

import org.checkerframework.framework.qual.DefaultQualifier;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.*;

class MaxMinTest {
    MaxMin maxMin = new MaxMin();
    List<Car> lis = List.of(new Car(1970),
            new Car(2020),
            new Car(1985),
            new Car(2009),
            new Car(2019),
            new Car(1999)
    );
    CarComparator comparator = new CarComparator();

    @Test
    void whenMaxYear() {
        Car expected = maxMin.max(lis, comparator);
        Car result = new Car(2020);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenMinYear() {
        Car expected = maxMin.min(lis, comparator);
        Car result = new Car(1970);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenResultIsNull() {
        Car expected = maxMin.max(new ArrayList<>(), comparator);
        assertThat(expected).isNull();
    }
}