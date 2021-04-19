package ru.job4j.collection;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        Map<Integer, String> curr = current.stream().collect(Collectors.toMap(User::getId, User::getName));
        for (User user : previous) {
            if (curr.containsKey(user.getId())) {
                if (!curr.get(user.getId()).equals(user.getName())) {
                    info.changed++;
                }
            } else {
                info.deleted++;
            }
        }
        info.added = current.size() - previous.size() + info.deleted;
        return info;
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;
    }
}
