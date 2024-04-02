package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {
    Connection connection = getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "create table IF NOT EXISTS users ( id int auto_increment comment 'ID' primary key," +
                "name varchar(60) not null comment 'Imya'," +
                "lastname varchar(60) not null comment 'Familiya'," +
                "age tinyint null comment 'Vozrast')";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Таблица успешно создана");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка! Таблица не была создана");
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Таблица успешно удалена");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка! Таблица не удалена!");
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (NAME, LASTNAME, AGE) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("Данные успешно внесены в таблицу");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка! Данные в таблицу не записаны");
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Пользователь успешно удален по id");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка! Пользователь по id удален не был");
        }

    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT ID, NAME, LASTNAME, AGE FROM users";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setAge(resultSet.getByte("AGE"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка! Данные из таблицы не получены");
        }

        return userList;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM users";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Таблица успешно очищена");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка! Таблица не очищена");
        }

    }
}
