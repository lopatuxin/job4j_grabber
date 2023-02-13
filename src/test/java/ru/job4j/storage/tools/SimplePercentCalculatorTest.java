package ru.job4j.storage.tools;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class SimplePercentCalculatorTest {

    @Test
    void checkGetPercent() {
        SimplePercentCalculator simplePercentCalculator = new SimplePercentCalculator();
        double expected = simplePercentCalculator.getPercent(
                LocalDate.of(2023, 05, 23), LocalDate.of(2022, 01, 31));
        assertThat(expected).isEqualTo(75);
    }
}