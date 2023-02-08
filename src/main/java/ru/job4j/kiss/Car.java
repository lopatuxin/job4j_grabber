package ru.job4j.kiss;

public class Car {
    private int year;

    public Car(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        return year == car.year;
    }

    @Override
    public int hashCode() {
        return year;
    }
}
