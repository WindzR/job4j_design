package ru.job4j.lsp.parking;

import org.junit.Test;
import ru.job4j.collection.set.Set;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MachineDistributionTest {

    @Test
    public void whenNoCarPlaces() {
        AbstractCar car = new Car("Toyota");
        Parking parking = new ParkingA(0, 5);
        MachineDistribution distribution = new MachineDistribution(parking);
        distribution.carParking(car);
        assertFalse(distribution.carParking(car));
    }

    @Test
    public void whenNoTruckPlaces() {
        AbstractCar truck = new Truck("Kamaz", 2);
        Parking parking = new ParkingA(10, 0);
        MachineDistribution distribution = new MachineDistribution(parking);
        distribution.carParking(truck);
        List<AbstractCar> result = parking.getAll();
        assertThat(result.get(0), is(truck));
    }

    @Test
    public void whenPlacesIsRunOut() {
        AbstractCar truck1 = new Truck("Kamaz", 2);
        AbstractCar car1 = new Car("Toyota");
        AbstractCar truck2 = new Truck("Volvo", 3);
        AbstractCar car2 = new Car("Lada");
        Parking parking = new ParkingA(4, 1);
        MachineDistribution distribution = new MachineDistribution(parking);
        distribution.carParking(truck1);
        distribution.carParking(car1);
        distribution.carParking(truck2);
        distribution.carParking(car2);
        List<AbstractCar> resultA = parking.getAll();
        HashSet<AbstractCar> result = new HashSet<AbstractCar>(resultA);
        var expected = new HashSet<AbstractCar>();
        expected.add(truck1);
        expected.add(car1);
        expected.add(truck2);
        assertThat(result, is(expected));
    }

    @Test
    public void whenTruckPlacesIsRunOut() {
        AbstractCar truck1 = new Truck("Kamaz", 2);
        AbstractCar car1 = new Car("Toyota");
        AbstractCar truck2 = new Truck("Volvo", 3);
        AbstractCar car2 = new Car("Lada");
        Parking parking = new ParkingA(3, 1);
        MachineDistribution distribution = new MachineDistribution(parking);
        assertTrue(distribution.carParking(truck1));
        assertTrue(distribution.carParking(car1));
        assertFalse(distribution.carParking(truck2));
        assertTrue(distribution.carParking(car2));
    }
}