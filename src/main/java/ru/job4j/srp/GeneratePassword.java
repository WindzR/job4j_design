package ru.job4j.srp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * данный класс нарушает принцип SRP, так как реализует 2 разные функции для сущностей
 */
public class GeneratePassword {
    public static final String SET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private String path;

    public GeneratePassword(String path) {
        this.path = path;
    }

    /**
     * метод генерирует случайную строковую последовательность заданной длины
     * @param size заданная длина
     * @return сгенерированная строка
     */
    private String generatePassword(int size) {
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        char[] symbols = SET.toCharArray();
        for (int i = 0; i < size; i++) {
            char symbol = symbols[random.nextInt(symbols.length - 1)];
            result.append(symbol);
        }
        System.out.println("Сгенерированная последовательность <" + result + ">.");
        return result.toString();
    }

    /**
     * сохраняем строку в файл
     * @param password последовательность, которую надо сохранить
     */
    private void save(String password) {
        try (BufferedWriter out = new BufferedWriter(
                new FileWriter(path))) {
            out.write(password);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        GeneratePassword password = new GeneratePassword("data/password.txt");
        String symbols = password.generatePassword(12);
        password.save(symbols);
    }
}
