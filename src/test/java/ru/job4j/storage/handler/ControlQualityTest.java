package ru.job4j.storage.handler;

import org.junit.jupiter.api.Test;
import ru.job4j.storage.model.Food;
import ru.job4j.storage.store.Shop;
import ru.job4j.storage.store.Store;
import ru.job4j.storage.store.Trash;
import ru.job4j.storage.store.Warehouse;
import ru.job4j.storage.tools.GetPercent;

import java.time.LocalDate;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {

    @Test
    void checkDistribution() {
        Food meat = new Food(
                "Meat",
                500.0,
                150.0,
                LocalDate.of(2023, 02, 20),
                LocalDate.of(2023, 02, 11)
        );
        Food milk = new Food(
                "Milk",
                100,
                50,
                LocalDate.of(2023, 02, 25),
                LocalDate.of(2023, 01, 01)
        );
        Food apple = new Food(
                "Apple",
                125,
                50,
                LocalDate.of(2023, 01, 25),
                LocalDate.of(2023, 01, 9)
        );
        Map<String, Store> stores = Map.of(
                "Shop", new Shop(), "Trash", new Trash(), "Warehouse", new Warehouse());
        GetPercent getPercent = new GetPercent();
        ControlQuality controlQuality = new ControlQuality(getPercent, stores);
        controlQuality.distribution(meat);
        controlQuality.distribution(milk);
        controlQuality.distribution(apple);
        assertThat(stores.get("Warehouse").getFoods().get(0)).isEqualTo(meat);
        assertThat(stores.get("Shop").getFoods().get(0)).isEqualTo(milk);
        assertThat(stores.get("Trash").getFoods().get(0)).isEqualTo(apple);
    }

    @Test
    void whenDiscount() {
        Food meat = new Food(
                "Meat",
                500.0,
                150.0,
                LocalDate.of(2023, 02, 13),
                LocalDate.of(2023, 02, 1)
        );
        Map<String, Store> stores = Map.of(
                "Shop", new Shop(), "Trash", new Trash(), "Warehouse", new Warehouse());
        GetPercent getPercent = new GetPercent();
        ControlQuality controlQuality = new ControlQuality(getPercent, stores);
        controlQuality.distribution(meat);
        assertThat(stores.get("Shop").getFoods().get(0).getPrice()).isEqualTo(350);
    }
}