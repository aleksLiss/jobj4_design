package ru.job4j.serialization.xml;

public class Address {

    private final String street;
    private final int number;
    private final int floor;

    public Address(String street, int number, int floor) {
        this.street = street;
        this.number = number;
        this.floor = floor;
    }

    @Override
    public String toString() {
        return "Address{"
                + "street='"
                + street + '\''
                + ", number="
                + number
                + ", floor="
                + floor
                + '}';
    }
}
