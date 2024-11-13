package ru.job4j.srp.third;

import ru.job4j.jdbc.TableEditor;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class OpenConnectionWithDB {

    private Connection connection;
    private Properties properties;

    public OpenConnectionWithDB(Properties properties) {
        this.properties = properties;
    }

    /**
     * Метод для инициализации соединения с базой данных.
     * Так же содержит подгрузку зависимостей из файла app.properties.
     * Действие с подгрузкой зависимостей можно вынести в отдельный метод.
     */
    private void initConnection() {
        try {
            try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
                properties.load(in);
            }
            String url = properties.get("url").toString();
            String login = properties.get("login").toString();
            String password = properties.get("password").toString();
            connection = DriverManager.getConnection(url, login, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
