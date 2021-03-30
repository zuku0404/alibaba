package data_base.user;

import data_base.ConnectorDB;
import model.dao.User;

import java.sql.*;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public User saveUser(User user) {
        String query = "Insert into user (LOGIN, PASSWORD, EMAIL, CREATED) values (?,?,?,?)";
        PreparedStatement ps;
        try (Connection connection = ConnectorDB.createConnection()) {
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setTimestamp(4, Timestamp.valueOf(user.getCreated()));
            ps.addBatch();
            ps.executeBatch();
            ps.getGeneratedKeys();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public void removeUser(User user) {
        String query = "DELETE FROM User WHERE id = ?";
        PreparedStatement preparedStatement;
        try (Connection connection = ConnectorDB.createConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Optional<User> findUserByLogin(String login) {
        String query = "Select * from user where login = ? ";
        PreparedStatement preparedStatement;
        try (Connection connect = ConnectorDB.createConnection()) {
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(User.builder()
                            .id(resultSet.getLong("ID"))
                            .login(resultSet.getString("LOGIN"))
                            .password(resultSet.getString("PASSWORD"))
                            .email(resultSet.getString("EMAIL"))
                            .firstName(resultSet.getString("FIRSTNAME"))
                            .secondName(resultSet.getString("SECONDNAME"))
                            .age(resultSet.getInt("AGE"))
                            .created(resultSet.getTimestamp("CREATED").toLocalDateTime())
                            .build());
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public User updateUser(User user, String firstName, String secondName, int age) {
        String query = "UPDATE user SET firstName = ?, secondName = ?, age = ? WHERE ID = ?";
        PreparedStatement preparedStatement;
        try (Connection connection = ConnectorDB.createConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, secondName);
            preparedStatement.setInt(3, age);
            preparedStatement.setLong(4, user.getId());
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
            preparedStatement.getGeneratedKeys();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public User updatePassword(User user, String password) {
        String query = "UPDATE user SET password = ? WHERE ID = ?";
        PreparedStatement preparedStatement;
        try (Connection connection = ConnectorDB.createConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, password);
            preparedStatement.setLong(2, user.getId());
            preparedStatement.addBatch();
            preparedStatement.executeBatch();
            preparedStatement.getGeneratedKeys();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }
}



