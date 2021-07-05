package ru.job4j.lsp.storage;

import java.util.ArrayList;
import java.util.List;

/**
 * интерфейс Depository реализует стратегию хранения продуктов в зависимости от установленного срока годности продуктов
 */
public interface Depository {

    void storage(AbstractFood food);

    /**
     * введен для проверки работы тестами
     * @param name имя продукта
     * @return проверяемый продукт
     */
    AbstractFood getFood(String name);
}
