package ru.job4j.isp.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, для предоставления пункта меню, имеющего вложенные подменю
 */
public class CompoundMenu extends MenuItem {
    /**
     * String indent - параметр, отвечающий за отступ при выводе на экран,
     * List<MenuItem> items - список вложенных пунктов подменю
     */
    private String indent = "";
    private int id = 0;
    private String name;
    private List<MenuItem> items = new ArrayList<>();

    public CompoundMenu() {
    }

    public CompoundMenu(String name) {
        this.name = name;
    }

    public void setIndent(String space) {
        this.indent = indent + space;
    }

    public String getIndent() {
        return indent;
    }

    public List<MenuItem> getItems() {
        return items;
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
     * @param id форматируемый номер вида [123]
     * @return порядковый номер меню вида [1.2.3.]
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

    /**
     * метод для добавления подпункта меню
     * @param item вложенный пункт меню (может быть как Leaf/лист/, так и CompoundMenu/содержащий другие подпункты/)
     */
    public void add(MenuItem item) {
        items.add(item);
    }

    /**
     * метод для удаления подпункта меню
     * @param item вложенный пункт меню (может быть как Leaf/лист/, так и CompoundMenu/содержащий другие подпункты/)
     */
    public void remove(MenuItem item) {
        items.remove(item);
    }

    @Override
    public boolean acceptMenu(int menuNumber) {
        return menuNumber == id;
    }

    @Override
    public void display() {
        System.out.println(indent + formatId(id) + "<" + name + ">");
        int counter = 1;
        for (MenuItem item : items) {
            item.setIndent(getIndent() + "  ");
            item.setId(id * 10 + counter);
            counter++;
            item.display();
        }
    }

    @Override
    public void select(int menuNumber) {
        System.out.println("Ищем в меню <" + name + ">!");
        for (MenuItem item : items) {
            item.select(menuNumber);
        }
    }
}
