package ru.job4j.kiss.fool;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FoolTest {

    @Test
    public void whenInputNumModThreeEqualsZeroAndModFiveEqualsZeroThenReturnFizzBuzz() {
        String expected = "FizzBuzz";
        int inputNumber = 15;
        String result = Fool.calculate(inputNumber);
        assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .isEqualTo(expected);
    }

    @Test
    public void whenInputNumModThreeEqualsZeroThenReturnFizz() {
        String expected = "Fizz";
        int inputNumber = 9;
        String result = Fool.calculate(inputNumber);
        assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .isEqualTo(expected);
    }

    @Test
    public void whenInputNumModModFiveEqualsZeroThenReturnBuzz() {
        String expected = "Buzz";
        int inputNumber = 5;
        String result = Fool.calculate(inputNumber);
        assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .isEqualTo(expected);
    }

    @Test
    public void whenInputNumModThreeAndModFiveNotEqualsZeroThenReturnNum() {
        int inputNumber = 11;
        String expected = String.valueOf(inputNumber);
        String result = Fool.calculate(inputNumber);
        assertThat(result)
                .isNotNull()
                .isNotEmpty()
                .isEqualTo(expected);
    }
}