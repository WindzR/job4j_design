package ru.job4j.ocp;

/**
 * Хотя здесь и используется интерфейс, но гибкость расширения программы теряется, так как используется
 * конкретный дженерик <Integer>, а следовало либо <Е>, либо <? extends Number>.
 */
public interface ArrayOfNumbers<Integer> {
    Integer[] notEven(Integer[] numbers);
}
