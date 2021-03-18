package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int size;

    public T poll() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        T temp = null;
        if (!out.isEmpty()) {
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
