package ru.job4j.ocp.first;

import ru.job4j.jdbc.TableEditor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Saver {

    private Connection connection;
    private Properties properties;


    /**
     * 1. Появились задача: необходимо сохранять данные.
     * Изначально было предложено сохранять локально на файл.
     * Метод для сохранения строки в файл.
     * @param path
     * @param content
     */
    public void saveToFile(String path, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(content);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 1. Затем появилось новое требование:
     * пусть мы будет сохранять данные еще и в базу данных.
     * Здесь происходит нарушение принципа открытости-закрытости,
     * т.к. требования могут появлятся и появляться и нам придется
     * каждый раз изменять существующий код.
     * @param content
     */
    public void saveToDB(String content) {
        initConnection();
        try (var statement = connection.prepareStatement("INSERT INTO(content) VALUES(?)")) {
            statement.setString(1, content);
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void initConnection() {
        try {
            loadProperties();
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

    private void loadProperties() throws Exception {
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(in);
        }
    }
}
