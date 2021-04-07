package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        /* TODO parse args to values. */
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments is absent!");
        }
        String[] temp;
        for (int i = 0; i < args.length; i++) {
            temp = args[i].split("=");
            if (temp.length != 2) {
                throw new IllegalArgumentException("Incorrect input of arguments!");
            }
            temp[0] = temp[0].substring(1);
            values.put(temp[0], temp[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
