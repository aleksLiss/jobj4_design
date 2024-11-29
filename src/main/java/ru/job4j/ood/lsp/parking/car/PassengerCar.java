package ru.job4j.ood.lsp.parking.car;

public class PassengerCar extends Car {
    public PassengerCar(int size) {
        super(size);
    }

    @Override
    public void go() {
        System.out.println("Passenger car is moving");
    }

}
