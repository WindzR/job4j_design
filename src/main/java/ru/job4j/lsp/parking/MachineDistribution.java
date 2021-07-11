package ru.job4j.lsp.parking;

import java.util.List;

public class MachineDistribution {
    private List<Parking> parkings;

    public MachineDistribution() {
        this.parkings = List.of(new ParkingA());
    }

    public MachineDistribution(Parking parking) {
        this.parkings = List.of(parking);
    }

    public boolean carParking(AbstractCar car) {
        for (Parking parking : parkings) {
            if (parking.accept(car)) {
                parking.parking(car);
                return true;
            }
        }
        return false;
    }
}
