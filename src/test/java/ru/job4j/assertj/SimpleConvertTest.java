package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("one", "two", "three", "four", "five");
        assertThat(list).isNotEmpty()
                .hasSize(5)
                .containsAnyOf("one", "three", "five")
                .startsWith("one")
                .endsWith("five");
    }

    @Test
    void checkFirstElementFromList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("one");
        assertThat(list).first()
                .isNotNull()
                .isEqualTo("one");
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("Six", "Seven", "Eight", "Nine", "Ten");
        assertThat(set).isNotEmpty()
                .hasSize(5)
                .containsExactlyInAnyOrder("Six", "Seven", "Eight", "Nine", "Ten");
    }

    @Test
    void checkAllMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("one", "two", "three");
        assertThat(map).hasSize(3)
                .containsKey("one")
                .containsValue(1)
                .containsEntry("one", 0);
    }
}