package ru.job4j.jdbc;

import java.sql.*;
import java.util.Properties;


public class TableEditor implements AutoCloseable {
    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    properties.getProperty("connection.url"),
                    properties.getProperty("connection.username"),
                    properties.getProperty("connection.password")
            );
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        String sql = "create table if not exists " + tableName + "()";
        execute(sql);
    }

    public void dropTable(String tableName) {
        String sql = "drop table if exists " + tableName;
        execute(sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = "alter table " + tableName + " add " + columnName + " " + type;
        execute(sql);
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = "alter table " + tableName + " drop " + columnName;
        execute(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = "alter table " + tableName + " rename column " + columnName + " to " + newColumnName;
        execute(sql);
    }

    public String getScheme(String tableName) {
        StringBuilder scheme = new StringBuilder();
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            try (ResultSet columns = metaData.getColumns(null, null, tableName, null)) {
                scheme.append(String.format("%-15s %-15s%n", "column", "type"));
                while (columns.next()) {
                    scheme.append(String.format("%-15s %-15s%n",
                            columns.getString("COLUMN_NAME"),
                            columns.getString("TYPE_NAME")));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return scheme.toString();
    }

    private void execute(String sql) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }
}
