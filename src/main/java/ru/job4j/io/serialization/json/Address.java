package ru.job4j.io.serialization.json;

public class Address {
    private final String street;
    private final int house;
    private final int flat;

    public Address(String street, int house, int flat) {
        this.street = street;
        this.house = house;
        this.flat = flat;
    }

    @Override
    public String toString() {
        return "Address{"
                + " street='" + street + '\''
                + ", house=" + house
                + ", flat=" + flat
                + '}';
    }
}
