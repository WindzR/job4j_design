package ru.job4j.collection.set;

import ru.job4j.collection.SimpleArray;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {
    private SimpleArray<T> set = new SimpleArray<>();
    private int size = 0;

    @Override
    public boolean add(T value) {
        boolean result = false;
        if (isEmpty() || !contains(value)) {
            set.add(value);
            result = true;
            size++;
        }
        return result;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T value) {
        boolean result = false;
        for (T element : set) {
            if (Objects.equals(element, value)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
