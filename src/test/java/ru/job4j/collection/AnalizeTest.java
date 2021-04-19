package ru.job4j.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalizeTest {

    @Test
    public void whenAdded() {
        Analize analize = new Analize();
        List<Analize.User> previous = List.of();
        List<Analize.User> current = List.of(
                new Analize.User(1, "Ivan")
        );
        Analize.Info info = analize.diff(previous, current);
        assertThat(new int[]{info.added, info.deleted, info.changed},
                is(new int[]{1, 0, 0}));
    }

    @Test
    public void whenEmpty() {
        Analize analize = new Analize();
        List<Analize.User> previous = new ArrayList<>();
        List<Analize.User> current = new ArrayList<>();
        Analize.Info info = analize.diff(previous, current);
        assertThat(new int[]{info.added, info.deleted, info.changed},
                is(new int[]{0, 0, 0}));
    }

    @Test
    public void whenAddedAndDeleted() {
        Analize analize = new Analize();
        List<Analize.User> previous = List.of(
                new Analize.User(1, "Ivan"),
                new Analize.User(2, "Petr")
        );
        List<Analize.User> current = List.of(
                new Analize.User(1, "Ivan"),
                new Analize.User(3, "Vladimir")
        );
        Analize.Info info = analize.diff(previous, current);
        assertThat(new int[]{info.added, info.deleted, info.changed},
                is(new int[]{1, 1, 0}));
    }

    @Test
    public void whenChanged() {
        Analize analize = new Analize();
        List<Analize.User> previous = List.of(
                new Analize.User(1, "Ivan"),
                new Analize.User(2, "Petr")
        );
        List<Analize.User> current = List.of(
                new Analize.User(1, "Vasiliy"),
                new Analize.User(2, "Vladimir")
        );
        Analize.Info info = analize.diff(previous, current);
        assertThat(new int[]{info.added, info.deleted, info.changed},
                is(new int[]{0, 0, 2}));
    }

    @Test
    public void whenAddedDeletedAndChanged() {
        Analize analize = new Analize();
        List<Analize.User> previous = List.of(
                new Analize.User(1, "Ivan"),
                new Analize.User(2, "Dmitry"),
                new Analize.User(3, "Petr")
        );
        List<Analize.User> current = List.of(
                new Analize.User(1, "Vasiliy"),
                new Analize.User(4, "Vladimir"),
                new Analize.User(5, "Konstantin")
        );
        Analize.Info info = analize.diff(previous, current);
        assertThat(new int[]{info.added, info.deleted, info.changed},
                is(new int[]{2, 2, 1}));
    }
}