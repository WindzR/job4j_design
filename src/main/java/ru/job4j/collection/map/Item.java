package ru.job4j.collection.map;

public class Item<K, V> {
    private K key;
    private V value;
    private int hash;

    public Item(K key, V value, int hash) {
        this.key = key;
        this.value = value;
        this.hash = hash;
    }

    public int keyHash() {
        return hash;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
