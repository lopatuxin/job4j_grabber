package ru.job4j.storage.tools;

import java.time.LocalDate;

public interface PercentCalculator {
    double getPercent(LocalDate expiryDate, LocalDate createDate);
}
