package ru.job4j.dip;

import java.util.HashMap;
import java.util.Map;

/**
 * Чтобы класс не нарушал принцип DIP, нужно чтобы BookStorage реализовывал интерфейс Storage
 * с методами storage и print.
 */
public class BookStorage {
    private Map<String, SimpleBook> books = new HashMap<>();

    public void storage(SimpleBook book) {
        if (books.containsKey(book.getName())) {
            throw new IllegalArgumentException("Уже есть такая книга!");
        }
        books.put(book.getName(), book);
    }

    public void print(Map<String, SimpleBook> store) {
        store.forEach((key, book) -> {
            System.out.println("Название книги <" + key + "> , содержание -- " + book.getContent());
        });
    }
}
