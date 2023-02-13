package ru.job4j.isp.transport;

public class Bus implements Transport {
    @Override
    public void drive() {
        System.out.println("Еду");
    }

    @Override
    public void fly() {
        System.out.println("Не могу летать");
    }

    @Override
    public void brake() {
        System.out.println("Торможу");
    }
}
