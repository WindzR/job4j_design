package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

public class GermanArmedForces {
    private List<Candidate> personnel = new ArrayList<>();

    public void recruitment(Candidate candidate) {
        boolean isGood = candidate.getAge() >= 18 && candidate.getAge() < 30
                && candidate.getHeight() >= 170 && candidate.getHeight() < 200
                && candidate.getWeight() >= 65 && candidate.getWeight() < 110;
        if (isGood) {
            personnel.add(candidate);
            System.out.println("Кандидат зачислен в войска!");
        }
    }
}
