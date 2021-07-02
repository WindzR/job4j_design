package ru.job4j.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReportTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportForm report = new ReportOrigin();
        ReportEngine engine = new ReportEngine(store, report);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenHRGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Petr", now, now, 120);
        store.add(worker1);
        store.add(worker2);
        ReportForm report = new ReportHR();
        ReportEngine engine = new ReportEngine(store, report);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenBookKeepingGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100.55);
        store.add(worker);
        ReportForm report = new ReportBookKeep();
        ReportEngine engine = new ReportEngine(store, report);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(100).append(" руб. ").append(55).append(" коп.").append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

    @Test
    public void whenHtmlGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportForm report = new HtmlReport();
        ReportEngine engine = new ReportEngine(store, report);
        StringBuilder expectString = new StringBuilder()
                .append("<tr>").append("<th>").append(worker.getName()).append("</th>")
                .append("<th>").append(worker.getHired()).append("</th>")
                .append("<th>").append(worker.getFired()).append("</th>")
                .append("<th>").append(worker.getSalary()).append("</th>").append("</tr>");
        String expect = expectString.toString();
        assertTrue(engine.generate(em -> true).contains(expect));
    }

    @Test
    public void whenXMLGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportForm report = new XmlReport();
        ReportEngine engine = new ReportEngine(store, report);
        StringBuilder expectString = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>").append("\n")
                .append("<employee name=").append("\"").append(worker.getName()).append("\"")
                .append(" salary=").append("\"").append(worker.getSalary()).append("\"").append(">").append("\n")
                .append("    ")
                .append("<hired>").append(dateFormat.format(worker.getHired().getTime())).append("</hired>").append("\n")
                .append("    ")
                .append("<fired>").append(dateFormat.format(worker.getFired().getTime())).append("</fired>").append("\n")
                .append("</employee>").append("\n");
        String expect = expectString.toString();
        assertThat(engine.generate(em -> true), is(expect));
    }

    @Test
    public void whenJSONGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        now.set(2021, Calendar.JULY, 3, 1, 9, 55);
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportForm report = new JsonReport();
        ReportEngine engine = new ReportEngine(store, report);
        StringBuilder expectString = new StringBuilder()
                .append("{")
                .append("\"name\":\"").append(worker.getName()).append("\",")
                .append("\"hired\":{")
                .append("\"year\":2021,\"month\":6,\"dayOfMonth\":3,")
                .append("\"hourOfDay\":1,\"minute\":9,\"second\":55},")
                .append("\"fired\":{")
                .append("\"year\":2021,\"month\":6,\"dayOfMonth\":3,")
                .append("\"hourOfDay\":1,\"minute\":9,\"second\":55},")
                .append("\"salary\":").append(worker.getSalary()).append("}")
                .append(System.lineSeparator());
        String expect = expectString.toString();
        assertThat(engine.generate(em -> true), is(expect));
    }
}