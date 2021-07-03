package ru.job4j.lsp;

import java.util.Objects;

public class User {
    private String name;
    private int socialRating;
    private SmartPhone smartPhone;

    public User(String name, int socialRating, SmartPhone smartPhone) {
        this.name = name;
        this.socialRating = socialRating;
        this.smartPhone = smartPhone;
    }

    public String getName() {
        return name;
    }

    public int getSocialRating() {
        return socialRating;
    }

    public void setSocialRating(int socialRating) {
        this.socialRating = socialRating;
    }

    public SmartPhone getSmartPhone() {
        return smartPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return socialRating == user.socialRating && name.equals(user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, socialRating);
    }
}
