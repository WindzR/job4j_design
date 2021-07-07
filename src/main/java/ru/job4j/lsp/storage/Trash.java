package ru.job4j.lsp.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trash implements Depository {
    private Map<String, AbstractFood> trash = new HashMap<>();

    /**
     * заносим данные о выброшенном продукте в trash
     * @param food выбрасываемый продукт
     */
    @Override
    public void storage(AbstractFood food) {
        trash.put(food.getName(), food);
        System.out.println("Срок годности <" + food.getName() + "> истек и он подлежит утилизации!");
    }

    public List<AbstractFood> getAll() {
        return new ArrayList<>(trash.values());
    }

    @Override
    public boolean accept(double shelfLife) {
        return shelfLife == 100;
    }
}
