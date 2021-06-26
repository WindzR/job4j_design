package ru.job4j.srp;

import java.util.HashMap;
import java.util.Map;

public class BookStorage implements UniversalStorage<String, SimpleBook> {
    private Map<String, SimpleBook> books = new HashMap<>();

    @Override
    public void storage(SimpleBook book) {
        if (books.containsKey(book.getName())) {
            throw new IllegalArgumentException("Уже есть такая книга!");
        }
        books.put(book.getName(), book);
    }

    @Override
    public void print(Map<String, SimpleBook> store) {
        store.forEach((key, book) -> {
            System.out.println("Название книги <" + key + "> , содержание -- " + book.getContent());
        });
    }
}
