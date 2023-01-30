package ru.job4j.grabber.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

class HabrCareerDateTimeParserTest {

    @Test
    void whenParseTrue() {
        HabrCareerDateTimeParser parser = new HabrCareerDateTimeParser();
        String date = "2023-01-29T20:27:33+03:00";
        LocalDateTime expected = LocalDateTime.of(2023, 01, 29, 20, 27, 33);
        assertThat(expected.isEqual(parser.parse(date)));
    }

    @Test
    void whenParseFalse() {
        HabrCareerDateTimeParser parser = new HabrCareerDateTimeParser();
        String date = "2023-01-29T20:27:33+03:00";
        LocalDateTime expected = LocalDateTime.of(2023, 03, 29, 20, 27, 33);
        assertFalse(expected.isEqual(parser.parse(date)));
    }
}