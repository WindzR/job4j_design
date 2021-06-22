package ru.job4j.collection.exam;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class EmailsTest {

    @Test
    public void whenAddUser() {
        Emails emails = new Emails();
        HashMap<String, HashSet<String>> expected = new HashMap<>();
        User user = new User("Ivan", Arrays.asList("aaa@mail.ru", "bbb@ya.ru"));
        emails.addUsers(user);
        HashSet<String> mails = new HashSet<>(Arrays.asList("aaa@mail.ru", "bbb@ya.ru"));
        expected.put("Ivan", mails);
        assertThat(expected.entrySet(), is(emails.getMapEmails().entrySet()));
    }

    @Test
    public void whenNotGeneralMails() {
        Emails emails = new Emails();
        HashMap<String, HashSet<String>> expected = new HashMap<>();
        User user1 = new User("Ivan", Arrays.asList("aaa@mail.ru", "bbb@ya.ru"));
        User user2 = new User("Petr", Arrays.asList("ccc@mail.ru", "ddd@ya.ru"));
        emails.addUsers(user1);
        emails.addUsers(user2);
        emails.mergeEmails();
        HashSet<String> mailsIvan = new HashSet<>(Arrays.asList("aaa@mail.ru", "bbb@ya.ru"));
        expected.put("Ivan", mailsIvan);
        HashSet<String> mailsPetr = new HashSet<>(Arrays.asList("ccc@mail.ru", "ddd@ya.ru"));
        expected.put("Petr", mailsPetr);
        assertThat(expected.entrySet(), is(emails.getMapEmails().entrySet()));
    }

    @Test
    public void whenHasGeneralMails() {
        Emails emails = new Emails();
        User user1 = new User("User1", Arrays.asList("aaa@mail.ru", "bbb@mail.ru"));
        User user2 = new User("User2", Arrays.asList("ccc@mail.ru", "aaa@mail.ru"));
        emails.addUsers(user1);
        emails.addUsers(user2);
        HashMap<String, HashSet<String>> result = emails.mergeEmails();
        HashSet<String> expected = new HashSet<>(Arrays.asList("aaa@mail.ru", "bbb@mail.ru", "ccc@mail.ru"));
        assertThat(expected, is(result.get("User2")));
    }

    @Test
    public void when4UsersHasGeneralMails() {
        Emails emails = new Emails();
        HashMap<String, HashSet<String>> expected = new HashMap<>();
        User user1 = new User("User1", Arrays.asList("aaa@mail.ru", "bbb@mail.ru"));
        User user2 = new User("User2", Arrays.asList("ccc@mail.ru", "aaa@mail.ru"));
        User user3 = new User("User3", Arrays.asList("ddd@mail.ru", "fff@mail.ru"));
        User user4 = new User("User4", Arrays.asList("eee@mail.ru", "ccc@mail.ru"));
        emails.addUsers(user1);
        emails.addUsers(user2);
        emails.addUsers(user3);
        emails.addUsers(user4);
        HashSet<String> test1 = new HashSet<>(Arrays.asList("aaa@mail.ru", "bbb@mail.ru", "ccc@mail.ru", "eee@mail.ru"));
        expected.put("User2", test1);
        HashSet<String> test2 = new HashSet<>(Arrays.asList("ddd@mail.ru", "fff@mail.ru"));
        expected.put("User3", test2);
        HashMap<String, HashSet<String>> result = emails.mergeEmails();
        assertThat(expected.entrySet(), is(result.entrySet()));
    }
}