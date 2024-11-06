package ru.job4j.gc.leak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class UserGenerator implements Generate {

    private static final String PATH_NAMES = "src/main/java/ru/job4j/gc/leak/files/names.txt";
    private static final String PATH_SURNAMES = "src/main/java/ru/job4j/gc/leak/files/surnames.txt";
    private static final String PATH_PATRONS = "src/main/java/ru/job4j/gc/leak/files/patr.txt";

    private static final String SEPARATOR = " ";
    private static final Integer NEW_USERS = 1000;

    private List<String> names;
    private List<String> surnames;
    private List<String> patrons;
    private List<User> users = new ArrayList<>();
    private Random random;

    public UserGenerator(Random random) {
        this.random = random;
        readAll();
    }

    @Override
    public void generate() {
        users.clear();
        Stream.of(NEW_USERS).forEach(i -> users.add(new User(
                new StringBuilder(surnames.get(random.nextInt(surnames.size())))
                        .append(SEPARATOR)
                        .append(names.get(random.nextInt(names.size())))
                        .append(SEPARATOR)
                        .append(patrons.get(random.nextInt(patrons.size()))).toString())));
    }

    private void readAll() {
        try {
            names = read(PATH_NAMES);
            surnames = read(PATH_SURNAMES);
            patrons = read(PATH_PATRONS);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public User randomUser() {
        return users.get(random.nextInt(users.size()));
    }
}