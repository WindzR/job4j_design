package ru.job4j.lsp.storage;

import java.util.HashMap;
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

    public AbstractFood getFood(String name) {
        return trash.get(name);
    }
}
