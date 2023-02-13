package ru.job4j.storage.tools;

import java.time.LocalDate;

public class SimplePercentCalculator implements PercentCalculator {
    private final LocalDate date;

    public SimplePercentCalculator() {
        this.date = LocalDate.now();
    }

    public double getPercent(LocalDate expiryDate, LocalDate createDate) {
        double result = -1;
        long full = expiryDate.toEpochDay() - createDate.toEpochDay();
        long remains = expiryDate.toEpochDay() - date.toEpochDay();
        if (remains < 0) {
            return result;
        }
        result = (double) (100 - (100 /  (full / remains)));
        return result;
    }
}
