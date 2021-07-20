package ru.job4j.dip;

import java.util.Objects;

public class CitezenRF {
    private String name;
    private int age;
    private int mobileNumber;
    private String email;
    private double income;

    public CitezenRF(String name, int age, int mobileNumber, String email, double income) {
        this.name = name;
        this.age = age;
        this.mobileNumber = mobileNumber;
        this.email = email;
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

    public int getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(int mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
