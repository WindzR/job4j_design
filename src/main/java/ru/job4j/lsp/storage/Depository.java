package ru.job4j.lsp.storage;

import java.util.ArrayList;
import java.util.List;

public interface Depository {
//    List<? extends AbstractFood> DEPOSITORY = new ArrayList<>();

    void storage(AbstractFood food);
}
