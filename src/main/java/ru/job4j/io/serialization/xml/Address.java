package ru.job4j.io.serialization.xml;

public class Address {
    private String street;
    private int house;
    private int flat;

    public Address() {}

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
