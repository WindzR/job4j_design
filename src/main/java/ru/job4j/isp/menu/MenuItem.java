package ru.job4j.isp.menu;

public abstract class MenuItem implements Menu {

    @Override
    public abstract void display();

    @Override
    public abstract void select(int menuNumber);

    public abstract boolean acceptMenu(int menuNumber);

    public abstract void setIndent(String indent);

    public abstract void setId(int id);

    public abstract int getId();

    public abstract String getName();
}
