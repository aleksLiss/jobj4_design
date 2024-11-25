package ru.job4j.isp.second;

public interface Doctor {

    /**
     * Разные врачи лечат разные органы,
     * при реализации одного метода у одного врача
     * придётся заглушать остальные методы.
     */
    void treatEyes();

    void treatHeart();

    void treatBrain();
}
