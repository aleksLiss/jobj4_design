package ru.job4j.ood.lsp.parking.park;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.parking.car.Car;
import ru.job4j.ood.lsp.parking.car.PassengerCar;

import static org.assertj.core.api.Assertions.*;

class ParkingAnalyzerTest {

    @Test
    public void whenAddCarThenReturnCountOfFreePlacesForThisCar() {
        Analyzer analyzer = new ParkingAnalyzer();
        Car car = new PassengerCar(1);
        int result = analyzer.getAllFreePlaces(car);
        int expected = 0;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenAddCarThenReturnFirstFreePlaceForThisCar() {
        Analyzer analyzer = new ParkingAnalyzer();
        Car car = new PassengerCar(1);
        int result = analyzer.getFreePlace(car);
        int expected = 0;
        assertThat(result).isEqualTo(expected);
    }
}