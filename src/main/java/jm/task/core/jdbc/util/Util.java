package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final static String DB_URL = "jdbc:mysql://localhost:3306/user";

    private final static String DB_USERNAME = "root";

    private final static String DB_PASSWORD = "root";


    public Connection getConnection() {
        Connection connection = null;

        try {

            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            if (!connection.isClosed()) {

                System.out.println("Соединение с БД произошло успешно");

            }

        } catch (SQLException e) {

            System.out.println("Ошибка! Подключение к БД отсутствует");

        }
        return connection;
    }
}
