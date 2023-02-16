package ru.job4j.lsp.parking;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CarParkTest {
    @Test
    void whenAddPassengerAuto() {
        CarPark carPark = new CarPark(0, 1);
        Auto auto = new Passenger(1, "KIA");
        assertThat(carPark.add(auto)).isTrue();
    }

    @Test
    void whenAddCargoAuto() {
        CarPark carPark = new CarPark(1, 0);
        Auto auto = new Cargo(2, "MAN");
        assertThat(carPark.add(auto)).isTrue();
    }

    @Test
    void whenAddCargoInPassenger() {
        CarPark carPark = new CarPark(0, 2);
        Auto auto = new Cargo(2, "MAN");
        assertThat(carPark.add(auto)).isTrue();
    }

    @Test
    void whenNotAddAuto() {
        CarPark carPark = new CarPark(0, 1);
        Auto auto = new Cargo(2, "MAN");
        assertThat(carPark.add(auto)).isFalse();
    }
}