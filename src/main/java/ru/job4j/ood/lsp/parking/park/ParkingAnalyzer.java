package ru.job4j.ood.lsp.parking.park;

import ru.job4j.ood.lsp.parking.car.Car;

public class ParkingAnalyzer implements Analyzer {
    private ParkingExample parkingEx;

    public ParkingAnalyzer(ParkingExample parkingEx) {
        this.parkingEx = parkingEx;
    }

    @Override
    public int getAllFreePlaces(Car car) {
        int[][] parking = parkingEx.getParking();
        int counterAllFreePlaces = 0;
        int carSize = car.getSize();
        int indexStart = -1;
        int indexEnd = -1;
        for (int i = 0; i < parking.length; i++) {
            int counter = 0;
            for (int j = 0; j < parking[i].length; j++) {
                if (parking[i][j] == 0) {
                    counter++;
                    if (indexStart == -1) {
                        indexStart = j;
                    }
                }
                if (counter == carSize) {
                    indexEnd = j;
                }
                if (indexStart != -1 && indexEnd != -1) {
                    counterAllFreePlaces++;
                    indexStart = -1;
                    indexEnd = -1;
                    counter = 0;
                }
            }
        }
        return counterAllFreePlaces;
    }

    @Override
    public int getFreePlace(Car car) {
        int[][] parking = parkingEx.getParking();
        int carSize = car.getSize();
        int indexStart = -1;
        int result = -1;
        for (int i = 0; i < parking.length; i++) {
            int counter = 0;
            for (int j = 0; j < parking[i].length; j++) {
                if (parking[i][j] == 0) {
                    counter++;
                    if (indexStart == -1) {
                        indexStart = j;
                    }
                }
                if (counter == carSize) {
                    result = indexStart;
                }
            }
        }
        return result;
    }
}