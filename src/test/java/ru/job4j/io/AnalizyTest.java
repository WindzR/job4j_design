package ru.job4j.io;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalizyTest {

    @Test
    public void whenCheckServerStatus() {
        Analizy analizy = new Analizy();
        analizy.unavailable("./data/testForAnalize.txt", "./data/unavailable.txt");
        assertThat(analizy.stringFromFile("./data/unavailable.txt", 1), is("10:55:00;11:03:05"));
    }

    @Test
    public void whenCheckServerStatus2() {
        Analizy analizy = new Analizy();
        analizy.unavailable("./data/testForAnalize.txt", "./data/unavailable.txt");
        assertThat(analizy.stringFromFile("./data/unavailable.txt", 2), is("11:11:00;11:13:30"));
    }

    @Test
    public void whenCheckServerStatus3() {
        Analizy analizy = new Analizy();
        analizy.unavailable("./data/serverstatus.txt", "./data/unavailable.txt");
        assertThat(analizy.stringFromFile("./data/unavailable.txt", 2), is("11:01:02;11:02:02"));
    }
}