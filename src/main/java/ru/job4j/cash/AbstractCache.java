package ru.job4j.cash;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        SoftReference<V> valueRef = new SoftReference<>(value);
        cache.put(key, valueRef);
    }

    public V get(K key) {
        V value = null;
        SoftReference<V> valueRef = cache.get(key);
        if (valueRef != null) {
            value = valueRef.get();
        }
        if (value == null) {
            value = load(key);
            put(key, value);
        }
        return value;
    }

    protected abstract V load(K key);
}
