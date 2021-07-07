package ru.job4j.lsp.storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WareHouse implements Depository {
    private Map<String, AbstractFood> wareHouse = new HashMap<>();

    @Override
    public void storage(AbstractFood food) {
        wareHouse.put(food.getName(), food);
        System.out.println("Продукт <" + food.getName() + "> отправлен на хранение на склад.");
    }

    public List<AbstractFood> getAll() {
        return new ArrayList<>(wareHouse.values());
    }

    @Override
    public boolean accept(double shelfLife) {
        return shelfLife >= 0 && shelfLife < 25;
    }
}
