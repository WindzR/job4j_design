package ru.job4j.lsp.parking;

import java.util.List;

public interface Parking {

    boolean accept(AbstractCar car);

    void parking(AbstractCar car);

    List<AbstractCar> getAll();
}
