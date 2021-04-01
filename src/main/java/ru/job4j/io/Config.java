package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class Config {
    private final String path;
    private Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            values = read.lines()
                    .filter(str -> !str.isEmpty() && !str.startsWith("#"))
                    .collect(Collectors.toMap(key -> key.split("=")[0], value -> value.split("=")[1]));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String value(String key) {
        if (values.isEmpty()) {
            throw new UnsupportedOperationException("Don't impl this method yet!");
        }
        String value = null;
        for (Map.Entry<String, String> index : values.entrySet()) {
            if (key.equals(index.getKey())) {
                value = index.getValue();
            }
        }
        return value;
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
