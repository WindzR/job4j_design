package ru.job4j.collection.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
        assertThat(hashMap.get(4), is("Ruslan"));
        assertThat(hashMap.getArraySize(), is(16));
    }

    @Test
    public void extendArrayStringSuccess() {
        var hashMap = new SimpleHashMap<String, String>(4);
        assertTrue(hashMap.insert("First", "Ivan"));
        assertTrue(hashMap.insert("2", "Petr"));
        assertTrue(hashMap.insert("3", "Uliana"));
        assertTrue(hashMap.insert("Four", "Ruslan"));
        assertThat(hashMap.get("First"), is("Ivan"));
        assertThat(hashMap.get("Four"), is("Ruslan"));
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
    public void deleteSuccess() {
        var hashMap = new SimpleHashMap<Integer, String>();
        assertTrue(hashMap.insert(1, "Ivan"));
        assertTrue(hashMap.insert(2, "Petr"));
        assertTrue(hashMap.insert(3, "Uliana"));
        assertTrue(hashMap.insert(4, "Ruslan"));
        assertTrue(hashMap.delete(3));
        assertNull(hashMap.get(3));
    }

    @Test
    public void whenIterate() {
        var hashMap = new SimpleHashMap<Integer, String>();
        assertTrue(hashMap.insert(1, "Ivan"));
        assertTrue(hashMap.insert(2, "Petr"));
        assertTrue(hashMap.insert(3, "Uliana"));
        assertTrue(hashMap.insert(4, "Ruslan"));
        Iterator<Integer> it = hashMap.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));

    }

    @Test
    public void whenHasNext() {
        var hashMap = new SimpleHashMap<Integer, String>();
        assertTrue(hashMap.insert(1, "Ivan"));
        assertTrue(hashMap.insert(2, "Petr"));
        Iterator<Integer> it = hashMap.iterator();
        assertTrue(it.hasNext());
        assertThat(it.next(), is(1));
        assertTrue(it.hasNext());
        assertThat(it.next(), is(2));
        assertFalse(it.hasNext());

    }

    @Test (expected = NoSuchElementException.class)
    public void whenHasNotNext() {
        var hashMap = new SimpleHashMap<Integer, String>();
        Iterator<Integer> it = hashMap.iterator();
        it.next();
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenWasModification() {
        var hashMap = new SimpleHashMap<Integer, String>();
        assertTrue(hashMap.insert(1, "Ivan"));
        Iterator<Integer> it = hashMap.iterator();
        hashMap.insert(2, "Petr");
        it.next();
    }
}