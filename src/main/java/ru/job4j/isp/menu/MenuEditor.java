package ru.job4j.isp.menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Класс для работы с меню через интерфейс Menu и его реализации
 */
public class MenuEditor {
    private CompoundMenu menu = new CompoundMenu("RoadMap JAVA");
//    private Map<Integer, String> menuItems = new HashMap<>();

    public MenuEditor() {
    }

    private CompoundMenu init() {
        CompoundMenu heading = new CompoundMenu("Изучение Java");
        CompoundMenu core = new CompoundMenu("Изучение Java core");
        CompoundMenu spring = new CompoundMenu("Изучение Spring");
        heading.add(core);
        heading.add(spring);
        Leaf circle = new Leaf("Циклы");
        Leaf arrays = new Leaf("Массивы");
        Leaf streamApi = new Leaf("Stream API");
        core.add(circle);
        core.add(arrays);
        core.add(streamApi);
        CompoundMenu collections = new CompoundMenu("Коллекции");
        Leaf arList = new Leaf("ArrayList");
        Leaf linkList = new Leaf("LinkedList");
        Leaf hashMap = new Leaf("HashMap");
        collections.add(arList);
        collections.add(linkList);
        collections.add(hashMap);
        core.add(collections);
        Leaf boot = new Leaf("Spring Boot");
        Leaf mvc = new Leaf("Spring MVC");
        Leaf security = new Leaf("Spring Security");
        spring.add(boot);
        spring.add(mvc);
        spring.add(security);
        return heading;
    }

    public void loadMenu(MenuItem items) {
        menu.add(items);
    }

//    public void hashMenu(CompoundMenu menu) {
//        menuItems = menu.getItems()
//                    .stream()
//                    .collect(Collectors.toMap(MenuItem::getId, MenuItem::getName));
//    }

    public void displayMenu() {
        menu.display();
    }

    public int selectNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите порядковый номер меню (указывать разделители НЕ надо!): ");
        return scanner.nextInt();
    }

    public void selectMenu(int menuNumber) {
        menu.select(menuNumber);
//        System.out.println("Выполняем пункт <" + menuItems.get(menuNumber) + ">!");
    }

    public static void main(String[] args) {
        MenuEditor editor = new MenuEditor();
        CompoundMenu root = editor.init();
        editor.loadMenu(root);
        editor.displayMenu();
        int select = editor.selectNumber();
        editor.selectMenu(select);
    }
}
