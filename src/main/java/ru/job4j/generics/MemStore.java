package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;

public class MemStore<T extends Base> implements Store<T> {
    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean rsl = false;
        int index = findIndexById(id);
        if (index >= 0) {
            mem.set(index, model);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public boolean delete(String id) {
        boolean rsl = false;
        int index = findIndexById(id);
        if (index >= 0) {
            mem.remove(index);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public T findById(String id) {
        int index = findIndexById(id);
        return index >= 0 ? mem.get(index) : null;
    }

    public int findIndexById(String id) {
        int searchId = -1;
        for (int index = 0; index < mem.size(); index++) {
            if (mem.get(index).getId().equals(id)) {
                searchId = index;
                break;
            }
        }
        return searchId;
    }
}
