package ru.job4j.io.serialization.json;

public class Address {
    private String street;
    private int house;
    private int flat;

    public Address(String street, int house, int flat) {
        this.street = street;
        this.house = house;
        this.flat = flat;
    }

    public String getStreet() {
        return street;
    }

    public int getHouse() {
        return house;
    }

    public int getFlat() {
        return flat;
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
