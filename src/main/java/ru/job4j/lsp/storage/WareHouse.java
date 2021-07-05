package ru.job4j.lsp.storage;

import java.util.HashMap;
import java.util.Map;

public class WareHouse implements Depository {
    private Map<String, AbstractFood> wareHouse = new HashMap<>();

    @Override
    public void storage(AbstractFood food) {
        wareHouse.put(food.getName(), food);
        System.out.println("Продукт <" + food.getName() + "> отправлен на хранение на склад.");
    }

    public AbstractFood getFood(String name) {
        return wareHouse.get(name);
    }
}
