package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File file : sources) {
                System.out.println("Adding file <" + file.getName() + "> to archive");
                zip.putNextEntry(new ZipEntry(file.getPath()));
                try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))) {
                    zip.write(in.readAllBytes());
                }
            }
            System.out.println("Files has been archived to <" + target.getName() + ">");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(in.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Zip().packFiles(List.of(
                new File("checkstyle.xml"),
                new File("pom.xml"),
                new File("even.txt")
        ), new File("./archive.zip")
        );
    }
}
