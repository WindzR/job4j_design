package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    /**
     * @param elements[] - массив универсальных элементов, инициализируется при создании
     * объекта SimpleArray c @param size - размер массива;
     * @param index - счетчик добавленных/удаленных элементов
     */
    private T[] elements;
    private int index;

    public SimpleArray(int size) {
        elements = (T[]) new Object[size];
    }

    /**
     * добавление @param model в первую свободную ячейку массива elements
     * @return результат добавления - true/false
     */
    public boolean add(T model) {
        boolean rsl = false;
        if (index < elements.length) {
            elements[index++] = model;
            rsl = true;
        }
        return rsl;
    }

    /**
     * замена, указанным элементом, элемента по указанному индексу
     * @param setIndex - индекс заменяемого элемента
     * @param model - объект, которым хотим заменить
     */
    public boolean set(int setIndex, T model) {
       boolean rsl = false;
       if (Objects.checkIndex(setIndex, index) >= 0) {
           elements[setIndex] = model;
           rsl = true;
       }
       return rsl;
    }

    /**
     * удаление элемента по указанному индексу
     * @param removeIndex - индекс элемента, который хотим удалить
     * @return результат удаления - true/false
     */
    public boolean remove(int removeIndex) {
        boolean rsl = false;
        if (Objects.checkIndex(removeIndex, index) >= 0) {
            System.arraycopy(elements, removeIndex + 1, elements, removeIndex, elements.length - removeIndex - 1);
            rsl = true;
        }
        return rsl;
    }

    /**
     * получение элемента, расположенного по указанному индексу
     * @param getIndex - индекс элемента, который хотим получить
     * @return Object - элемент массива, который хотим получить
     */
    public Object get(int getIndex) {
        return Objects.checkIndex(getIndex, elements.length) >= 0 ? elements[getIndex] : null;
    }

    /**
     * возвращает итератор, предназначенный для обхода массива elements
     * @return итератор для elements[]
     */
    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < index;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return elements[currentIndex++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }
}
