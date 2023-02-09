package ru.job4j.template;

import javassist.NotFoundException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
class GeneratorLibraryTest {

    @Test
    void whenGenerate() {
        Generator generator = new GeneratorLibrary();
        String template = "I am a ${name}, Who are ${subject}? ";
        String expected = "I am a Petr Arsentev, Who are you? ";
        String result = generator.produce(template, Map.of("name", "Petr Arsentev", "subject", "you"));
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void whenNotFoundKey() {
        Generator generator = new GeneratorLibrary();
        String template = "I am a ${surname}, Who are ${subject}? ";
        assertThatThrownBy(() ->
                generator.produce(template, Map.of("name", "Arsentev", "subject", "you"))).
                isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenThreeArgument() {
        Generator generator = new GeneratorLibrary();
        String template = "I am a ${name}{surname}, Who are ${subject}? ";
        assertThatThrownBy(() ->
                generator.produce(template, Map.of("name", "Arsentev", "subject", "you"))).
                isInstanceOf(IllegalArgumentException.class);
    }
}