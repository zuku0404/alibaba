package data_base.user;

import data_base.ConnectorDB;
import model.dao.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserFinderDb {
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
}
