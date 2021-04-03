package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalizyTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

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

    @Test
    public void whenCheckWithTemporaryFolder() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter writer = new PrintWriter(source)) {
            writer.println("200 10:00:00");
            writer.println("400 10:55:00");
            writer.println("400 10:58:48");
            writer.println("200 11:03:05");
            writer.println("300 11:08:02");
            writer.println("500 11:11:00");
            writer.println("400 11:12:45");
            writer.println("100 11:13:30");
        }
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            reader.lines().forEach(result :: append);
        }
        assertThat(result.toString(), is("10:55:00;11:03:05"
                + "11:11:00;11:13:30"));
    }
}