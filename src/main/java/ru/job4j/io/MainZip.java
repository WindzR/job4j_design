package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MainZip {
    private final String[] args;
    private static String[] values = new String[3];

    public MainZip(String[] args) {
        this.args = args;
    }

    /**
     * метод для обработки входящих аргументов и получения массива String со значениями
     * - директории, которую надо заархивировать
     * - расширения файлов, которые надо исключить
     * - имя архива
     * Здесь используются методы класса ArgsName
     * @return
     */
    public String[] argsHandling() {
        if (!isValid()) {
            throw new IllegalArgumentException("Incorrect arguments!");
        }
        ArgsName argsName;
        argsName = ArgsName.of(args);
        if (argsName.get("d") == null
                || argsName.get("e") == null
                || argsName.get("o") == null) {
            throw new IllegalArgumentException("No such arguments!");
        }
        values[0] = argsName.get("d");
        values[1] = argsName.get("e");
        values[2] = argsName.get("o");
        return values;
    }

    /**
     * Метод для получения списка файлов для архивации
     * использует для этого метод search класса Search
     * @return
     * @throws IOException
     */
    public List<File> filesToArchive() throws IOException {
        Path dir = Paths.get(values[0]);
        System.out.println("Имя архивируемой папки : " + dir.toFile().getName());
        Predicate<Path> condition = path -> !path.toFile().getName().endsWith(values[1]);
        List<Path> filePath = Search.search(dir, condition);
//        System.out.println("Список файлов Path : ");
//        filePath.forEach(System.out :: println);
        return filePath.stream().map(Path::toFile).collect(Collectors.toList());
    }

    private boolean isValid() {
        return args.length == 3;
    }

    public static void main(String[] args) throws IOException {
//       MainZip mainZip = new MainZip(new String[] {"-d=C:\\projects\\job4j_design", "-e=xml", "-o=project.zip"});
        MainZip mainZip = new MainZip(args);
        mainZip.argsHandling();
        System.out.println(Arrays.toString(values));
        Zip zip = new Zip();
        List<File> fileList = mainZip.filesToArchive();
//        System.out.println("Список файлов File : ");
//        fileList.forEach(System.out :: println);
        zip.packFiles(fileList, new File(values[2]));
    }
}
