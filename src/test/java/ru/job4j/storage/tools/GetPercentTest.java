package ru.job4j.storage.tools;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class GetPercentTest {

    @Test
    void checkGetPercent() {
        GetPercent getPercent = new GetPercent();
        double expected = getPercent.getPercent(
                LocalDate.of(2023, 05, 23), LocalDate.of(2022, 01, 31));
        assertThat(expected).isEqualTo(75);
    }
}