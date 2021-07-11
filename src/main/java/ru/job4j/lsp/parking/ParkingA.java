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

    /**
     * метод, определяющий количество свободных мест на парковке
     * @param machineParking проверяемая стоянка
     * @return количество свободных мест
     */
    public int getEmptyPlaces(byte[] machineParking) {
        int count = 0;
        for (int i = 0; i < machineParking.length; i++) {
            if (machineParking[i] == 0) {
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

    /**
     * метод, проверяющий можно ли запарковать на эту стоянку машину/грузовик
     * @param car проверяемый транспорт
     * @return результат true/false
     */
    @Override
    public boolean accept(AbstractCar car) {
        boolean isTruck = car.getClass().getCanonicalName().contains("Truck");
        if (isTruck) {
            if (getEmptyPlaces(truckParking) > 0) {
                return true;
            }
            return getEmptyPlaces(carParking) >= car.getSize();
        }
        return getEmptyPlaces(carParking) > 0;
    }

    /**
     * метод, определяющий место на стоянке куда будет запаркована машина/грузовик
     * @param car паркуемая машина
     */
    @Override
    public void parking(AbstractCar car) {
        boolean isTruck = car.getClass().getCanonicalName().contains("Truck");
        if (isTruck) {
            if (getEmptyPlaces(truckParking) > 0) {
                fillTruckParking();
            } else {
                fillCarParking(car.getSize());
            }
            trucks.add(car);
            return;
        }
        fillCarParking(1);
        cars.add(car);
    }

    /**
     * метод возвращает список запаркованного транспорта на этой стоянке
     * @return список транспорта
     */
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
