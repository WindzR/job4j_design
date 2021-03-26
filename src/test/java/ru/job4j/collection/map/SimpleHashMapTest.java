package ru.job4j.collection.map;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.*;

public class SimpleHashMapTest {

    @Test
    public void insertSuccess() {
        var hashMap = new SimpleHashMap<Integer, String>();
        assertTrue(hashMap.insert(1, "Ivan"));
        assertTrue(hashMap.insert(2, "Petr"));
        assertTrue(hashMap.insert(3, "Uliana"));
        assertTrue(hashMap.insert(4, "Ruslan"));
    }

    @Test
    public void insertKeyIsEquals() {
        var hashMap = new SimpleHashMap<Integer, String>();
        assertTrue(hashMap.insert(1, "Ivan"));
        assertTrue(hashMap.insert(2, "Petr"));
        assertTrue(hashMap.insert(3, "Uliana"));
        assertTrue(hashMap.insert(3, "Ruslan"));
        assertThat(hashMap.get(3), is("Ruslan"));
    }

    @Test
    public void extendArraySuccess() {
        var hashMap = new SimpleHashMap<Integer, String>(4);
        assertTrue(hashMap.insert(1, "Ivan"));
        assertTrue(hashMap.insert(2, "Petr"));
        assertTrue(hashMap.insert(3, "Uliana"));
        assertTrue(hashMap.insert(4, "Ruslan"));
        assertThat(hashMap.get(1), is("Ivan"));
        assertThat(hashMap.getArraySize(), is(16));
    }

    @Test
    public void whenKeyString() {
        var hashMap = new SimpleHashMap<String, String>();
        assertTrue(hashMap.insert("First", "Ivan"));
        assertTrue(hashMap.insert("Second", "Petr"));
        assertTrue(hashMap.insert("Third", "Uliana"));
        assertTrue(hashMap.insert("Forth", "Ruslan"));
        assertThat(hashMap.get("First"), is("Ivan"));
        assertThat(hashMap.get("Second"), is("Petr"));
        assertThat(hashMap.get("Third"), is("Uliana"));
        assertThat(hashMap.get("Forth"), is("Ruslan"));
    }

    @Test
    public void delete() {
    }
}