package data_base.user;

import data_base.ConnectorDB;
import model.dao.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserEditorDb {
    public User updateUser (User user, String firstName, String secondName,int age) {
        String query = "UPDATE user SET firstName = ?, secondName = ?, age = ? WHERE ID = ?";
        PreparedStatement preparedStatement ;
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
    public User updatePassword (User user,String password) {
        String query = "UPDATE user SET password = ? WHERE ID = ?";
        PreparedStatement preparedStatement ;
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
