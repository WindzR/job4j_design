package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        fileTreatment("c:\\projects");
        fileTreatment("C:\\projects\\job4j_design\\data");
        fileTreatment("C:\\projects\\job4j_design");
        fileTreatment("src/main/java/ru/job4j/io/LogFilter.java");
    }

    public static void fileTreatment(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        System.out.println("File name: " + file.getName());
        System.out.println("File size: " + file.length());
    }
}
