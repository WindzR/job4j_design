package ru.job4j.generics;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class UserStoreTest {

    @Test
    public void whenAddUser() {
        var userStore = new UserStore();
        userStore.add(new User("1", "Dmitry"));
        assertThat(userStore.findById("1").getName(), is("Dmitry"));
    }

    @Test
    public void whenReplaceUser() {
        var userStore = new UserStore();
        userStore.add(new User("1", "Dmitry"));
        userStore.replace("1", new User("1", "Ivan"));
        assertThat(userStore.findById("1").getName(), is("Ivan"));
    }

    @Test
    public void whenDeleteUser() {
        var userStore = new UserStore();
        userStore.add(new User("1", "Dmitry"));
        userStore.delete("1");
        assertNull(userStore.findById("1"));
    }

    @Test
    public void whenUserFindById() {
        var userStore = new UserStore();
        userStore.add(new User("1", "Dmitry"));
        userStore.add(new User("2", "Petr"));
        userStore.add(new User("3", "Ivan"));
        userStore.add(new User("4", "Eugene"));
        assertThat(userStore.findById("4").getName(), is("Eugene"));
    }
}