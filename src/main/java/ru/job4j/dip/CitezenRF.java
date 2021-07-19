package ru.job4j.dip;

import java.util.Objects;

public class CitezenRF {
    private String name;
    private int age;
    private double income;

    public CitezenRF(String name, int age, double income) {
        this.name = name;
        this.age = age;
        this.income = income;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CitezenRF citezenRF = (CitezenRF) o;
        return age == citezenRF.age
                && Double.compare(citezenRF.income, income) == 0
                && name.equals(citezenRF.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, income);
    }

    @Override
    public String toString() {
        return "CitezenRF {"
                + "name='" + name + '\''
                + ", age=" + age
                + ", income=" + income
                + '}';
    }
}
