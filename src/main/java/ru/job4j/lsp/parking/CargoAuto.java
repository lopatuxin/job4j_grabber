package ru.job4j.lsp.parking;

public class CargoAuto extends Auto {
    public CargoAuto(int size, String name) {
        super(size, name);
        if (size <= PassengerAuto.SIZE) {
            throw new IllegalArgumentException("Size must be more than 1");
        }
    }
}
