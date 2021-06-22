package ru.job4j.collection.exam;

import java.util.List;
import java.util.Objects;

public class User {
    private String name;
    private List<String> emails;

    public User() {
    }

    public User(String name, List<String> emails) {
        this.name = name;
        this.emails = emails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
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
        return name.equals(user.name) && emails.equals(user.emails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, emails);
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", emails=" + emails
                + '}';
    }
}
