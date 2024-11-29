package ru.job4j.ood.lsp.parking.park;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.parking.car.BigTruck;
import ru.job4j.ood.lsp.parking.car.Car;
import ru.job4j.ood.lsp.parking.car.PassengerCar;
import ru.job4j.ood.lsp.parking.car.SmallTruck;

import static org.assertj.core.api.Assertions.*;


class ParkingAnalyzerTest {

    @Test
    public void whenCarHasSizeOneThenGetTwentyFiveFreePlacesForParking() {
        int[][] parkingPlace = new int[5][5];
        ParkingExample parking = new ParkingExample(parkingPlace);
        Analyzer analyzer = new ParkingAnalyzer(parking);
        Car car = new PassengerCar(1);
        int result = analyzer.getAllFreePlaces(car);
        int expected = 25;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenCarHasSizeTwoThenReturnThreeFreePlacesForParking() {
        int[][] parkingPlace = new int[3][3];
        ParkingExample parking = new ParkingExample(parkingPlace);
        Analyzer analyzer = new ParkingAnalyzer(parking);
        Car car = new SmallTruck(2);
        int result = analyzer.getAllFreePlaces(car);
        int expected = 3;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenCarHasSizeThreeThenReturnFourFreePlacesForParking() {
        int[][] parkingPlace = new int[4][4];
        ParkingExample parking = new ParkingExample(parkingPlace);
        Analyzer analyzer = new ParkingAnalyzer(parking);
        Car car = new BigTruck(3);
        int result = analyzer.getAllFreePlaces(car);
        int expected = 4;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenAddCarWithSizeOneThenReturnEightFreePlacesForParking() {
        int[][] parkingPlace = new int[3][3];
        Car car = new PassengerCar(1);
        ParkingExample parking = new ParkingExample(parkingPlace);
        parking.in(car);
        Analyzer analyzer = new ParkingAnalyzer(parking);
        int result = analyzer.getAllFreePlaces(car);
        int expected = 8;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenAddCarWithSizeTwoThenReturnTwoFreePlacesForParking() {
        int[][] parkingPlace = new int[3][3];
        Car car = new SmallTruck(2);
        ParkingExample parking = new ParkingExample(parkingPlace);
        parking.in(car);
        Analyzer analyzer = new ParkingAnalyzer(parking);
        int result = analyzer.getAllFreePlaces(car);
        int expected = 2;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenAddCarWithSizeThreeThenReturnThreeFreePlacesForParking() {
        int[][] parkingPlace = new int[2][6];
        Car car = new BigTruck(3);
        ParkingExample parking = new ParkingExample(parkingPlace);
        parking.in(car);
        Analyzer analyzer = new ParkingAnalyzer(parking);
        int result = analyzer.getAllFreePlaces(car);
        int expected = 3;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenCarHasSizeOneThenReturnFirstFreePlaceForThisCar() {
        int[][] parkingPlace = new int[3][3];
        Car car = new PassengerCar(1);
        ParkingExample parking = new ParkingExample(parkingPlace);
        parking.in(car);
        Analyzer analyzer = new ParkingAnalyzer(parking);
        int result = analyzer.getFreePlace(car);
        int expected = 1;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenCarHasSizeTwoThenReturnFirstFreePlaceForThisCar() {
        int[][] parkingPlace = new int[3][3];
        Car car = new SmallTruck(2);
        ParkingExample parking = new ParkingExample(parkingPlace);
        parking.in(car);
        Analyzer analyzer = new ParkingAnalyzer(parking);
        int result = analyzer.getAllFreePlaces(car);
        int expected = 2;
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenCarHasSizeThreeThenReturnFirstFreePlaceForThisCar() {
        int[][] parkingPlace = new int[3][3];
        Car car = new BigTruck(3);
        ParkingExample parking = new ParkingExample(parkingPlace);
        parking.in(car);
        Analyzer analyzer = new ParkingAnalyzer(parking);
        int result = analyzer.getAllFreePlaces(car);
        int expected = 2;
        assertThat(result).isEqualTo(expected);
    }
}