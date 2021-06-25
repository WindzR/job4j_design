package ru.job4j.cash;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DirFileCache extends AbstractCache<String, String> {
    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        DirFileCache fileCache = new DirFileCache(cachingDir);
        String result = null;
        if (cache.containsKey(key)) {
            return fileCache.get(key);
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(key))) {
            StringBuilder value = new StringBuilder();
            reader.lines().forEach(line -> {
                value.append(line).append("\n");
            });
            result = value.toString();
            fileCache.put(key, result);
        } catch (IOException ex) {
            System.out.println("Not correct file or file not found!");
            ex.printStackTrace();
        }
        return result;
    }
}
