package ru.job4j.srp;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * класс нарушает принцип SRP, так как задает путь искомой директории непосредственно в классе, и не использует
 * при поиске файлов универсальный предикат.
 */
public class Search {
    public static void main(String[] args) throws IOException {
        Path start = Paths.get("C:\\projects\\job4j_design");
        search(start).forEach(System.out::println);
    }

    public static List<Path> search(Path root) throws IOException {
        SearchFiles searcher = new SearchFiles();
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
