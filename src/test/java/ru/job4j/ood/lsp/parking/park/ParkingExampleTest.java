package ru.job4j.ood.lsp.parking.park;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.parking.car.BigTruck;
import ru.job4j.ood.lsp.parking.car.Car;

import static org.assertj.core.api.Assertions.*;

class ParkingExampleTest {
    @Test
    public void whenParkingIsFullThenGetException() {
        int[][] parkingPlace = new int[3][3];
        Car car = new BigTruck(3);
        ParkingExample parking = new ParkingExample(parkingPlace);
        parking.in(car);
        parking.in(car);
        parking.in(car);
        assertThatThrownBy(() -> parking.in(car))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("The Parking is full");

    }
}