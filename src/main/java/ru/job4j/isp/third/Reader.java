package ru.job4j.isp.third;

import java.io.File;

public interface Reader {

    /**
     * Так же, лучше сделать несколько интерфейсов
     * под чтение файлов с различным расширением.
     * @param file
     */
    void readTxt(File file);

    void readDocx(File file);

    void readXml(File file);
}
