package ru.job4j.ood.lsp.parking.park;

import ru.job4j.ood.lsp.parking.car.Car;

public class ParkingExample extends Parking implements Park {

    public ParkingExample(int[][] parking) {
        super(parking);
    }

    @Override
    public void in(Car car) {
        int[][] parking = getParking();
        int carSize = car.getSize();
        boolean in = false;
        int indexStart;
        int indexEnd;
        for (int i = 0; i < parking.length; i++) {
            int counter = 0;
            indexStart = -1;
            indexEnd = -1;
            if (in) {
                break;
            }
            for (int j = 0; j < parking[i].length; j++) {
                if (parking[i][j] == 0) {
                    counter++;
                    if (indexStart == -1) {
                        indexStart = j;
                    }
                }
                if (counter == carSize) {
                    indexEnd = j;
                    for (int k = indexStart; k <= indexEnd; k++) {
                        parking[i][k] = carSize;
                    }
                    in = true;
                    break;
                }
            }
        }
        if (!in) {
            throw new IllegalArgumentException("The Parking is full");
        }
    }
}
