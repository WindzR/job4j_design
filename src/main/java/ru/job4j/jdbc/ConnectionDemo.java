package ru.job4j.jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class ConnectionDemo {
    private final String path = "data/app.properties";
    private Map<String, String> config = new HashMap<>();
    private List<String> keys = new ArrayList<>();

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ConnectionDemo connect = new ConnectionDemo();
        connect.readConfig();
        String driver = connect.values("driver_class");
        Class.forName(driver);
        String url = connect.values("url");
        String login = connect.values("username");
        String password = connect.values("password");
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }

    private void readConfig() {
        try (BufferedReader read = new BufferedReader(new FileReader(path))) {
            for (String line = read.readLine(); line != null; line = read.readLine()) {
                if (isPermissible(line)) {
                    String[] temp = line.split("=");
                    if (temp.length != 2 || temp[0].isEmpty() || temp[1].isEmpty()) {
                        throw new IllegalArgumentException();
                    }
                    config.put(temp[0], temp[1]);
                    keys.add(temp[0]);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private boolean isPermissible(String line) {
        return !line.isEmpty() && !line.startsWith("#");
    }

    private String values(String value) {
        Optional<String> temp = keys.stream().filter(k -> k.contains(value)).findFirst();
        if (temp.isPresent()) {
            return config.get(temp.get());
        }
        return " ";
    }
}
