package ru.job4j.storage.store;

import org.junit.jupiter.api.Test;
import ru.job4j.storage.model.Food;
import ru.job4j.storage.tools.SimplePercentCalculator;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class WarehouseTest {
    @Test
    void whenIsSuitable() {
        SimplePercentCalculator simplePercentCalculator = new SimplePercentCalculator();
        Store warehouse = new Warehouse(simplePercentCalculator);
        Food meat = new Food(
                "Meat",
                500.0,
                150.0,
                LocalDate.of(2023, 02, 20),
                LocalDate.of(2023, 02, 11)
        );
        assertThat(warehouse.add(meat)).isTrue();
    }
}