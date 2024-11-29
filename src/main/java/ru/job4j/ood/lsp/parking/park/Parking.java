package ru.job4j.ood.lsp.parking.park;

public abstract class Parking {
    protected int[][] parking;

    public Parking(int[][] parking) {
        this.parking = parking;
    }

    public int[][] getParking() {
        return parking;
    }
}
