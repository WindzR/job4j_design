package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    /**
     * Основной метод для общения с ботом через консоль.
     * Записывает весь диалог пользователя и бота в файл из path.
     */

    public void run() {
        boolean isDialog = true;
        boolean botIsWorking = true;
        List<String> logFile = new ArrayList<>();
        List<String> answerList = botAnswer();
        while (isDialog) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Создатель: ");
            String question = sc.nextLine();
            logFile.add("Создатель: " + question);
            if (question.equals(OUT)) {
                isDialog = false;
                botIsWorking = false;
            }
            if (question.equals(STOP)) {
                botIsWorking = false;
            }
            if (question.equals(CONTINUE)) {
                botIsWorking = true;
            }
            if (botIsWorking) {
                Random random = new Random();
                int choiceAnswer = random.nextInt(answerList.size() - 1);
                String answer = answerList.get(choiceAnswer);
                System.out.println("Бот : " + answer);
                logFile.add("Бот : " + answer);
            }
        }
        try (BufferedWriter out = new BufferedWriter(
                new FileWriter(path, StandardCharsets.UTF_8, false))) {
            for (String phrase : logFile) {
                out.write(phrase);
                out.newLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Метод для выбора ответа бота.
     * Читает файл с ответами и случайно выбирает строку для ответа.
     * @return ответ вида String
     */

    private List<String> botAnswer() {
        List<String> answerList = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            answerList = in.lines().collect(Collectors.toList());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return answerList;
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("data/dialog.txt", "data/botAnswers.txt");
        cc.run();
    }
}
