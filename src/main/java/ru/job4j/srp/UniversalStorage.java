package ru.job4j.srp;

import java.util.Map;

public interface UniversalStorage<K, V> {
    /**
     * Нарушаем принцип SRP, т. к. классы реализующие этот интерфейс
     * имеют более 2х возложенных на них функций
     */
    void storage(V object);
    void print(Map<K, V> store);
}
