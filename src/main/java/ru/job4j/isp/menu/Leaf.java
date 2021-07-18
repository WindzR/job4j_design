package ru.job4j.isp.menu;

/**
 * Класс, для предоставления пункта меню, который не может иметь в себе другие подменю
 */
public class Leaf extends MenuItem {
    private String indent = "";
    private int id;
    private String name;

    public Leaf(String name) {
        this.name = name;
    }

    public void setIndent(String space) {
        this.indent = indent + space;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Форматируем порядковый номер меню
     * @param id форматируемый номер
     * @return порядковый номер меню
     */
    private String formatId(int id) {
        String strId = String.valueOf(id);
        char[] arrId = strId.toCharArray();
        StringBuilder result = new StringBuilder();
        for (char num : arrId) {
            result.append(num).append(".");
        }
        return result.toString();
    }

    @Override
    public boolean acceptMenu(int menuNumber) {
        return menuNumber == id;
    }

    @Override
    public void display() {
        System.out.println(indent + formatId(id) + "<" + name + ">");
    }

    @Override
    public void select(int menuNumber) {
        if (acceptMenu(menuNumber)) {
            System.out.println("Выполняем пункт <" + name + ">!");
        }
    }
}
