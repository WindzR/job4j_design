package ru.job4j.lsp.storage;

import java.time.LocalDateTime;

abstract public class AbstractFood {
     private String name;
     private LocalDateTime expireDate;
     private LocalDateTime createDate;
     private float price;
     private float discount;
     private boolean isDiscount = false;

     abstract String getName();

     abstract LocalDateTime getExpireDate();

     abstract LocalDateTime getCreateDate();

     abstract float getPrice();

     abstract void setPrice(float price);

     abstract float getDiscount();

     public void setUpDiscount(boolean accept) {
          isDiscount = accept;
     }

     public boolean isDiscount() {
          return isDiscount;
     }
}
