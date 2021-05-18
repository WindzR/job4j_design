package ru.job4j.spammer;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {
    private final Properties cfg;
    private final String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    /**
     * Метод читает файл dump.txt и возвращает список Users(спаммеров)
     * @return arrayList<User>
     */
    public List<User> load() {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines().forEach(s -> {
                String[] line = s.split(";");
                users.add(new User(line[0], line[1]));
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return users;
    }

    /**
     * Метод подключается к БД PostgreSQL и добавляет пользователей из списка в БД
     * @param users список спамеров(List<User>)
     * @throws ClassNotFoundException пробрасывает Class.forName().
     */
    public void save(List<User> users) throws ClassNotFoundException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement(
                        "INSERT INTO users (name, email) VALUES (?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Модель спаммера
     */
    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }


    public static void main(String[] args) {
        Properties cfg = new Properties();
        ClassLoader loader = ImportDB.class.getClassLoader();
        try (InputStream in = loader.getResourceAsStream("app.properties")) {
            cfg.load(in);
            ImportDB db = new ImportDB(cfg, "./src/main/resources/dump.txt");
            db.save(db.load());
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
