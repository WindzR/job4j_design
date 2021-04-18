package ru.job4j.io.serialization.json;

import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Premises {
    private final boolean living;
    private final int area;
    private final String model;
    private final Address address;
    private final String[] rooms;

    public Premises(boolean living, int area, String model, Address address, String[] rooms) {
        this.living = living;
        this.area = area;
        this.model = model;
        this.address = address;
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "Premises{"
                + "living=" + living
                + ", area=" + area
                + ", model='" + model + '\''
                + ", address=" + address
                + ", rooms=" + Arrays.toString(rooms)
                + '}';
    }

    public static void main(String[] args) {
        final Premises premise = new Premises(true, 40, "DTF-14", new Address("Lenina", 13, 42), new String[]{"kitchen", "hallway","hall"});
        final Gson gson = new GsonBuilder().create();
        String premiseJson = gson.toJson(premise);
        System.out.println(premiseJson);
        final Premises premiseMod = gson.fromJson(premiseJson, Premises.class);
        System.out.println(premiseMod);
    }
}
