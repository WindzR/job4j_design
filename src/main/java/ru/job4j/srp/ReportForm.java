package ru.job4j.srp;

import java.util.List;

public interface ReportForm {
    String generate(List<Employee> employees);
}
