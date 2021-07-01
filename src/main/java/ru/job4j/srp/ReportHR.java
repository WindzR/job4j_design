package ru.job4j.srp;

import java.util.Calendar;
import java.util.List;

public class ReportHR implements ReportForm {

    @Override
    public String generate(List<Employee> employees) {
        employees.sort((em1, em2) -> Double.compare(em2.getSalary(), em1.getSalary()));
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : employees) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

    public static void main(String[] args) {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petr", now, now, 120);
        store.add(worker1);
        store.add(worker2);
        ReportHR reportHR = new ReportHR();
        System.out.println(reportHR.generate(store.findBy(em -> true)));
    }
}
