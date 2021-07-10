package ru.job4j.lsp.parking;

import org.junit.Test;

import java.util.Arrays;
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
        List<AbstractCar> result = parking.getAll();
        var expected = new ArrayList<AbstractCar>();
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

    @Test
    public void whenTestTest() {
        AbstractCar truck1 = new Truck("Kamaz", 2);
        AbstractCar car1 = new Car("Toyota");
        AbstractCar truck2 = new Truck("Volvo", 3);
        AbstractCar car2 = new Car("Lada");
        ParkingA parking = new ParkingA(3, 1);
        MachineDistribution distribution = new MachineDistribution(parking);
        assertTrue(parking.getTruckEmptyPlaces() == 1);
        distribution.carParking(truck1);
        assertTrue(parking.getCarEmptyPlaces() == 3);
        assertTrue(parking.getTruckEmptyPlaces() == 1);
        distribution.carParking(car1);
        assertTrue(parking.getCarEmptyPlaces() == 3);
        distribution.carParking(truck2);
        assertTrue(parking.getTruckEmptyPlaces() == 1);
        assertTrue(parking.getCarEmptyPlaces() == 3);
        distribution.carParking(car2);
        assertTrue(parking.getCarEmptyPlaces() == 3);
    }

    @Test
    public void whenTestArray() {
        AbstractCar truck1 = new Truck("Kamaz", 2);
        AbstractCar car1 = new Car("Toyota");
        AbstractCar truck2 = new Truck("Volvo", 3);
        AbstractCar car2 = new Car("Lada");
        ParkingA parking = new ParkingA(3, 1);
        MachineDistribution distribution = new MachineDistribution(parking);
        System.out.println(Arrays.toString(parking.getArrayCarParking()));
        System.out.println(Arrays.toString(parking.getArrayTruckParking()));
        distribution.carParking(truck1);
        System.out.println(Arrays.toString(parking.getArrayCarParking()));
        System.out.println(Arrays.toString(parking.getArrayTruckParking()));
        distribution.carParking(car1);
        System.out.println(Arrays.toString(parking.getArrayCarParking()));
        System.out.println(Arrays.toString(parking.getArrayTruckParking()));
        distribution.carParking(truck2);
        System.out.println(Arrays.toString(parking.getArrayCarParking()));
        System.out.println(Arrays.toString(parking.getArrayTruckParking()));
        distribution.carParking(car2);
        System.out.println(Arrays.toString(parking.getArrayCarParking()));
        System.out.println(Arrays.toString(parking.getArrayTruckParking()));
    }
}