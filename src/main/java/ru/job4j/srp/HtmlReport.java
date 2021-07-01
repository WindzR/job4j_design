package ru.job4j.srp;

import java.util.List;

public class HtmlReport implements ReportForm {

    private String header() {
        StringBuilder header = new StringBuilder();
        header.append("<!DOCTYPE html>").append(System.lineSeparator())
                .append("<html>").append(System.lineSeparator())
                .append("<head>").append(System.lineSeparator())
                .append("<title>!DOCTYPE</title>").append(System.lineSeparator())
                .append("<meta charset=\"utf-8\">").append(System.lineSeparator())
                .append("</head>").append(System.lineSeparator())
                .append("<body>").append(System.lineSeparator());
        return header.toString();
    }

    private String footer() {
        StringBuilder footer = new StringBuilder();
        footer.append("</body>").append(System.lineSeparator())
                .append("</html>").append(System.lineSeparator());
        return footer.toString();
    }

    @Override
    public String generate(List<Employee> employees) {
        StringBuilder result = new StringBuilder(header());
        result.append("<table>").append(System.lineSeparator())
                .append("<tr>").append("<th>").append("Name").append("</th>")
                .append("<th>").append("Hired").append("</th>")
                .append("<th>").append("Fired").append("</th>")
                .append("<th>").append("Salary").append("</th>").append("</tr>").append(System.lineSeparator());
        for (Employee employee : employees) {
            result.append("<tr>").append("<th>").append(employee.getName()).append("</th>")
                    .append("<th>").append(employee.getHired()).append("</th>")
                    .append("<th>").append(employee.getFired()).append("</th>")
                    .append("<th>").append(employee.getSalary()).append("</th>").append("</tr>").append(System.lineSeparator());
        }
        result.append("</table>").append(System.lineSeparator()).append(footer());
        return result.toString();
    }
}
