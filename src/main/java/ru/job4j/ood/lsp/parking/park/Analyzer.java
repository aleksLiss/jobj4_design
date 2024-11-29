package ru.job4j.ood.lsp.parking.park;

import ru.job4j.ood.lsp.parking.car.Car;

public interface Analyzer {
    Parking PARKING = null;

    int getAllFreePlaces(Car car);

    int getFreePlace(Car car);
}
