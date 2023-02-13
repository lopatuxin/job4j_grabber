package ru.job4j.storage.store;

import org.junit.jupiter.api.Test;
import ru.job4j.storage.model.Food;
import ru.job4j.storage.tools.SimplePercentCalculator;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class ShopTest {
    @Test
    void whenIsSuitable() {
        SimplePercentCalculator simplePercentCalculator = new SimplePercentCalculator();
        Store shop = new Shop(simplePercentCalculator);
        Food milk = new Food(
                "Milk",
                100,
                50,
                LocalDate.of(2023, 02, 25),
                LocalDate.of(2023, 01, 01)
        );
        assertThat(shop.add(milk)).isTrue();
    }
}