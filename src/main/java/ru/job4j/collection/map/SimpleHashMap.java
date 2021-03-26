package ru.job4j.collection.map;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class SimpleHashMap<K, V> implements Iterable<K> {
    private final static float LOAD_FACTOR = 0.5F;
    private Item<K, V>[] hashArray;
    private int arraySize;
    private int size;
    private int modCount = 0;

    public SimpleHashMap() {
        arraySize = 16;
        hashArray = new Item[16];
    }

    public SimpleHashMap(int size) {
        arraySize = size;
        hashArray = new Item[size];
    }

    public int hash(Object o) {
        int checkIndex = o.hashCode() % arraySize;
        return o.hashCode() > 0 ? checkIndex : (-1) * checkIndex;
    }

    private void extendArray() {
        arraySize = arraySize * 2;
        hashArray = Arrays.copyOf(hashArray, arraySize);
    }

    private boolean checkLoadFactor() {
        return (float) (size + 1) / arraySize >= LOAD_FACTOR;
    }

    private boolean keyEquals(Item<K, V> itemArray, Item<K, V> itemInsert) {
        boolean keyEqual = false;
        if (itemArray.keyHash() == itemInsert.keyHash()) {
            keyEqual = Objects.equals(itemArray.getKey(), itemInsert.getKey());
        }
        return keyEqual;
    }

    private boolean keyEquals(K key, Item<K, V> itemArray) {
        boolean keyEqual = false;
        if (itemArray.keyHash() == key.hashCode()) {
            keyEqual = Objects.equals(itemArray.getKey(), key);
        }
        return keyEqual;
    }

    public boolean insert(K key, V value) {
        if (checkLoadFactor()) {
            extendArray();
        }
        int index = hash(key);
        int keyHash = key.hashCode();
        Item<K, V> item = new Item<>(key, value, keyHash);
        if (hashArray[index] != null
                && !keyEquals(hashArray[index], item)) {
            return false;
        }
        hashArray[index] = item;
        size++;
        modCount++;
        return true;
    }

    public V get(K key) {
        int index = hash(key);
        if (hashArray[index] == null
                || !keyEquals(key, hashArray[index])) {
            return null;
        }
        return hashArray[index].getValue();
    }

    public boolean delete(K key) {
        int index = hash(key);
        if (hashArray[index] == null
                || !keyEquals(key, hashArray[index])) {
            return false;
        }
        hashArray[index] = null;
        size--;
        modCount++;
        return true;
    }

    public int getArraySize() {
        return arraySize;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }

    public static void main(String[] args) {
        SimpleHashMap s = new SimpleHashMap<Integer, String>(58);
        System.out.println(s.hash(s.arraySize));
    }
}
