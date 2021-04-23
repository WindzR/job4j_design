package ru.job4j.io.serialization.json;

import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

public class Premises {
    private boolean living;
    private int area;
    private String model;
    private Address address;
    private String[] rooms;

    public Premises(boolean living, int area, String model, Address address, String[] rooms) {
        this.living = living;
        this.area = area;
        this.model = model;
        this.address = address;
        this.rooms = rooms;
    }

    public boolean isLiving() {
        return living;
    }

    public int getArea() {
        return area;
    }

    public String getModel() {
        return model;
    }

    public Address getAddress() {
        return address;
    }

    public String[] getRooms() {
        return rooms;
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
        Address address = new Address("Marksa", 21, 33);
        JSONObject jsonAddress = new JSONObject(address);
        jsonAddress.put("street", address.getStreet());
        jsonAddress.put("house", address.getHouse());
        jsonAddress.put("flat", address.getFlat());

        Premises premise = new Premises(true, 40, "RPC-21", address, new String[] {"kitchen", "hall", "hallway"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("living", premise.isLiving());
        jsonObject.put("area", premise.getArea());
        jsonObject.put("model", premise.getModel());
        jsonObject.put("address", jsonAddress);
        jsonObject.put("rooms", new JSONArray(premise.getRooms()));

        System.out.println(jsonObject.toString());
        System.out.println(new JSONObject(premise).toString());
    }
}
