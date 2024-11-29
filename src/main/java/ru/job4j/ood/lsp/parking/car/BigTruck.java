package ru.job4j.ood.lsp.parking.car;

public class BigTruck extends Car {
    public BigTruck(int size) {
        super(size);
    }

    @Override
    public void go() {
        System.out.println("Big truck is moving");
    }

}
