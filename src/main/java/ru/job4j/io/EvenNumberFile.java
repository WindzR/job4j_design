package ru.job4j.io;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.stream.Collectors;

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

//    public static void main(String[] args) {
//        try (FileInputStream in = new FileInputStream("even.txt")) {
//            StringBuilder nums = new StringBuilder();
//            int read;
//            while ((read = in.read()) != -1) {
//                nums.append((char) read);
//            }
//            String[] numbers = nums.toString().split(System.lineSeparator());
//            Arrays.stream(numbers)
//                    .map(Integer::parseInt)
//                    .filter(element -> element % 2 != 0)
//                    .peek(element -> System.out.println("Число " + element + " является нечетным!"))
//                    .collect(Collectors.toList());
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
}
