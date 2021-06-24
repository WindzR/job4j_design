package ru.job4j.collection.exam;

import java.util.*;

public class Emails {
    private HashMap<String, HashSet<String>> mapEmails = new HashMap<>();
    private final static List<String> EMAILS = List.of("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru", "ups@pisem.net",
            "xyz@pisem.net", "vasya@pupkin.com", "aaa@bbb.ru", "petr@mail.ru", "garik@buldog.com", "covid@trahnet.vseh");

    public HashMap<String, HashSet<String>> getMapEmails() {
        return mapEmails;
    }

    /**
     * метод для добавления пользователя (имя, список е-мейлов)
     * @param user модель пользователя
     */
    public void addUsers(User user) {
        HashSet<String> set = new HashSet<>(user.getEmails());
        mapEmails.putIfAbsent(user.getName(), set);
        System.out.println("User " + user.getName() + " successfully added!");
    }

    /**
     * метод для генерации случайного пользователя
     * @param lexeme лексема на основе которой будет называться пользователь,
     * numberEmails количество емейлов у пользователя
     * @return новый пользователь
     */
    public User generateUser(String lexeme, int numberEmails) {
        Random random = new Random();
        int number = random.nextInt(100);
        StringBuilder nameBuilder = new StringBuilder(lexeme);
        nameBuilder.append(number);
        String name = nameBuilder.toString();
        if (mapEmails.containsKey(name)) {
            generateUser(lexeme, numberEmails);
        }
        List<String> emails = new ArrayList<>();
        for (int i = 0; i < numberEmails; i++) {
            int place = random.nextInt(EMAILS.size());
            emails.add(EMAILS.get(place));
        }
        return new User(name, emails);
    }

    /**
     * метод для получения нового списка пользователей, удаляющих пользователей с одинаковыми емейлами
     * использует результаты метода mergeList, который дубликаты пользователей приравнивает к user_name
     * @return HashMap<String, HashSet<String>> новая карта слияния пользователей с одинаковыми емейлами
     */
    public HashMap<String, HashSet<String>> mergeEmails() {
        HashMap<String, HashSet<String>> merge = new HashMap<>();
        for (String name : mapEmails.keySet()) {
            Set<String> origin = mapEmails.get(name);
            mergeList(origin);
        }
        for (String key : mapEmails.keySet()) {
            if (!mapEmails.get(key).contains(key)) {
                merge.put(key, mapEmails.get(key));
            }
        }
        return merge;
    }

    /**
     * метод для слияния списков емейлов,
     * если есть общие емейлы у двух пользователей, то списки емейлов объединяются под одним пользователем,
     * а список второго приравнивается к заглушке (значению списка емейлов присвоиваем имя самого пользователя)
     * @param origin список контрольного пользователя
     * @return итоговый список емейлов пользователя
     */
    private Set<String> mergeList(Set<String> origin) {
        int originSize = origin.size();
        for (String key : mapEmails.keySet()) {
            Set<String> list = mapEmails.get(key);
            HashSet<String> plug = new HashSet<>(Collections.singletonList(key));
            if (list.equals(origin)) {
                continue;
            }
            for (String email : list) {
                if (origin.contains(email)) {
                    origin.addAll(list);
                    mapEmails.put(key, plug);
                }
            }
        }
        //если список емейлов расширился, то надо прогнать по HashMap еще раз, так как могут появиться новые совпадения
        if (originSize != origin.size()) {
            mergeList(origin);
        }
        return origin;
    }

    /**
     * метод для вывода на экран текущего списка пользователей и их емейлов
     */
    public void display(HashMap<String, HashSet<String>> map) {
        for (Map.Entry<String, HashSet<String>> entry : map.entrySet()) {
            System.out.println("Пользователь <" + entry.getKey() + "> список емейлов --> " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        Emails emails = new Emails();
        User user1 = emails.generateUser("vasya", 2);
        User user2 = emails.generateUser("user", 2);
        User user3 = emails.generateUser("padla", 2);
        User user4 = emails.generateUser("liza", 2);
        User user5 = emails.generateUser("4mo", 2);
        emails.addUsers(user1);
        emails.addUsers(user2);
        emails.addUsers(user3);
        emails.addUsers(user4);
        emails.addUsers(user5);
        emails.display(emails.getMapEmails());
        HashMap<String, HashSet<String>> result = emails.mergeEmails();
        System.out.println("<--------------------MERGING LIST------------------->");
        emails.display(result);
    }
}
