package ru.job4j.lsp.parking;

import java.util.Objects;

public class Truck extends AbstractCar {
    private String name;
    private int size;

    public Truck() {
    }

    public Truck(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Truck truck = (Truck) o;
        return size == truck.size &&
                name.equals(truck.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size);
    }
}
