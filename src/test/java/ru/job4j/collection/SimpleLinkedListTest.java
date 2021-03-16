package ru.job4j.collection;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.hamcrest.core.Is;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedListTest {

    @Test
    public void whenAddOneNode() {
        var simpleLinkedList = new SimpleLinkedList<String>();
        simpleLinkedList.add("first");
        assertThat(simpleLinkedList.get(0), is("first"));
    }

    @Test
    public void whenAddTwoNodes() {
        var simpleLinkedList = new SimpleLinkedList<String>();
        simpleLinkedList.add("first");
        simpleLinkedList.add("second");
        assertThat(simpleLinkedList.get(1), is("second"));
    }

    @Test
    public void whenAddThreeNodes() {
        var simpleLinkedList = new SimpleLinkedList<String>();
        simpleLinkedList.add("first");
        simpleLinkedList.add("second");
        simpleLinkedList.add("third");
        assertThat(simpleLinkedList.get(1), is("second"));
    }
}