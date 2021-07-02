package ru.job4j.ocp;

import java.util.Random;

/**
 * в классе происходит нарушение принципа OCP, так как при наследовании теряется гибкость программы, а следовательно
 * при необходимости расширить функционал программы нужно будет переписывать всю архитектуру проекта
 */
public class StringGenerator {
    private static final String SET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private String generate(int size) {
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        char[] symbols = SET.toCharArray();
        for (int i = 0; i < size; i++) {
            char symbol = symbols[random.nextInt(symbols.length - 1)];
            result.append(symbol);
        }
        System.out.println("Сгенерированная строка <" + result + ">.");
        return result.toString();
    }
}
