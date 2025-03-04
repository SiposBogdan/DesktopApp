package repository.admin;


import model.Role;
import model.User;

import model.builder.UserBuilder;
import model.validation.Notification;
import service.user.AuthenticationService;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static database.Constants.Tables.USER;

public class AdminRepositoryMySQL implements AdminRepository {
    private Connection connection;
    private AuthenticationService authenticationService;

    public AdminRepositoryMySQL(Connection connection,AuthenticationService authenticationService) {
        this.connection = connection;
        this.authenticationService= authenticationService;

    }

    public Notification<Role> findRoleId(String role)
    {
        String newSql = "select * from role where role = ? ";
        Notification<Role> findRoleId = new Notification<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(newSql)) {
            preparedStatement.setString(1, role);

            try (ResultSet userResultSet = preparedStatement.executeQuery()) {
                if (userResultSet.next()) {
                    findRoleId.setResult(getRoleFromResultSet(userResultSet));
                }
                else
                {
                    findRoleId.addError("Invalid username or password!");
                    return findRoleId;
                }

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
            findRoleId.addError("Something is wrong with the Database!");
        }

        return findRoleId;
    }
    @Override
    public boolean save(User user) {

        String newSql = "INSERT INTO user (username, password, role) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(newSql)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getStringRoles());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    public boolean saveRole(Long userId,Long roleId) {

        String newSql = "INSERT INTO user_role  (user_id, role_id) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(newSql)) {
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, roleId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }


    @Override
    public boolean delete(User user) {
        String newSql = "DELETE FROM user WHERE username = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(newSql)) {
            preparedStatement.setString(1, user.getUsername());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean generateReport() {
        return false;
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM user";
        List<User> users = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                users.add(getUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User findByUsername(String username) {
        String fetchUserSql = "SELECT * FROM `" + USER + "` WHERE `username` = ?";

        Notification<User> findByUsername = new Notification<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(fetchUserSql)) {
            preparedStatement.setString(1, username);

            try (ResultSet userResultSet = preparedStatement.executeQuery()) {
                if (userResultSet.next()) {
                    User user = new UserBuilder()
                            .setId(userResultSet.getLong("id"))
                            .setPassword(userResultSet.getString("password"))
                            .setUsername(userResultSet.getString("username"))
                            .build();
                    findByUsername.setResult(user);
                }
                else
                {
                    findByUsername.addError("Invalid username or password!");
                    return findByUsername.getResult();
                }

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
            findByUsername.addError("Something is wrong with the Database!");
        }

        return findByUsername.getResult();
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException
    {
        return new UserBuilder()
                .setId(resultSet.getLong("id"))
                .setUsername(resultSet.getString("username"))
                .setPassword(resultSet.getString("password"))
                .setStringRoles(resultSet.getString("role"))
                .build();
    }
    private Role getRoleFromResultSet(ResultSet resultSet) throws SQLException
    {
        return new Role(resultSet.getLong("id"),resultSet.getString("role"),null);
    }
}
