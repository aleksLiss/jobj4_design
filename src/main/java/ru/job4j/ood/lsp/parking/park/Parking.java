package ru.job4j.ood.lsp.parking.park;

import ru.job4j.ood.lsp.parking.car.Car;

public interface Parking {

    void in(Car car);

    void out(Car car);
}
