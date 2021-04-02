package ru.job4j.io;

import java.io.*;

public class Analizy {
    private boolean isAvailable = true;

    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
        BufferedWriter out = new BufferedWriter(new FileWriter(target))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] current = line.split(" ");
                if (isAvailable && (current[0].equals("400") || current[0].equals("500"))) {
                    isAvailable = false;
                    out.write(current[1] + ";");
                }
                if (!isAvailable && !current[0].equals("400") && !current[0].equals("500")) {
                    isAvailable = true;
                    out.write(current[1] + System.lineSeparator());
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * метод для проверки тестами
     * @param file
     * @return
     */
    public String stringFromFile(String file, int numOfString) {
        String result = null;
        int number = 0;
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                result = line;
                number++;
                if (number == numOfString) {
                    break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analizy analizy = new Analizy();
        analizy.unavailable("./data/serverstatus.txt", "./data/unavailable.txt");
    }
}
