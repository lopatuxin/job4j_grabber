package ru.job4j.isp.transport;

public class Aircraft implements Transport {
    @Override
    public void drive() {
        System.out.println("Не могу ехать");
    }

    @Override
    public void fly() {
        System.out.println("Лечу");
    }

    @Override
    public void brake() {
        System.out.println("Торможу");
    }
}
