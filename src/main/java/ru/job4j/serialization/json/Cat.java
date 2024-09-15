package ru.job4j.serialization.json;

import java.util.Arrays;

public class Cat {
    private final String name;
    private final int age;
    private final boolean hasChild;
    private final String[] childNames;
    private final Kitty son;

    public Cat(String name, int age, boolean hasChild, String[] childNames, Kitty son) {
        this.name = name;
        this.age = age;
        this.hasChild = hasChild;
        this.childNames = childNames;
        this.son = son;
    }


    @Override
    public String toString() {
        return "Cat{"
                + "name="
                + name
                + ", age="
                + age
                + ", hasChild="
                + hasChild
                + ", childNames="
                + Arrays.toString(childNames)
                + ", son="
                + son
                + '}';
    }
}
