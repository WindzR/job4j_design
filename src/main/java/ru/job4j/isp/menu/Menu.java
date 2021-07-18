package ru.job4j.isp.menu;

public interface Menu {

    void display();

    void select(int menuNumber);

    boolean acceptMenu(int menuNumber);
}
