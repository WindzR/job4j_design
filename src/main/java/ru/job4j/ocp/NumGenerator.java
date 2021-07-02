package ru.job4j.ocp;

import java.util.Random;

public class NumGenerator extends StringGenerator {
    private static final String SET = "0123456789";

    private long generate(int size) {
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        char[] symbols = SET.toCharArray();
        for (int i = 0; i < size; i++) {
            char symbol = symbols[random.nextInt(symbols.length - 1)];
            result.append(symbol);
        }
        System.out.println("Сгенерированная цифра <" + result + ">.");
        return Long.parseLong(result.toString());
    }
}
