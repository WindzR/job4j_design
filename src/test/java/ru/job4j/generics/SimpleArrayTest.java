package ru.job4j.generics;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class SimpleArrayTest {

    @Test
    public void whenAddStringSuccess() {
        var simpleArray = new SimpleArray(1);
        String test = "test";
        simpleArray.add(test);
        assertThat(simpleArray.get(0), is("test"));
    }

    @Test
    public void whenReplaceString() {
        var simpleArray = new SimpleArray(1);
        String test = "test";
        simpleArray.add(test);
        String replace = "replace";
        simpleArray.set(0, replace);
        assertThat(simpleArray.get(0), is("replace"));
    }

    @Test
    public void whenRemoveString() {
        var simpleArray = new SimpleArray(3);
        String test = "test";
        simpleArray.add(test);
        simpleArray.remove(0);
        assertNull(simpleArray.get(0));
    }

    @Test
    public void whenGetElementDoesNotExist() {
        var simpleArray = new SimpleArray(3);
        String test = "test";
        simpleArray.add(test);
        assertNull(simpleArray.get(1));
    }

    @Test
    public void whenIteratorNextAllElements() {
        var simpleArray = new SimpleArray(5);
        for (int i = 0; i < 5; i++) {
            simpleArray.add(i);
        }
        Iterator<Integer> it = simpleArray.iterator();
        assertThat(it.next(), is(0));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
    }

    @Test
    public void whenIteratorHasNextElements() {
        var simpleArray = new SimpleArray(5);
        for (int i = 0; i < 4; i++) {
            simpleArray.add(i);
        }
        Iterator<Integer> it = simpleArray.iterator();
        it.next();
        it.next();
        it.next();
        it.next();
        assertThat(it.hasNext(), is(false));

    }

    @Test (expected = NoSuchElementException.class)
    public void iteratorThrowsExceptionOnWrongNext() {
        var simpleArray = new SimpleArray(4);
        for (int i = 0; i < 4; i++) {
            simpleArray.add(i);
        }
        Iterator<Integer> it = simpleArray.iterator();
        it.next();
        it.next();
        it.next();
        it.next();
        it.next();
    }

    @Test
    public void iteratorReturnsFalseIfHasNext() {
        var simpleArray = new SimpleArray(4);
        for (int i = 0; i < 3; i++) {
            simpleArray.add(i);
        }
        Iterator<Integer> it = simpleArray.iterator();
        it.next();
        it.next();
        it.next();
        assertThat(it.hasNext(), is(false));
    }
}