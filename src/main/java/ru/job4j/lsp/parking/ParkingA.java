package ru.job4j.lsp.parking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParkingA implements Parking {
    /**
     * truckParking - массив, представляющий собой заполненность парковки грузовиков
     * carParking - массив, представляющий собой заполненность парковки легковых автомобилей
     * 0 - место пусто, 1 - место занято
     */
    private int carPlaces;
    private int truckPlaces;
    private byte[] truckParking;
    private byte[] carParking;
    private List<AbstractCar> cars = new ArrayList<>();
    private List<AbstractCar> trucks = new ArrayList<>();

    public ParkingA() {
        carPlaces = 10;
        truckPlaces = 10;
        truckParking = new byte[truckPlaces];
        carParking = new byte[carPlaces];
        Arrays.fill(truckParking, (byte) 0);
        Arrays.fill(carParking, (byte) 0);
    }

    public ParkingA(int carPlaces, int truckPlaces) {
        this.carPlaces = carPlaces;
        this.truckPlaces = truckPlaces;
        truckParking = new byte[truckPlaces];
        carParking = new byte[carPlaces];
        Arrays.fill(truckParking, (byte) 0);
        Arrays.fill(carParking, (byte) 0);
    }

    public int getCarPlaces() {
        return carPlaces;
    }

    public int getTruckPlaces() {
        return truckPlaces;
    }

    public int getCarEmptyPlaces() {
        int count = 0;
        for (int i = 0; i < carParking.length; i++) {
            if (carParking[i] == 0) {
                count++;
            }
        }
        return count;
    }

    public int getTruckEmptyPlaces() {
        int count = 0;
        for (int i = 0; i < truckParking.length; i++) {
            if (truckParking[i] == 0) {
                count++;
            }
        }
        return count;
    }

    private void fillTruckParking() {
        for (int i = 0; i < truckParking.length; i++) {
            if (truckParking[i] == 0) {
                truckParking[i] = 1;
                break;
            }
        }
    }

    private void fillCarParking(int size) {
//        int count = 0;
//        for (int i = 0; i < test.length; i++) {
//            if (test[i] == 0) {
//                test[i] = 1;
//                count++;
//                if (count == 5) {
//                    break;
//                }
//            }
//        }
        int count = 0;
        for (int i = 0; i < carParking.length; i++) {
            if (carParking[i] == 0) {
                carParking[i] = 1;
                count++;
                if (count == size) {
                    break;
                }
            }
        }
    }

    @Override
    public boolean accept(AbstractCar car) {
        boolean isTruck = car.getClass().getCanonicalName().equals("Truck");
        if (isTruck) {
            if (getTruckEmptyPlaces() > 0) {
                return true;
            }
            if (getCarEmptyPlaces() >= car.getSize()) {
                return true;
            }
        }
        return getCarEmptyPlaces() > 0;
    }

    @Override
    public void parking(AbstractCar car) {
        boolean isTruck = car.getClass().getCanonicalName().equals("Truck");
        if (isTruck) {
            if (getTruckEmptyPlaces() > 0) {
                fillTruckParking();
            } else {
                fillCarParking(car.getSize());
            }
            trucks.add(car);
        }
        fillCarParking(1);
        cars.add(car);
    }

    public List<AbstractCar> getAll() {
        List<AbstractCar> result = new ArrayList<>(cars);
        result.addAll(trucks);
        return result;
    }

    public byte[] getArrayCarParking() {
        return carParking;
    }

    public byte[] getArrayTruckParking() {
        return truckParking;
    }
}
