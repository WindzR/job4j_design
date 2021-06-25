package ru.job4j.cash;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Emulator {
    private String directory;
    private List<File> directories = new ArrayList<>();
    private List<String> cashingFiles = new ArrayList<>();

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public List<String> getCashingFiles() {
        return cashingFiles;
    }

    /**
     * запуск программы по кешированию текстовых файлов и вывод содержимого на экран
     */
    private static void run() {
        Emulator emulator = new Emulator();
        String dir = emulator.chooseDirectory("src/main/resources") + "/";
        emulator.setDirectory(dir);
        DirFileCache cache = new DirFileCache(emulator.getDirectory());
        emulator.contentDirectory(emulator.getDirectory());
        String fileName = emulator.displayFiles(emulator.getCashingFiles());
        String path = emulator.getDirectory() + "/" + fileName;
        String fileContent = cache.load(path);
        System.out.println(fileContent);
    }

    /**
     * выводит список каталогов, который нужно закешировать
     * @return путь до нужного каталога
     */
    public String chooseDirectory(String path) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("<-------Какую директорию будем кешировать?------->");
        File file = new File(path);
        File[] list = file.listFiles();
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        AtomicInteger i = new AtomicInteger(1);
        assert list != null;
        for (File subfile : list) {
            if (subfile.isDirectory()) {
                System.out.println(i.getAndIncrement() + ". <" + subfile.getAbsoluteFile() + ">");
                directories.add(subfile);
            }
        }
        int select = Integer.parseInt(scanner.nextLine());
        if (select > list.length || select < 1) {
            System.out.println("Нет такого каталога! Попробуйте еще раз!");
            chooseDirectory(path);
        }
        return directories.get(select - 1).getPath();
    }

    /**
     * метод для отображения списка файлов каталога и создания списка файлов, подлежащих кешированию
     * @param directory требуемый каталог
     */
    public void contentDirectory(String directory) {
        File dir = new File(directory);
        if (dir.isDirectory()) {
            System.out.println("Список файлов в каталоге <" + dir + ">");
            String[] files = dir.list();
            assert files != null;
            for (String file : files) {
                File subFile = new File(dir + "/" + file);
                if (!subFile.isDirectory()) {
                    if (file.contains(".txt")) {
                        System.out.println("файл <" + file + "> может быть кеширован");
                        cashingFiles.add(file);
                    }
                    if (!file.contains(".txt")) {
                        System.out.println("файл <" + file + "> не подлежит кешированию");
                    }
                }
            }
        }
    }

    /**
     * метод, который позволяет выбрать файл для кеширования и вывода его содержимого на экран
     * @param cashingFiles список файлов, которые могут быть кешированы
     */
    public String displayFiles(List<String> cashingFiles) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("<-----Содержимое какого файла должно быть кешировано?----->");
        AtomicInteger i = new AtomicInteger(1);
        cashingFiles.forEach(file -> {
            System.out.println(i.getAndIncrement() + ". <" + file + ">");
        });
        int select = Integer.parseInt(scanner.nextLine());
        if (select > cashingFiles.size() || select < 1) {
            System.out.println("Нет такого файла! Попробуйте еще раз!");
            displayFiles(cashingFiles);
        }
        return cashingFiles.get(select - 1);
    }

    public static void main(String[] args) {
        run();
    }
}
