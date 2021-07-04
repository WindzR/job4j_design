package ru.job4j.lsp.storage;

import java.time.LocalDateTime;

abstract public class AbstractFood {
     private String name;
     private LocalDateTime expireDate;
     private LocalDateTime createDate;
     private int price;
     private boolean discount;
}
