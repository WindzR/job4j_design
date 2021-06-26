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
        if (cache.get(key) == null || !cache.containsKey(key)) {
            load(key);
        }
        SoftReference<V> value = cache.get(key);
        return value.get();
    }

    protected abstract V load(K key);
}
