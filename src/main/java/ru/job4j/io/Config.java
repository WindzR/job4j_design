package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Config {
    private final String path;
    private Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            for (String line = read.readLine(); line != null; line = read.readLine()) {
                if (isPermissible(line)) {
                    String[] temp = getArray(line);
                    values.put(temp[0], temp[1]);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private boolean isPermissible(String line) {
        return !line.isEmpty() && !line.startsWith("#");
    }

    private String[] getArray(String line) {
        String[] result = line.split("=");
        if (result.length != 2 || result[0].isEmpty() || result[1].isEmpty()) {
            throw new IllegalArgumentException();
        }
        return result;
    }

    public String value(String key) {
        if (!values.containsKey(key)) {
            System.out.println("Keys is not found!");
            return "";
        }
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Config test = new Config("app.properties");
    }
}
