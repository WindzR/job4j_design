package ru.job4j.dip;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class HiringOffer {
    private static String path;
    private static List<CitezenRF> citizens;
    private static String company = "Рога и копыта";
    private static String position = "Junior++++ Principal Solution Architect";

    public HiringOffer(String path, List<CitezenRF> citizens) {
        this.path = path;
        this.citizens = citizens;
    }

    public static List<CitezenRF> hiringCitizen() {
        List<CitezenRF> citezens = new ArrayList<>();
        CitezensFilter filter = new CitezensFilter(citizens);
        citezens = filter.checkCitezensForHiring();
        return citezens;
    }

    private static String textHiringOffer(CitezenRF citezen) {
        StringBuilder text = new StringBuilder();
        text.append("Уважаемый, ").append(citezen.getName()).append("!").append(System.lineSeparator())
                .append("Я представитель компании ").append(company).append(" предлагаю Вам пройти техническое собеседование на позицию ")
                .append(position).append(".").append(System.lineSeparator())
                .append("Мы предлагаем Вам работу по ТК Зимбабве и дружный коллектив единомышленников.")
                .append("Зарплатная вилка : 10000 - 100500 рублей.").append(System.lineSeparator());
        return text.toString();
    }

    public void prepareForMailing(CitezenRF citizen) {
        List<String> text = new ArrayList<>();
        String hiringOffer = textHiringOffer(citizen);
        try (BufferedWriter out = new BufferedWriter(
                new FileWriter(path, StandardCharsets.UTF_8, false))) {
            for (String phrase : text) {
                out.write(phrase);
                out.newLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
