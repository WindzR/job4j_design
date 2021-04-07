package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.nio.file.Files.size;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Set<FileProperty> files = new HashSet<>();
    Map<String, FileProperty> duplicates = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        String fileName = file.toFile().getName();
        FileProperty fileProperty = new FileProperty(size(file), fileName);
        if (!files.add(fileProperty)) {
            duplicates.put(fileName, fileProperty);
            System.out.println(file.toAbsolutePath());
        }
        return super.visitFile(file, attrs);
    }
}
