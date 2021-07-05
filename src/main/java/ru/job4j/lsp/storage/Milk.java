package ru.job4j.lsp.storage;

import java.time.LocalDateTime;
import java.util.Objects;

public class Milk extends AbstractFood {
    private String name;
    private LocalDateTime expireDate;
    private LocalDateTime createDate;
    private float price;
    private float discount = 0.6f;

    public Milk() {
    }

    public Milk(String name, LocalDateTime expireDate, LocalDateTime createDate, float price) {
        this.name = name;
        this.expireDate = expireDate;
        this.createDate = createDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDateTime expireDate) {
        this.expireDate = expireDate;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Milk milk = (Milk) o;
        return price == milk.price &&
                name.equals(milk.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
