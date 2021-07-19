package ru.job4j.dip;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TimePass {
    private static LocalDateTime checkDate = LocalDateTime.of(2021, 6, 1, 0, 0);
    private static ZoneOffset zone = ZoneOffset.UTC;

    /**
     * метод нарушает принцип DIP, так как зависит от определенного поля checkDate,
     * следовало сделать реализацию hoursPass(LocalDateTime date);
     * @return сколько часов прошло с определенной даты
     */
    public static double hoursPass() {
        LocalDateTime now = LocalDateTime.now();
        long secondsPass = now.toEpochSecond(zone) - checkDate.toEpochSecond(zone);
        return (double) secondsPass / 60;
    }

    public static void main(String[] args) {
        System.out.printf("Прошло часов с 1 июня 2021 --> %.2f", hoursPass());
    }
}
