package ru.job4j.lsp.parking;

import java.util.Objects;

public class Car extends AbstractCar {
    private String name;
    private final int size = 1;

    public Car() {
    }

    public Car(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return size == car.size
                && name.equals(car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size);
    }
}
