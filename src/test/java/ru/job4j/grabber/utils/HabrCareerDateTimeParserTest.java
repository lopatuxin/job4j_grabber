package ru.job4j.grabber.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

class HabrCareerDateTimeParserTest {

    @Test
    void whenParseTrue() {
        HabrCareerDateTimeParser parser = new HabrCareerDateTimeParser();
        String date = LocalDateTime.now().toString();
        assertThat(LocalDateTime.now().isEqual(parser.parse(date)));
    }

    @Test
    void whenParseFalse() {
        HabrCareerDateTimeParser parser = new HabrCareerDateTimeParser();
        String date = "2023-01-29T20:27:33+03:00";
        assertFalse(LocalDateTime.now().isEqual(parser.parse(date)));
    }
}