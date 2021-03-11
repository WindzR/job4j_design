package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] container;
    private int size = 0;
    private int modCount = 0;
    private int index = 0;

    public SimpleArray() {
        container = new Object[10];
        size = 10;
    }

    public SimpleArray(int size) {
        container = new Object[size];
        this.size = size;
    }

    public boolean add(T model) {
        if (index == container.length) {
            extendArray();
        }
        container[index++] = model;
        modCount++;
        return true;
    }

    private void extendArray() {
        size = container.length + 10;
        container = Arrays.copyOf(container, size);
    }

    public boolean set(int setIndex, T model) {
        boolean rsl = false;
        if (Objects.checkIndex(setIndex, container.length) >= 0) {
            container[setIndex] = model;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    public boolean remove(int removeIndex) {
        boolean rsl = false;
        if (Objects.checkIndex(removeIndex, index) >= 0) {
            System.arraycopy(container, removeIndex + 1, container, removeIndex, container.length - removeIndex - 1);
            container[index] = null;
            modCount++;
            index--;
            rsl = true;
        }
        return rsl;
    }
    public T get(int getIndex) {
        return Objects.checkIndex(getIndex, index) >= 0 ? (T) container[getIndex] : null;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {
            private final int expectedModCount = modCount;
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < index;
            }

            @Override
            public T next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) container[currentIndex++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }
}
