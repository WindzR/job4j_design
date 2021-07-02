package ru.job4j.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class JsonReport implements ReportForm {

    @Override
    public String generate(List<Employee> employees) {
        StringBuilder result = new StringBuilder();
        Gson gson = new GsonBuilder().create();
        for (Employee employee : employees) {
            result.append(gson.toJson(employee)).append(System.lineSeparator());
        }
        return result.toString();
    }
}
