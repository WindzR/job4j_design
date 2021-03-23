package ru.job4j.collection.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class TestUser {
    public static void main(String[] args) {
        User user1 = new User("Ivan", 2, new GregorianCalendar(1985, 0, 25));
        User user2 = new User("Ivan", 2, new GregorianCalendar(1985, 0, 25));
        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());
        System.out.println(map);
    }
}
