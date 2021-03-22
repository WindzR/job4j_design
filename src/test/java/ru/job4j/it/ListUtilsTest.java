package ru.job4j.it;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfter() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 4));
        ListUtils.addAfter(input, 1, 3);
        assertThat(Arrays.asList(1, 2, 3, 4), Is.is(input));
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 4));
        ListUtils.addAfter(input, 2, 5);
        assertThat(Arrays.asList(1, 2, 4, 5), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAfterWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 4));
        ListUtils.addBefore(input, 3, 3);
    }

    @Test
    public void whenRemoveIfMoreThan10() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 11, 2, 32, 3, 15, 4, 68));
        ListUtils.removeIf(input, a -> a > 10);
        assertThat(Arrays.asList(1, 2, 3, 4), Is.is(input));
    }

    @Test
    public void whenRemoveIfContainsIvan() {
        List<String> input = new ArrayList<>(
                Arrays.asList("Petr Ivanov", "Igor Lavrov", "Ivan Kozlov", "Fedor Kislov", "Ivan Taranov", "Nikolai Epshtein"));
        ListUtils.removeIf(input, a -> a.contains("Ivan"));
        assertThat(
                Arrays.asList("Igor Lavrov", "Fedor Kislov", "Nikolai Epshtein"),
                Is.is(input));
    }

    @Test
    public void whenReplaceIfMoreThan10() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 11, 2, 32, 3, 15, 4, 68));
        ListUtils.replaceIf(input, a -> a > 10, 0);
        assertThat(Arrays.asList(1, 0, 2, 0, 3, 0, 4, 0), Is.is(input));
    }

    @Test
    public void whenReplaceIfContainsIvan() {
        List<String> input = new ArrayList<>(
                Arrays.asList("Petr Ivanov", "Igor Lavrov", "Ivan Kozlov", "Fedor Kislov", "Ivan Taranov", "Nikolai Epshtein"));
        ListUtils.replaceIf(input, a -> a.contains("Ivan"), "ReplaceItem");
        assertThat(
                Arrays.asList("ReplaceItem", "Igor Lavrov", "ReplaceItem", "Fedor Kislov", "ReplaceItem", "Nikolai Epshtein"),
                Is.is(input));
    }

    @Test
    public void whenListBContainsListB() {
        List<Integer> listA = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> listB = new ArrayList<>(Arrays.asList(1, 4));
        ListUtils.removeAll(listA, listB);
        assertThat(Arrays.asList(2, 3), Is.is(listA));
    }

    @Test
    public void whenListANotContainsListB() {
        List<Integer> listA = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> listB = new ArrayList<>(Arrays.asList(0, 5));
        ListUtils.removeAll(listA, listB);
        assertThat(Arrays.asList(1, 2, 3, 4), Is.is(listA));
    }
}