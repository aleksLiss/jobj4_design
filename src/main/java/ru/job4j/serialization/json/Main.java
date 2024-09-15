package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Kitty son = new Kitty("Nick", 1);
        final Cat cat = new Cat("Bob", 8, true, new String[]{son.getName()}, son);
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(cat));
        final String mod = "{"
                + "\"name\":\"Jack\","
                + "\"age\":12,"
                + "\"hasChild\":true,"
                + "\"childNames\":[\"Tim\"],"
                + "\"son\":"
                + "{"
                + "\"name\":\"Tim\","
                + "\"age\":3"
                + "}"
                + '}';
        final Cat modCat = gson.fromJson(mod, Cat.class);
        System.out.println(modCat);
    }
}
