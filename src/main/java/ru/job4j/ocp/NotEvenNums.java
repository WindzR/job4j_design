package ru.job4j.ocp;

import java.util.Arrays;
import java.util.Collections;

public class NotEvenNums implements ArrayOfNumbers<Integer> {

    @Override
    public Integer[] notEven(Integer[] numbers) {
        Integer[] result = {0};
        if (numbers.length == 0) {
            return result;
        }
        return Arrays.stream(numbers)
                .filter(integer -> integer % 2 == 1)
                .toArray(Integer[]::new);
    }
}
