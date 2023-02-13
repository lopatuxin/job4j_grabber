package ru.job4j.storage.store;

import org.junit.jupiter.api.Test;
import ru.job4j.storage.model.Food;
import ru.job4j.storage.tools.SimplePercentCalculator;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class TrashTest {
    @Test
    void whenIsSuitable() {
        SimplePercentCalculator simplePercentCalculator = new SimplePercentCalculator();
        Store trash = new Trash(simplePercentCalculator);
        Food apple = new Food(
                "Apple",
                125,
                50,
                LocalDate.of(2023, 01, 25),
                LocalDate.of(2023, 01, 9)
        );
        assertThat(trash.add(apple)).isTrue();
    }
}