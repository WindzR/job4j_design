package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder nums = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                nums.append((char) read);
            }
            String[] numbers = nums.toString().split(System.lineSeparator());
            for (String number : numbers) {
                int element = Integer.parseInt(number);
                if (element % 2 == 0) {
                    System.out.println("Число " + element + " является четным!");
                } else {
                    System.out.println("Число " + element + " является нечетным!");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
