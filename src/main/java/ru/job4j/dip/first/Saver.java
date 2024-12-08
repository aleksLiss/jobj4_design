package ru.job4j.dip.first;

public class Saver {

    /**
     * Происходит нарушение принципа DIP,
     * так как мы зависим от реализации,
     * так как сразу указано с какой БД мы работаем.
     */
    public void saveToPostgresDB() {

    }

}
