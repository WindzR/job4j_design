package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int size;

    public T poll() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        if (out.getSize() == 0) {
            while (!(in.getSize() == 0)) {
                out.push(in.pop());
            }
        }
        T temp = null;
        if (!(out.getSize() == 0)) {
            temp = out.pop();
            size--;
        }
        return temp;
    }

    public void push(T value) {
        in.push(value);
        size++;
    }

    private boolean isEmpty() {
        return size == 0;
    }
}
