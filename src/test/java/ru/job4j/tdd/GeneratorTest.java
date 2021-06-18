package ru.job4j.tdd;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class GeneratorTest {

    /**
     * проверка работы базового шаблона
     */
    @Test
    public void whenCorrectProduce() {
        Generator generator = new Question();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> arguments = Map.of("name", "Petr Arsentev", "subject", "you");
        String result = generator.produce(template, arguments);
        String expected = "I am a Petr Arsentev, Who are you?";
        assertThat(expected, is(result));
    }

    /**
     * карта не содержит верные ключи, выкидывается исключение
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenIncorrectKeys() {
        Generator generator = new Question();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> arguments = Map.of("number", "Petr Arsentev", "subject", "you");
        String result = generator.produce(template, arguments);
    }

    /**
     * карта содержит лишние ключи, выкидывается исключение
     */
    @Test(expected = IllegalArgumentException.class)
    public void whenExcessKeys() {
        Generator generator = new Question();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> arguments = Map.of("name", "Petr Arsentev", "subject", "you", "key3", "value3");
        String result = generator.produce(template, arguments);
    }

    /**
     * значения карты не должны быть null
     */
    @Test(expected = NullPointerException.class)
    public void whenNullKeys() {
        Generator generator = new Question();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> arguments = Map.of("name", null, "subject", null);
        String result = generator.produce(template, arguments);
    }

    /**
     * значение шаблона не должно быть null
     */
    @Test(expected = NullPointerException.class)
    public void whenNullTemplate() {
        Generator generator = new Question();
        String template = null;
        Map<String, String> arguments = Map.of("name", "Petr Arsentev", "subject", "you");
        String result = generator.produce(template, arguments);
    }

    /**
     * когда шаблон не содержит ключей, метод возвращает сам шаблон
     */
    @Test
    public void whenNotExistKeys() {
        Generator generator = new Question();
        String template = "I am a Ivan, Who are you?";
        Map<String, String> arguments = Map.of("name", "Petr Arsentev", "subject", "you");
        String result = generator.produce(template, arguments);
        String expected = "I am a Ivan, Who are you?";
        assertThat(expected, is(result));
    }
}