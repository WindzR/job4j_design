package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public static List<String> filter(String file) {
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            return in.lines()
                    .filter(str -> str.contains("404"))
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Collections.emptyList();
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
    }
}
