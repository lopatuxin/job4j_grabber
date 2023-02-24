package ru.job4j.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class CarPark implements Parking {
    private final int cargoCount;
    private int cargoCounter = 0;
    private final int passengerCount;
    private int passengerCounter = 0;
    private List<Auto> autos;

    public CarPark(int cargoCount, int passengerCount) {
        this.cargoCount = cargoCount;
        this.passengerCount = passengerCount;
        this.autos = new ArrayList<>();
    }

    @Override
    public boolean add(Auto auto) {
        if (auto.getSize() > PassengerAuto.SIZE && cargoCounter < cargoCount) {
            autos.add(auto);
            cargoCounter++;
            return true;
        }
        if (auto.getSize() == PassengerAuto.SIZE && passengerCounter < passengerCount) {
            autos.add(auto);
            passengerCounter++;
            return true;
        }
        if (auto.getSize() > PassengerAuto.SIZE
                && cargoCounter >= cargoCount
                && passengerCount - passengerCounter >= auto.getSize()) {
            autos.add(auto);
            passengerCounter = passengerCounter + auto.getSize();
            return true;
        }
        return false;
    }
}
