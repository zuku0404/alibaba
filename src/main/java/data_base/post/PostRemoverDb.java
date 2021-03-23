package data_base.post;

import data_base.ConnectorDB;
import model.dao.Post;

import java.sql.*;

public class PostRemoverDb {
    public void removePost(Post post) {
        String query = "DELETE FROM post WHERE id = ?";
        PreparedStatement preparedStatement = null;
        try (Connection connection = ConnectorDB.createConnection()) {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, post.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
