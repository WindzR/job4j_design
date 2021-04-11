package ru.job4j.io;

import java.io.*;
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

    public void run() {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(path)))) {
            boolean isDialog = true;
            while (isDialog) {
                Scanner sc = new Scanner(System.in);
                System.out.print("Создатель: ");
                String question = sc.nextLine();
                out.println("Создатель: " + question);
                String answer = botAnswer();
                out.println("Бот : " + answer);
                if (question.equals(OUT)) {
                    isDialog = false;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String botAnswer() {
        String answer = " ";
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers))) {
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
