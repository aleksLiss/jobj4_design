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

    private void runStatement(String sqlQuery, String tableName, String oldColumn, String type, String newColumn) {
        if (sqlQuery.startsWith("CREATE") || sqlQuery.startsWith("DROP")) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                        sqlQuery,
                        tableName
                );
                statement.execute(sql);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        if (sqlQuery.startsWith("ALTER")) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                        sqlQuery,
                        tableName
                );
                statement.execute(sql);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void createTable(String tableName) throws Exception {
        String sql = "CREATE TABLE IF NOT EXISTS %s();";
        runStatement(sql, tableName, "", "", "");
    }

    public void dropTable(String tableName) {
        String sql = "DROP TABLE %s;";
        runStatement(sql, tableName, "", "", "");
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = "ALTER TABLE %s ADD COLUMN %s %s;";
        runStatement(sql, tableName, columnName, type, "");
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = "ALTER TABLE %s DROP COLUMN %s;";
        runStatement(sql, tableName, columnName, "", "");
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = "ALTER TABLE %s RENAME COLUMN %s TO %s;";
        runStatement(sql, tableName, columnName, "", newColumnName);
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