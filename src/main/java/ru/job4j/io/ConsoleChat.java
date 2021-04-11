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
        try (BufferedWriter out = new BufferedWriter(
                new FileWriter(path, StandardCharsets.UTF_8, true))) {
            boolean isDialog = true;
            boolean botIsWorking = true;
            while (isDialog) {
                Scanner sc = new Scanner(System.in);
                System.out.print("Создатель: ");
                String question = sc.nextLine();
                out.write("Создатель: " + question + System.lineSeparator());
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
                    String answer = botAnswer();
                    out.write("Бот : " + answer + System.lineSeparator());
                }
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

    private String botAnswer() {
        String answer = " ";
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            List<String> answerList = new ArrayList<>();
            answerList = in.lines().collect(Collectors.toList());
            Random random = new Random();
            int chooseAnswer = random.nextInt(answerList.size() - 1);
            answer = answerList.get(chooseAnswer);
            System.out.println("Бот : " + answer);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return answer;
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("data/dialog.txt", "data/botAnswers.txt");
        cc.run();
    }
}
