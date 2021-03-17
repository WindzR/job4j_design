package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {
    private int size;
    private int modCount;
    private Node<E> first;
    private Node<E> last;

    public SimpleLinkedList() {
        size = 0;
        modCount = 0;
        first = null;
        last = null;
    }

    public int size() {
        return size;
    }

    @Override
    public void add(E value) {
        if (first == null) {
            first = new Node<E>(value, null);
            last = first;
        } else {
            Node<E> node = new Node<E>(value, null);
            last.next = node;
            last = node;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        E result;
        if (index == 0) {
            result = first.item;
        } else if (index == size - 1) {
            result = last.item;
        } else {
            Node<E> nextNode = first.next;
            for (int i = 1; i < index; i++) {
                nextNode = nextNode.next;
            }
            result = nextNode.item;
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {
            private final int expectedModCount = modCount;
            private Node<E> currentNode = first;

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public E next() {
                checkModCount();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E result = currentNode.item;
                currentNode = currentNode.next;
                return result;
            }

            private void checkModCount() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
        return it;
    }

    private static class Node<E> {
        E item;
        Node<E> next;

        private Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}
