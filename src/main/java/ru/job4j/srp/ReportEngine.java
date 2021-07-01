package ru.job4j.srp;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class ReportEngine implements Report {
    private Store store;
    private ReportForm reportForm;

    public ReportEngine(Store store, ReportForm reportForm) {
        this.store = store;
        this.reportForm = reportForm;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> employees = store.findBy(filter);
        return reportForm.generate(employees);
    }
}
