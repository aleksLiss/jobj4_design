package ru.job4j.ood.lsp.parking.car;

import java.util.Objects;

public abstract class Car implements Drive {

    private int size;

    public Car(int size) {
        checkSize(size);
        this.size = size;
    }

    private void checkSize(int size) {
        if (size < 1) {
            throw new IllegalArgumentException();
        }
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
}
