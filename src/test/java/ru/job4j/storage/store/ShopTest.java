package ru.job4j.storage.store;

import org.junit.jupiter.api.Test;
import ru.job4j.storage.model.Food;
import ru.job4j.storage.tools.GetPercent;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class ShopTest {
    @Test
    void whenIsSuitable() {
        GetPercent getPercent = new GetPercent();
        Store shop = new Shop(getPercent);
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