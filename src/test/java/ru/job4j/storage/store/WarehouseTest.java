package ru.job4j.storage.store;

import org.junit.jupiter.api.Test;
import ru.job4j.storage.model.Food;
import ru.job4j.storage.tools.GetPercent;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class WarehouseTest {
    @Test
    void whenIsSuitable() {
        GetPercent getPercent = new GetPercent();
        Store warehouse = new Warehouse(getPercent);
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