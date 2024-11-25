package ru.job4j.isp.first;

public interface Telephone {

    void phone();

    /**
     * Если функция звонить имеется и в старых кнопочных телефонах,
     * и в современных смартфонах, то функция выхода в интернет
     * в кнопочных не подразумевается.
     */
    void surfInternet();

}
