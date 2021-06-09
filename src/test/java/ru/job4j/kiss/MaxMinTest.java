package ru.job4j.kiss;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MaxMinTest {

    @Test
    public void whenMaxElement() {
        MaxMin maxMin = new MaxMin();
        List<Integer> nums = List.of(3, 4, 5, 1, 6, 9, 13, 0, 5);
        Integer result = maxMin.max(nums, Comparator.naturalOrder());
        Integer expected = 13;
        assertThat(result, is(expected));
    }

    @Test
    public void whenMinElement() {
        MaxMin maxMin = new MaxMin();
        List<Integer> nums = List.of(3, 4, 5, 1, 6, 9, 13, 0, 5);
        Integer result = maxMin.min(nums, Comparator.naturalOrder());
        Integer expected = 0;
        assertThat(result, is(expected));
    }

    @Test
    public void whenStringElement() {
        MaxMin maxMin = new MaxMin();
        List<String> strings = List.of("0", "2", "4", "3");
        String result = maxMin.max(strings, Comparator.naturalOrder());
        String expected = "4";
        assertThat(result, is(expected));
    }
}