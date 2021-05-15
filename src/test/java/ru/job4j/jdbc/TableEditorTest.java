package ru.job4j.jdbc;

import org.junit.Test;

import java.io.FileReader;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TableEditorTest {

    @Test
    public void whenCreateTable() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileReader("data/app.properties"));
        try (TableEditor tableEditor = new TableEditor(properties)) {
            tableEditor.createTable("main");
            tableEditor.addColumn("main", "column1", "text");
            tableEditor.addColumn("main", "column2", "int");
            tableEditor.addColumn("main", "column3", "varchar(255)");
            tableEditor.renameColumn("main", "column3", "column_change");
            tableEditor.dropColumn("main", "column2");
            StringBuilder expected = new StringBuilder();
            expected.append(String.format("%-15s %-15s%n", "column", "type"));
            expected.append(String.format("%-15s %-15s%n", "column1", "text"));
            expected.append(String.format("%-15s %-15s%n", "column_change", "varchar"));
            assertThat(tableEditor.getScheme("main"), is(expected.toString()));
            tableEditor.dropTable("main");
            assertThat(tableEditor.getScheme("main"), is(String.format("%-15s %-15s%n", "column", "type")));
        }
    }
}