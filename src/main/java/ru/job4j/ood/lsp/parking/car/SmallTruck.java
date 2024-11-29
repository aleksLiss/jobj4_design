package ru.job4j.ood.lsp.parking.car;

public class SmallTruck extends Car {

    public SmallTruck(int size) {
        super(size);
    }

    @Override
    public void go() {
        System.out.println("Small truck is moving");
    }
}
