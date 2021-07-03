package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

/**
 * так как в подклассе отсутствуют условия, которые есть в базовом классе, то нарушается принцип подстановки Лисков
 */
public class RussianArmedForces extends GermanArmedForces {
    private List<Candidate> personnel = new ArrayList<>();

    public void recruitment(Candidate candidate) {
        boolean isGood = true;
        if (isGood) {
            personnel.add(candidate);
            System.out.println("Кандидат зачислен в доблестную армию России! Берем кривых-косых и учим круглое тащить, а квадратное катить!");
        }
    }
}
