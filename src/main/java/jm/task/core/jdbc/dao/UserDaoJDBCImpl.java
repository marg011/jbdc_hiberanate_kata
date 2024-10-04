package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl extends Util implements UserDao {
    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        String sqlQuery = "CREATE TABLE IF NOT EXISTS users(ID BIGINT NOT NULL AUTO_INCREMENT, NAME VARCHAR(100), LASTNAME VARCHAR(100), AGE INT, PRIMARY KEY (ID) )";

        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement()) {

            stmt.executeUpdate(sqlQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String sqlQuery = "DROP TABLE IF EXISTS users";

        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement()) {

            stmt.executeUpdate(sqlQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sqlQuery = "INSERT INTO users (NAME, LASTNAME, AGE) VALUES(?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sqlQuery)) {

            pstmt.setString(1, name);
            pstmt.setString(2, lastName);
            pstmt.setByte(3, age);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        String sqlQuery = "DELETE FROM users WHERE ID=?";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sqlQuery)) {

            pstmt.setLong(1, id);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        String sqlQuery = "SELECT ID, NAME, LASTNAME, AGE FROM users";

        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement()) {

            ResultSet resultSet = stmt.executeQuery(sqlQuery);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LASTNAME"));
                user.setAge(resultSet.getByte("AGE"));

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;

    }

    @Override
    public void cleanUsersTable() {
        String sqlQuery = "DELETE FROM users";
        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement()) {

            stmt.executeUpdate(sqlQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

