package ru.job4j.lsp;

import java.util.Objects;

public class Candidate {
    private int weight;
    private int height;
    private int age;

    public Candidate(int weight, int height, int age) {
        this.weight = weight;
        this.height = height;
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Candidate candidate = (Candidate) o;
        return weight == candidate.weight && height == candidate.height && age == candidate.age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, height, age);
    }
}
