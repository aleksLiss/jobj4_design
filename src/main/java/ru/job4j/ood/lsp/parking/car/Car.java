package ru.job4j.ood.lsp.parking.car;

import java.util.Objects;

public abstract class Car implements Drive {

    private int size;

    public Car(int size) {
        this.size = checkSize(size);
    }

    private int checkSize(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Size auto must be great than zero");
        }
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return size == car.size;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(size);
    }

    public int getSize() {
        return size;
    }
}
