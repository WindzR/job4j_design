package ru.job4j.collection.set;

import org.junit.Test;

import java.util.Iterator;
import java.util.Objects;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void whenAddStringAndNull() {
        Set<String> set = new SimpleSet<>();
        assertTrue(set.add("First"));
        assertTrue(set.add("Second"));
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertTrue(set.contains("Second"));
        assertFalse(set.add("First"));
    }

    @Test
    public void whenAddBothNulls() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add(null);
        set.add(null);
        Iterator<String> it = set.iterator();
        it.next();
        assertFalse(it.hasNext());
    }

    @Test
    public void whenAddNullThenIterator() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(1);
        set.add(null);
        Iterator<Integer> it = set.iterator();
        assertThat(it.next(), is(1));
        assertTrue(Objects.isNull(it.next()));
    }
}