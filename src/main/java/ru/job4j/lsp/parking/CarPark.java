package ru.job4j.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class CarPark implements Parking {
    private final int CARGO_COUNT;
    private int cargoCounter = 0;
    private final int PASSENGER_COUNT;
    private int passengerCounter = 0;
    private List<Auto> autos;

    public CarPark(int CARGO_COUNT, int PASSENGER_COUNT) {
        this.CARGO_COUNT = CARGO_COUNT;
        this.PASSENGER_COUNT = PASSENGER_COUNT;
        this.autos = new ArrayList<>();
    }

    @Override
    public boolean add(Auto auto) {
        if (auto.getSize() > 1 && cargoCounter < CARGO_COUNT) {
            autos.add(auto);
            cargoCounter++;
            return true;
        }
        if (auto.getSize() == 1 && passengerCounter < PASSENGER_COUNT) {
            autos.add(auto);
            passengerCounter++;
            return true;
        }
        if (auto.getSize() > 1
                && cargoCounter >= CARGO_COUNT
                && PASSENGER_COUNT - passengerCounter >= auto.getSize()) {
            autos.add(auto);
            passengerCounter = passengerCounter + auto.getSize();
            return true;
        }
        return false;
    }
}
