package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;


public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
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

    public void createTable(String tableName) throws Exception {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "CREATE TABLE IF NOT EXISTS %s();",
                    tableName
            );
            statement.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void dropTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("DROP TABLE %s;", tableName);
            statement.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addColumn(String tableName, String columnName, String type) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("ALTER TABLE %s ADD COLUMN %s %s;",
                    tableName, columnName, type);
            statement.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void dropColumn(String tableName, String columnName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("ALTER TABLE %s DROP COLUMN %s;",
                    tableName, columnName);
            statement.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format("ALTER TABLE %s RENAME COLUMN %s TO %s;",
                    tableName, columnName, newColumnName);
            statement.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void runEditor(String tableName, String oldCol, String newCol, String type) {
        try (TableEditor editor = new TableEditor(new Properties())) {
            editor.createTable(tableName);
            System.out.println(editor.getTableScheme(tableName));
            editor.addColumn(tableName, oldCol, type);
            System.out.println(editor.getTableScheme(tableName));
            editor.renameColumn(tableName, oldCol, newCol);
            System.out.println(editor.getTableScheme(tableName));
            editor.dropColumn(tableName, newCol);
            System.out.println(editor.getTableScheme(tableName));
            editor.dropTable(tableName);
            System.out.println(editor.getTableScheme(tableName));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        TableEditor editor = new TableEditor(new Properties());
        editor.runEditor("employees", "name", "emp_name", "text");
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}