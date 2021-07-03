package ru.job4j.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Calendar;
import java.util.List;

public class JsonReport implements ReportForm {

    @Override
    public String generate(List<Employee> employees) {
        StringBuilder result = new StringBuilder();
        Employees generalObject = new Employees();
        generalObject.setEmployeeList(employees);
        Gson gson = new GsonBuilder().create();
        result.append(gson.toJson(generalObject)).append(System.lineSeparator());
        return result.toString();
    }
}
