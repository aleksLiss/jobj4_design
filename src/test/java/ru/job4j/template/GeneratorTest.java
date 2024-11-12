package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@Disabled
class GeneratorTest {

    @Test
    public void whenMapContainsNameAndSubjectThenGetCorrectString() {
        Generator generator = new GeneratorOfQuestion();
        String template = "I am a %s, Who are %s?";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Vova");
        map.put("subject", "you");
        String expected = "I am a Vova, Who are you?";
        String result = generator.produce(template, map);
        assertThat(expected).isNotNull().isNotEmpty().isEqualTo(result);
    }

    @Test
    public void whenTemplateHasKeysThatDoesNotExistInMapThenGetException() {
        Generator generator = new GeneratorOfQuestion();
        String template = "I am a %s, Who are %s?";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Vova");
        assertThatThrownBy(() -> generator.produce(template, map))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenMapHasKeysWhichAreMoreThenNecessaryThenGetException() {
        Generator generator = new GeneratorOfQuestion();
        String template = "I am a %s, Who are %s?";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Vova");
        map.put("subject", "you");
        map.put("age", "22");
        assertThatThrownBy(() -> generator.produce(template, map))
                .isInstanceOf(IllegalArgumentException.class);
    }
}