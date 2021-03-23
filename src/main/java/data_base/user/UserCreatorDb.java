package data_base.user;

import data_base.ConnectorDB;
import model.dao.User;

import java.sql.*;

public class UserCreatorDb {
    public User addUser(User user) {
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
}
