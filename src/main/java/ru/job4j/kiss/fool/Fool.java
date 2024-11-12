package ru.job4j.kiss.fool;

import java.util.Scanner;

public class Fool {
    protected static String calculate(int startAt) {
        return (startAt % 3 == 0 && startAt % 5 == 0)
                ? "FizzBuzz"
                : startAt % 3 == 0
                ? "Fizz"
                : startAt % 5 == 0
                ? "Buzz"
                : String.valueOf(startAt);
    }

    private static void run() {
        System.out.println("Игра FizzBuzz.");
        var startAt = 1;
        var input = new Scanner(System.in);
        while (startAt < 100) {
            System.out.println(calculate(startAt));
            startAt++;
            var answer = input.nextLine();
            if (!calculate(startAt).equals(answer)) {
                System.out.println("Ошибка. Начинай снова.");
                startAt = 1;
                continue;
            }
            startAt++;
        }
    }

    public static void main(String[] args) {
        run();
    }
}
