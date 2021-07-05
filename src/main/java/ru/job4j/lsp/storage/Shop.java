package ru.job4j.lsp.storage;

import java.util.HashMap;
import java.util.Map;

public class Shop implements Depository {
    private Map<String, AbstractFood> shop = new HashMap<>();
    private Map<String, AbstractFood> discount = new HashMap<>();

    @Override
    public void storage(AbstractFood food) {
        if (!food.isDiscount()) {
            shop.put(food.getName(), food);
            System.out.println("Продукт <" + food.getName() + "> отправлен на продажу в магазин.");
        } else {
            discountStorage(food);
        }
    }

    private void discountStorage(AbstractFood food) {
        float discontSize = food.getPrice() * (1 - food.getDiscount());
        float discontPrice = food.getPrice() * food.getDiscount();
        food.setPrice(discontPrice);
        System.out.printf("На продукт <%s> применена скидка в размере <%.2f>", food.getName(), discontSize);
        discount.put(food.getName(), food);
    }

    public AbstractFood getFood(String name) {
        if (shop.containsKey(name)) {
            return shop.get(name);
        }
        return discount.get(name);
    }
}
