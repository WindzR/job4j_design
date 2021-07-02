package ru.job4j.ocp;

public class Rectangle {
    /**
     * в классе происходит нарушение принципа OCP, так как используется конкретная реализация, а не абстрактный метод
     * с применением функционального интерфейса и ее придется изменять в случае, если нам понадобилось расширение
     * ее возможностей
     */
    public int areaRectangle(int a, int b) {
        return a * b;
    }
}
