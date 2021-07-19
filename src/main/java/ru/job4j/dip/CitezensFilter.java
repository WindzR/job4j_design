package ru.job4j.dip;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс зависит от конкретной реализации класса CitezenRF,
 * поэтому лучше создать абстрактный класс Human и от него наследовать CitezenRF
 * и класс CitezensFilter лучше переделать для работы с наследниками Human
 */
public class CitezensFilter {
    private List<CitezenRF> citizen = new ArrayList<>();

    public CitezensFilter(List<CitezenRF> citizen) {
        this.citizen = citizen;
    }

    public List<CitezenRF> checkCitezens(List<CitezenRF> citizens) {
        return citizens.stream()
                .filter(c -> c.getAge() >= 18 && c.getAge() <= 55 && c.getIncome() >= 50000 && c.getIncome() <= 500000)
                .collect(Collectors.toList());
    }
}
