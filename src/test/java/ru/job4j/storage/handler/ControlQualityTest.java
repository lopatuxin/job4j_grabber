package ru.job4j.storage.handler;

import org.junit.jupiter.api.Test;
import ru.job4j.storage.model.Food;
import ru.job4j.storage.store.Shop;
import ru.job4j.storage.store.Store;
import ru.job4j.storage.store.Trash;
import ru.job4j.storage.store.Warehouse;
import ru.job4j.storage.tools.SimplePercentCalculator;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {

    @Test
    void whenDistributionIsWarehouse() {
        SimplePercentCalculator simplePercentCalculator = new SimplePercentCalculator();
        List<Store> storeList = List.of(new Warehouse(simplePercentCalculator));
        ControlQuality controlQuality = new ControlQuality(storeList);
        Food meat = new Food(
                "Meat",
                500.0,
                150.0,
                LocalDate.of(2023, 02, 20),
                LocalDate.of(2023, 02, 11)
        );
        Store expected = controlQuality.distribution(meat);
        assertThat(expected).isExactlyInstanceOf(Warehouse.class);
    }
    @Test
    void whenDistributionIsShop() {
        SimplePercentCalculator simplePercentCalculator = new SimplePercentCalculator();
        List<Store> storeList = List.of(new Shop(simplePercentCalculator));
        ControlQuality controlQuality = new ControlQuality(storeList);
        Food milk = new Food(
                "Milk",
                100,
                50,
                LocalDate.of(2023, 02, 25),
                LocalDate.of(2023, 01, 01)
        );
        Store expected = controlQuality.distribution(milk);
        assertThat(expected).isExactlyInstanceOf(Shop.class);
    }

    @Test
    void whenDistributionIsTrash() {
        SimplePercentCalculator simplePercentCalculator = new SimplePercentCalculator();
        List<Store> storeList = List.of(new Trash(simplePercentCalculator));
        ControlQuality controlQuality = new ControlQuality(storeList);
        Food apple = new Food(
                "Apple",
                125,
                50,
                LocalDate.of(2023, 01, 25),
                LocalDate.of(2023, 01, 9)
        );
        Store expected = controlQuality.distribution(apple);
        assertThat(expected).isExactlyInstanceOf(Trash.class);
    }
}