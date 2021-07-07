package ru.job4j.lsp.storage;

import java.util.List;

/**
 * интерфейс Depository реализует стратегию хранения продуктов в зависимости от установленного срока годности продуктов
 */
public interface Depository {

    void storage(AbstractFood food);

    List<AbstractFood> getAll();

    boolean accept(double shelfLife);
}
