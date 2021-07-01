package ru.job4j.srp;

import java.util.List;

public class ReportBookKeep implements ReportForm {

    @Override
    public String generate(List<Employee> employees) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : employees) {
            String[] salary = String.format("%.2f", employee.getSalary()).split("\\.");
            String empSalary = String.format("%s руб. %s коп.", salary[0], salary[1]);
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append(empSalary).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
