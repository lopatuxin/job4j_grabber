package ru.job4j.lsp.parking;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CarParkTest {
    @Test
    void whenAddPassengerAuto() {
        CarPark carPark = new CarPark(0, 1);
        Auto auto = new PassengerAuto("KIA");
        assertThat(carPark.add(auto)).isTrue();
    }

    @Test
    void whenAddCargoAuto() {
        CarPark carPark = new CarPark(1, 0);
        Auto auto = new CargoAuto(2, "MAN");
        assertThat(carPark.add(auto)).isTrue();
    }

    @Test
    void whenAddCargoInPassenger() {
        CarPark carPark = new CarPark(0, 2);
        Auto auto = new CargoAuto(2, "MAN");
        assertThat(carPark.add(auto)).isTrue();
    }

    @Test
    void whenNotAddAuto() {
        CarPark carPark = new CarPark(0, 1);
        Auto auto = new CargoAuto(2, "MAN");
        assertThat(carPark.add(auto)).isFalse();
    }

    @Test
    void whenCargoAutoBeLessOneSize() {
        assertThatThrownBy(() ->
                new CargoAuto(1, "KAMAZ")).hasMessageContaining("Size must be more than 1");
    }
}